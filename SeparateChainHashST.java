import java.util.ArrayList; // allowed import by assignment parameters

/**
 * A symbol table backed with a hash table for quick indexing.
 * This implementation uses the separate chain method, whereby keys are hashed for quick indexing into a hash table
 * before being finally inserted into a short linked-list symbol table.
 *
 * @param <K> the type of key to be stored in the symbol table
 * @param <V> the type of value to be associated with each key
 */
public class SeparateChainHashST<K,V> implements SymbolTable<K,V> {

    /** The size of the symbol table */
	private int size = 0;

    /** The domain of hash indices, used for calculating the hashTable index */
    private final int hashDomain; // largest prime before 1000

    /** The primary hash table of linked list symbol tables, used to store key-and-value pairs */
    private SequentialSearchST<K, V>[] hashTable;

    /**
     * Constructs a symbol table with a default hash domain size of 1999
     */
    public SeparateChainHashST(){
        this(1999); // Our default hash domain will be 1999, closest prime number to 2000
    }

    /**
     * Constructs a symbol table with the given hash domain size. A higher hash domain size increases
     * efficiency by reducing hash collisions, at the cost of significantly increased memory usage.
     *
     * Note: For efficiency in hashing, using a prime number {@code hashDomainSize} is recommended.
     * @param hashDomainSize the size of hash table to use to back the symbol table
     */
    @SuppressWarnings("unchecked")
    public SeparateChainHashST(int hashDomainSize){
        this.hashDomain = hashDomainSize;
        this.hashTable = (SequentialSearchST<K, V>[]) new SequentialSearchST[hashDomainSize];
        for (int i = 0; i < hashDomainSize; i++) {
            hashTable[i] = new SequentialSearchST<>(); // initialize a symbol table at each hash index
        }
    }

    /**
     * Places the key-and-value pair into the symbol table. If the key already exists in the table, its value is
     * overwritten by {@code value}
     * @param key the key to insert into the symbol table
     * @param value the value to associate with the inserted key
     */
	@Override
	public void put(K key, V value) {
        SequentialSearchST<K, V> table =  hashTable[hash(key)];
        int initialSize = table.size();
        table.put(key, value);
        if (table.size() > initialSize) this.size++; // the put has incremented the count
    }

    /**
     * Returns the value associated with the passed key. Returns {@code null} if the key does not appear in the table
     * @param key the key whose value is requested
     * @return the value of type {@code V} associated with the key, or {@code null} if the key does not exist
     */
	@Override
	public V get(K key) {
		return hashTable[hash(key)].get(key);
	}

    /**
     * Deletes the key-and-value entry associated with the given {@code key}
     * @param key the key to remove from the symbol table
     */
	@Override
	public void delete(K key) {
        this.size --;
		hashTable[hash(key)].delete(key);
	}

    /**
     * Returns {@code TRUE} if the symbol table contains a value associated with the given key
     * @param key the key to check for inclusion in the symbol table
     * @return {@code TRUE} if the symbol table contains a value associated with the given key
     */
	@Override
	public boolean contains(K key) {
		return hashTable[hash(key)].contains(key);
	}

    /**
     * Translates the object's hash code to within the range of our hash domain (size of the hash table, or M).
     * The resultant value can be safely used for hits on the hash map
     *
     * @param key the key for which to calculate a hash index
     * @return a guaranteed non-negative 32 bit integer less than {@code hashDomain}
     */
    private int hash(K key){
        /// 0x7FFFFFFF is a bitmask for the least significant 31 bits, which causes the sign bit (MSB) to be ignored
        return (key.hashCode() & 0x7FFFFFFF) % hashDomain;
    }

    /**
     * Returns TRUE if the symbol table is empty
     * @return {@code TRUE} if the symbol table has no keys
     */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

    /**
     * Returns the size of the symbol table
     * @return the number of keys contained within the symbol table
     */
	@Override
	public int size() {
		return size;
	}

    /**
     * Returns an iterable object, which can be used inside of enhanced for loops
     * @return an Iterable object of type K (the same type as the keys in the symbol table)
     */
	@Override
	public Iterable<K> keys() {
        ArrayList<K> keys = new ArrayList<>(size);

        for (SequentialSearchST<K, V> table : hashTable){ // add each key to the ArrayList by visiting each linked list
            for (K key : table.keys()){
                keys.add(key);
            }
        }

        return keys; // we've added each key to the ArrayList, which implements iterable, so we can just return that
    }

}
