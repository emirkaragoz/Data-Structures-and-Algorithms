package Q1;

import java.security.InvalidParameterException;

/**
 * Double Hash Map
 * @param <K> The Key
 * @param <V> The Value
 */
public class DoubleHashMap<K, V> implements Map{

    public static class Entry<K, V> {
        /** The key */
        private K key;
        /** The value */
        private V value;

        /**
         * Creates a new key-value pair.
         * @param key The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key.
         * @return The key
         */
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         * @return The value
         */
        public V getValue() {
            return value;
        }
    }

    /** Hash Table*/
    private Entry<K, V>[] table;
    /** Start capacity of Hash Table*/
    private static int START_CAPACITY;
    /** Load Factor*/
    private double LOAD_THRESHOLD = 0.75;
    /**Number of element in Hash Map*/
    private int numKeys;
    /**Number of deleted elements in hash map*/
    private int numDeletes;
    /**DELETED Template*/
    private final Entry<K, V> DELETED = new Entry<>(null, null);

    /**
     * Constructor
     * @param startCap Input Starting Capacity
     */
    public DoubleHashMap(int startCap) {
        if (startCap < 7)
            throw new InvalidParameterException("Hash Map Starting Capacity must be at least 7!");
        START_CAPACITY = startCap;
        table = new Entry[START_CAPACITY];
    }

    /**
     * Table Getter
     * @return hash table
     */
    public Entry<K, V>[] getTable() {
        return table;
    }

    /**
     * Helper find method
     * @param key key of the searched element
     * @return index of element in hash table
     */
    private int find(Object key) {

        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }

        while ((table[index] != null)
                && (!key.equals(table[index].key))) {

            index += (7-(key.hashCode()%7)) % table.length; //double hash formule

            if (index >= table.length) {
                index = 0;
            }
        }
        return index;
    }

    /**
     * Rehash Method
     */
    private void rehash() {
        Entry<K, V>[] oldTable = table;

        table = new Entry[2 * oldTable.length + 1];

        numKeys = 0;
        numDeletes = 0;
        for (Entry<K, V> anOldTable : oldTable)
            if ((anOldTable != null) && (anOldTable != DELETED))
                put(anOldTable.key, anOldTable.value);
    }

    /**
     * Get Method
     * @param key key of element
     * @return value of given key
     */
    @Override
    public Object get(Object key) {
        int index = find(key);

        if (table[index] != null)
            return table[index].value;
        else
            return null;
    }

    /**
     * check whether the table is empty
     * @return if empty return true, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return numKeys==0;
    }

    /**
     * Put Method
     * @param key key of element
     * @param value value of element
     * @return putted element
     */
    @Override
    public Object put(Object key, Object value) {
        int index = find(key);

        if (table[index] == null) {
            table[index] = new Entry<>((K)key, (V)value);
            numKeys++;

            double loadFactor = (double) (numKeys + numDeletes) / table.length;
            if (loadFactor > LOAD_THRESHOLD)
                rehash();

            return null;
        }

        V oldVal = table[index].value;
        table[index].value = (V)value;
        return oldVal;
    }

    /**
     * Remove method
     * @param key key of removed element
     * @return value of removed element
     */
    @Override
    public Object remove(Object key) {
        int index = find(key);

        if (table[index] != null) {
            Entry result = table[index];
            table[index] = DELETED;
            --numKeys;
            ++numDeletes;
            return result.value;
        }
        else
            return null;
    }

    /**
     * Size Method
     * @return size of hash map
     */
    @Override
    public int size() {
        return numKeys;
    }
}
