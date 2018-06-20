package Q1;

/**
 * Map Interface
 * @param <K>   key
 * @param <V>   value
 */
public interface Map<K, V> {
    /**
     * Get Method
     * @param key key of element
     * @return value of given key
     */
    V get (Object key);
    /**
     * check whether the table is empty
     * @return if empty return true, otherwise false
     */
    boolean isEmpty();
    /**
     * Put Method
     * @param key key of element
     * @param value value of element
     * @return putted element
     */
    V put(K key, V value);
    /**
     * Remove method
     * @param key key of removed element
     * @return value of removed element
     */
    V remove(Object key);
    /**
     * Size Method
     * @return size of hash map
     */
    int size();
}
