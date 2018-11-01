package pack;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class LinkedMap<K extends Comparable<K>,V> implements Maps<K,V> {
	private Node<K,V> head,tail;
	private int length = 0;
	public LinkedMap() {
		head = tail = new Node<K,V>();
	}
	
	public void put(K key, V value){
        Node<K, V> temp = new Node<K,V>(key, value);
        if(length == 0) {
        	//inserting node between head and tail
            head.setNext(temp);
            temp.setNext(tail);
            length++;
            return;
        }
        //if get is null, key is unique
        if(get(key) == null) {
            Node<K,V> iterator;
            iterator = head;
            while(iterator.getNext() != tail) {
                iterator = iterator.getNext();
            }
            //once it hits the end of the list, it adds the node
            iterator.setNext(temp);
            temp.setNext(tail);
            length++;
            return;
        }
        else {
        throw new IllegalArgumentException("Key Exists");
        }
    }
		
	public V get(K key) {
        Node temp = getHead();
        //iterates until key matches or null node is hit
        while(temp.getNext() != tail){
            temp = temp.getNext();
            if(temp.getKey() == key) {
                return (V) temp.getValue();
            }
        }
        //no key called
        return null;
    }
	
	public void remove(K key) {
        Node temp = getHead();
        while(temp.getNext().getKey() != key){
            if(temp == null) {
                return;
            }
            temp = temp.getNext();
        }
        temp.setNext(temp.getNext().getNext());
        length--;
    }
	
	public int size() {
		return length;
	}
	
	public boolean isEmpty() {
		return (size() == 0);
	}

	public Node<K,V> getHead() {
		return head;
	}

	public void setHead(Node<K,V> head) {
		this.head = head;
	}

	public Node<K,V> getTail() {
		return tail;
	}

	public void setTail(Node<K,V> tail) {
		this.tail = tail;
	}
}