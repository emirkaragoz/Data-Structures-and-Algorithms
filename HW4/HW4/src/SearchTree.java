/**
 * Search Tree Interface
 * @param <E> generic type
 */
public interface SearchTree<E> {
    /**
     * add item, if grater than local root to right side, otherwise to left side
     * @param item item to add
     * @return  if success true, otherwise false
     */
    boolean add(E item);

    /**
     * Starter method find.
     * @pre The target object must implement
     *      the Comparable interface.
     * @param target The Comparable object being sought
     * @return The object, if found, otherwise null
     */
    E find(E target);

    /**
     * checks whether targer is in tree
     * @param target    target node
     * @return  if found true,otherwise false
     */
    boolean contains(E target);

    /**
     * Deletes target from tree
     * @param target    node to delete
     * @return  The object, if deleted otherwise null
     */
    E delete(E target);

    /**
     * Removes target from tree
     * @param target    node to remove
     * @return  if success true,otherwise false
     */
    boolean remove(E target);
}
