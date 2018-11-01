package pack;

public class Node<K extends Comparable<K>,V> {
	private V data;
	K key;
	V value;
	int height;
	private Node<K,V> next, parent, leftChild, rightChild;
	public Node() {
		next = null;	
	}
	public Node(V value) {
		data = value;
		next = null;
	}
	Node(K key, V value){
		this.key = key;
		this.value = value;
	}
	public V getData() {
		return data;
	}
	public void setData(V x) {
		data = x;
	}
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public int compareTo(K otherKey) {
		return this.key.compareTo(otherKey);
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node y) {
		next = y;
	}
	public Node getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(Node a) {
		leftChild = a;
	}
	public Node getRightChild() {
		return rightChild;
	}
	public void setRightChild(Node b) {
		rightChild = b;
	}
}
