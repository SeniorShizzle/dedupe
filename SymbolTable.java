
public interface SymbolTable<K,V> {

	/**
	 * add key-value pair to the symbol table
	 * @param key
	 * @param value
	 * @throws NullPointerException if key is null
	 */
	void put(K key, V value);
	
	/**
	 * retrieve the value associated with the key
	 * @param key
	 * @return returns the value associated with the key, otherwise it returns null
	 * @throws NullPointerException if the key is null
	 */
	V get(K key);
	
	/**
	 * deletes the key-value pair associated with the key from the symbol table
	 * @param key
	 * @throws NullPointerException if the key is null
	 */
	void delete(K key);
	
	/**
	 * is a key in the symbol table or not?
	 * @param key
	 * @return returns true if the a key-value pair is associated with the key, otherwise false
	 * @throws NullPointerException if the key is null
	 */
	boolean contains(K key);
	
	/**
	 * 
	 * @return true if the table is empty, otherwise false
	 */
	boolean isEmpty();
	
	/**
	 * returns the number of key-value pairs in the table
	 * @return
	 */
	int size();
	
	/**
	 * 
	 * @return iterator of keys in the table
	 */
	Iterable<K> keys();
	
}
