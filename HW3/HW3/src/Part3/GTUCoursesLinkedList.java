package Part3;

import Part1.Course;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

/**
 * GTU Courses Linked List Class
 */
public class GTUCoursesLinkedList {
    /**
     * head node of linked list
     */
    private Node head = null;
    /**
     * size of linked list
     */
    private int size = 0;

    /**
     * Constructor
     */
    GTUCoursesLinkedList(){
    }

    /**
     * Private Inner Node Class
     */
    private class Node {
        /**
         * data of node
         */
        private Course data;
        /**
         * next node
         */
        private Node next;
        /**
         * next node in same semester
         */
        private Node nextofSemester = null;

        /**
         * Constructor
         * @param element data of node
         * @param n node
         */
        public Node(Course element, Node n){
            data = element;
            next = n;
        }

        /**
         * Constructor
         * @param element data of node
         */
        public Node(Course element){
            this(element,null);
        }

        /**
         * Constructor
         */
        public Node(){
            this(null,null);
        }

        /**
         * toString method
         * @return a string representation of the object.
         */
        @Override
        public String toString() {
            return data.toString();
        }

        /**
         * Indicates whether some other object is "equal to" this one.
         * @param obj the reference object with which to compare.
         * @return {true} if this object is the same as the obj argument; {false} otherwise.
         */
        @Override
        public boolean equals(Object obj) {
            return obj.toString().equals(this.toString());
        }
    }

    /**
     * Public Iterator Class
     */
    public class Iterator{
        /**
         * Iterator head
         */
        private Node headNode = head;
        /**
         * Iterator semester head
         */
        private Node semesterHead = head;
        /**
         * Iterator current node
         */
        private Node currentNode = null;

        /**
         * Constructor
         */
        public Iterator(){}

        /**
         * Checks whether iteration has more element
         * @return Returns true if the iteration has more elements, otherwise return false.
         */
        public boolean hasNext() {
            if (size == 0) {
                return false;
            } else if (currentNode == null){
                return true;
            } else if (currentNode.next == null){
                return false;
            }
            return true;
        }

        /**
         * Returns the next element in the iteration.
         * @return the next element in the iteration
         */
        public Course next() {
            if (currentNode == null) {
                currentNode = headNode;
                if (currentNode == null)
                    throw new NoSuchElementException("There is not next element.");
                semesterHead = currentNode;
                return currentNode.data;
            }
            if (currentNode.next == null) {
                throw new NoSuchElementException("There is not next element.");
            }
            currentNode = currentNode.next;
            semesterHead = currentNode;
            return currentNode.data;
        }

        /**
         * Returns the next element in same semester in the iteration.
         * @return the next element in same semester in the iteration
         */
        public Course nextInSemester() {
            if (currentNode == null) {
                currentNode = headNode;
                if (currentNode == null)
                    throw new NoSuchElementException("There is not next element.");
                return currentNode.data;
            }
            currentNode = currentNode.nextofSemester;
            return currentNode.data;
        }

        /**
         * Checks whether iteration has more element in same semester(not semester head)
         * @return Returns true if the iteration has more elements, otherwise return false.
         */
        public boolean hasNextInSemester() {
            if (size == 0) {
                return false;
            } else if (currentNode == null){
                return true;
            } else if (currentNode.nextofSemester.equals(semesterHead)){
                return false;
            }
            return true;
        }
    }

    /**
     * @return size of linked list
     */
    public int size() {
        return size;
    }

    /**
     * clears all linked list
     */
    public void clear(){
        for (Node x = head; x != null; ) {
            Node next = x.next;
            x.data = null;
            x.next = null;
            x = next;
        }
        head =  null;
        size = 0;
    }

    /**
     * Connects same semester courses circularly
     */
    private void connectSemesters(){
        Node tmpHead = head;
        Node tmpCurrent = tmpHead;
        Node lastEqualNode = tmpHead;
        String connectedSemesters="";

        while (tmpHead != null) { //listede kendi sömestarından olan derslerle eşleşmemiş bir course olduğu sürece
            while (tmpCurrent.next != null) {
                if (tmpCurrent.next.data.getSemester().equals(tmpHead.data.getSemester())) {
                    lastEqualNode.nextofSemester = tmpCurrent.next;
                    lastEqualNode = tmpCurrent.next;
                }
                tmpCurrent = tmpCurrent.next;
            }
            lastEqualNode.nextofSemester = tmpHead;
            connectedSemesters += lastEqualNode.data.getSemester(); //history of passed and connected semesters
            tmpCurrent = head;
            while (tmpCurrent != null) {
                if (tmpCurrent.next != null && !isInclude(connectedSemesters, tmpCurrent.next.data.getSemester())) {
                    tmpHead = tmpCurrent.next;
                    break;
                }
                tmpCurrent = tmpCurrent.next;
                if (tmpCurrent == null)
                    tmpHead = null;
            }
            tmpCurrent = tmpHead;
            lastEqualNode = tmpHead;
        }
    }

    /**
     * Helper method for connectSemester
     * @param mainString    passed semesters
     * @param keyString     key semester
     * @return  if mainString include keyString true, otherwise false
     */
    private boolean isInclude(String mainString, String keyString){
        char parse = keyString.charAt(0);
        for (int i = 0; i<mainString.length() ; ++i)
            if (mainString.charAt(i) == parse)
                return true;
        return false;
    }

    /**
     * Inserts the specified element at the end of this list.
     * @param e element to be inserted
     */
    public void add(Course e){
        Node newNode = new Node(e);
        Node tmp = head;
        if (size == 0){
            head = newNode;

        } else {
            while (tmp.next != null){
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }
        ++size;
        connectSemesters();
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * @param e element to be inserted
     * @param index index at which the specified element is to be inserted
     */
    public void add(Course e,int index){
        checkPositionIndexForAdd(index);
        Node newNode = new Node(e);
        Node tmp = head;

        if (index == 0){
            newNode.next = tmp;
            head = newNode;
        } else {
            for (int i = 1; i < index ; ++i) {
                tmp = tmp.next;
            }
            newNode.next= tmp.next;
            tmp.next = newNode;
        }
        ++size;
        connectSemesters();
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     */
    public Course remove(int index){
        if (index<0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        Node tmp = head;
        Node removed;

        if (index == 0){
            removed = tmp;
            head = tmp.next;
        } else {
            for (int i = 1; i < index; ++i) {
                tmp = tmp.next;
            }
            removed = tmp.next;
            tmp.next = tmp.next.next;
        }
        --size;
        connectSemesters();
        return removed.data;
    }

    /**
     * Removes the given element in this list.
     * @param o element to be removed from this list, if present
     * @return the element previously at the specified position
     */
    public Course remove(Object o){
        if (o == null)
            throw new NullPointerException("Input object is null.");

        Node tmp = head;
        int index=0,findKey=0;
        while (tmp != null) {
            if (tmp.data.toString().equals(o.toString())) {
                findKey = 1;
                break;
            }
            tmp = tmp.next;
            ++index;
        }
        if (findKey != 1){
            throw new InvalidParameterException("The element to be deleted is not in list.");
        }
        connectSemesters();
        return remove(index);
    }

    /**
     * Helper Method for Add methods
     * @param index given index
     */
    private void checkPositionIndexForAdd(Integer index) {
        if (size == 0 && index == 0)
            return;
        if (index<0 || index > size)
            throw new IndexOutOfBoundsException(index.toString()+" is out of bounds");
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator i = new Iterator();
        while (i.hasNext())
            sb.append(i.next());
        return sb.toString();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param obj
     * @return true if this object is the same as the obj argument, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(this.toString());
    }
}
