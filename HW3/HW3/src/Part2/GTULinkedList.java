package Part2;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * GTU Linked List Class
 * @param <E>   generic type of list
 */
public class GTULinkedList <E> extends LinkedList {
    /**
     * linked list of disabled nodes
     */
    private LinkedList<disabledElements> disabledMembers = new LinkedList<>();

    /**
     * disables node that has given index
     * @param index index of node
     * @return  true or false
     */
    public boolean disable(int index){
        if (index>=0 && index<super.size()){  //index parameter check
            disabledElements tmp = new disabledElements(index,get(index).toString());
            for (int i=0 ; i<disabledMembers.size(); ++i)
                if (disabledMembers.get(i).getIndex() == index){    //if it's already disabled
                    System.out.println("Element which is in this index has been already disabled.");
                    return false;
                }
            disabledMembers.add(tmp);
            return true;
        }
        else {
            throw new IndexOutOfBoundsException("Given index is out of index bounds!");
        }
    }

    /**
     * disables given node
     * @param o given node
     * @return   true or false
     */
    public boolean disable(Object o){
        for (int i=0; i<super.size() ;++i){
            if (o.toString().equals(get(i).toString()))
                return disable(i);  //finds index of given node and calls overloaded disable(index) method
        }
        return false;
    }

    /**
     * enables node that has given index
     * @param index index of node
     * @return true or false
     */
    public boolean enable(int index){
        int findStatus=0,i;
        if (index>=0 && index<super.size()) { //index parameter check
            for (i = 0; i < disabledMembers.size(); ++i)
                if (disabledMembers.get(i).getIndex() == index) {
                    findStatus = 1;
                    break;
                }
            if(findStatus != 1){    //if it's already enable
                System.out.println("Element which is in this index is already enable.");
                return false;
            }
            else {
                disabledMembers.remove(i);
                return true;
            }
        }
        else {
            throw new IndexOutOfBoundsException("Given index is out of index bounds!");
        }
    }

    /**
     * enables given node
     * @param o given node
     * @return  true or false
     */
    public boolean enable(Object o){
        for (int i=0; i<super.size() ;++i){
            if (o.toString().equals(get(i).toString()))
                return enable(i);   //finds index of given node and calls overloaded enable(index) method
        }
        return false;
    }

    /**
     * prints disabled elements
     */
    public void showDisable(){
        System.out.println("Disabled elements are :\n"+disabledMembers);
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return  the element at the specified position in this list
     */
    @Override
    public Object get(int index) {
        if (index>=0 && index<super.size()) {
            for (int i = 0; i < disabledMembers.size(); ++i) {
                if (index == disabledMembers.get(i).getIndex()) {
                    throw new InvalidParameterException("You can't access this element. It was disabled");
                }
            }
            return super.get(index);
        }
        else
            throw new IndexOutOfBoundsException("Given index is out of index bounds!");
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index index of the element to return
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     */
    @Override
    public Object set(int index, Object element) {
        if (index>=0 && index<super.size()) {
            for (int i = 0; i < disabledMembers.size(); ++i) {
                if (index == disabledMembers.get(i).getIndex()) {
                    throw new InvalidParameterException("You can't set a value to this index.There is already an disabled element in this index.");
                }
            }
            return super.set(index, element);
        }
        else
            throw new IndexOutOfBoundsException("Given index is out of index bounds!");
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return super.size()-disabledMembers.size();
    }

    /**
     * Removes the element at the specified position in this list.  Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     */
    @Override
    public Object remove(int index) {
        if (index>=0 && index<super.size()) {
            for (int i = 0; i < disabledMembers.size(); ++i) {
                if (index == disabledMembers.get(i).getIndex()) {
                    throw new InvalidParameterException("You can't remove this element. It was disabled");
                }
            }
            return super.remove(index);
        }
        else
            throw new IndexOutOfBoundsException("Given index is out of index bounds!");
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     *
     * @param o element to be removed from this list, if present
     * @return if this list contained the specified element
     */
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < disabledMembers.size(); ++i) {
            String tmp = disabledMembers.get(i).getValue();
            if (o.toString().equals(tmp)) {
                throw new InvalidParameterException("You can't remove this element. It was disabled!");
            }
        }
        return super.remove(o);
    }

    /**
     * Returns a list-iterator of the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     *
     * @param index index of the first element to be returned from the
     *              list-iterator
     * @return a ListIterator of the elements in this list (in proper
     *         sequence), starting at the specified position in the list
     */
    @Override
    public ListIterator listIterator(int index) {
        if (index>=0 && index<super.size()) {
            for (int i = 0; i < disabledMembers.size(); ++i) {
                if (index == disabledMembers.get(i).getIndex()) {
                    throw new InvalidParameterException("You can't start from this index.");
                }
            }
            return super.listIterator(index);
        }
        else
            throw new IndexOutOfBoundsException("Given index is out of index bounds!");
    }

    /**
     * Disable Elements Class
     */
    private class disabledElements{
        /**
         * index of disabled element
         */
        private int index;
        /**
         * value of disabled element
         */
        private String value;

        /**
         * Constructor
         * @param i given index
         * @param v given value
         */
        public disabledElements(int i,String v){
            index = i;
            value = v;
        }

        /**
         * gets index
         * @return index of disabled element
         */
        public int getIndex() {
            return index;
        }

        /**
         * gets value
         * @return  value of disabled element
         */
        public String getValue() {
            return value;
        }

        /**
         * Returns a string representation of the object.
         * @return a string representation of the object.
         */
        @Override
        public String toString() {
            return value;
        }
    }
}
