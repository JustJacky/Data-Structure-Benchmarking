package pack;

interface Maps<K, V> {
	  // Insert a value into the map associated with the key.
	  void put(K key, V value);

	  // Retrieve the value associated with the key.
	  V get(K key);

	  // Remove and return the value associated with the key.
	  void remove(K key);

	  // Return the number of elements in the map.
	  int size();

	  // Return true if there are no elements in the map.
	  boolean isEmpty();
	}

