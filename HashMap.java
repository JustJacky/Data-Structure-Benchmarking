package pack;

import java.util.ArrayList;
import java.util.Map;
import java.math.*;

public class HashMap<K extends Comparable<K>,V> implements Maps<K,V>{
	LinkedMap hashbrown[] = new LinkedMap[11];
	int count = 0;
	double MAX_LOAD_FACTOR = 0.75;
	HashMap() {
		//creates linkedmaps in each array space
		for(int i = 0; i < hashbrown.length; i++) {
			hashbrown[i] = new LinkedMap();
		}
	}

	  public void put(K key, V value) {
		  if(key == null){
			  hashbrown[0].put(key, value);
			  count++;
		  }
		  else {
			  //.hashCode() can be negative so Abs must be used for indexing the array
			   int hash = Math.abs(key.hashCode()) % hashbrown.length;
			   hashbrown[hash].put(key, value);
			   count++;
		  }
		  if(count / hashbrown.length > MAX_LOAD_FACTOR) {
			  LinkedMap tempHash[] = hashbrown;
			  while(count / (hashbrown.length *2 - 1) > MAX_LOAD_FACTOR) {
				  //to prevent a hashmap with a size divisible by 5
				  if(tempHash.length * 2 + 1 % 5 == 0) {
					  LinkedMap hashbrown[] = new LinkedMap[tempHash.length * 2 - 1];
				  }
				  else {
				  LinkedMap hashbrown[] = new LinkedMap[tempHash.length * 2 + 1];
				  }
			  }
			  //recreating linkedmaps for new hashmap
			  count = 0;
			  for(int i = 0; i < hashbrown.length; i++) {
				  hashbrown[i] = new LinkedMap();
			  }
			  //putting the data of the old map back in, redistributing because of new hash
			  for(int i = 0; i < tempHash.length; i++) {
				  Node <K,V> follower = tempHash[i].getHead();
				  while(follower != tempHash[i].getTail()) {
					  follower.setKey(key);
					  follower.setValue(value);
					  follower = follower.getNext();
					  count++;
				  }
			  }
			  //clearing memory of old hash
			  tempHash = null;
		  }
		  return;
	  }

	  public V get(K key) {
		  int hash = Math.abs(key.hashCode()) % hashbrown.length;
		  return (V) hashbrown[hash].get(key);
	  }

	  public void remove(K key) {
		  int hash = Math.abs(key.hashCode()) % hashbrown.length;
		  Node<K,V> follower = hashbrown[hash].getHead();
		  Node<K,V> temp = follower.getNext();
		  while(temp.getKey() != key || temp != null){
			  follower = temp;
			  temp = temp.getNext();
			  if(temp == null) {
				  return;
			  }
		  }
			  follower.setNext(temp.getNext());;
	  }
			  

	  public int size() {
		  int count = 0;
		  for(int i = 0; i < hashbrown.length; i++) {
			  count += hashbrown[i].size();
		  }
		  return count;
	  }

	  public boolean isEmpty() {
		  if(this.size() > 0) {
			  return false;
		  }
		  return true;
	  }
}