package pack;

public class AVLTree <K extends Comparable<K>,V> implements Maps<K,V> {
	private Node<K,V> head;
	int count = 0;
	
	public void put(K key, V value) {
		if(this.head == null) {
			head = new Node(key, value);
			head.height = 1;
			count++;
		}
		else {
			head = put(key, value, head);
		}
	}
	
	public Node<K,V> put(K key, V value, Node<K,V> current){
		if(current == null) {
			current = new Node<K,V>(key, value);
			current.height = max(current.getLeftChild(), current.getRightChild()) + 1;
			return current;
		}
		//key is already used
		else if(key == current.getKey()) {
			return null;
		}
		else if (key.compareTo(current.getKey()) < 1){
			current.setLeftChild(put(key, value, current.getLeftChild()));
		}
		else {
			current.setRightChild(put(key, value, current.getRightChild()));
		}
		
		//Got help for rebalancing from Alan (Allen?)
		int balance = height(current.getLeftChild()) - height(current.getRightChild());
		if(balance > 1) {
			if(height(current.getLeftChild().getLeftChild()) >= height(current.getLeftChild().getRightChild())) {
				current = rightRotation(current);
			}
			else {
				current.setLeftChild(leftRotation(current.getLeftChild()));
				current = rightRotation(current);
			}
		}
		else if(balance < -1) {
			if(height(current.getRightChild().getRightChild()) >= height(current.getRightChild().getLeftChild())) {
				current = leftRotation(current);
			}
			else {
				current.setRightChild(rightRotation(current.getRightChild()));
				current = leftRotation(current);
			}
		}
		else {
		current.height = max(current.getLeftChild(), current.getRightChild()) + 1;
		}
		return current;
	}

	public V get(K key) {
		Node<K,V> current = this.head;
		//key is not in the map
		while(true) {
			if(current == null) {
				return null;
			}
			if(current.getKey() == key) {
				return current.getValue();
			}
			if(key.compareTo(current.getKey()) < 0) {
				current = current.getLeftChild();
			}
			else {
				current = current.getRightChild();
			}
		}
	}

	public void remove(K key) {
		Node<K,V> current = head;
		//nothing to remove if tree is empty
		if(head == null) {
			return;
		}
		while(true) {
			//nothing to remove if tree is empty
			if(current == null) {
				return;
			}
			else if(key.compareTo(current.getKey()) == 0) {
				current = nextPossible(current);
				count--;
			}
			else if(key.compareTo(current.getKey()) < 0) {
				current = current.getLeftChild();
			}
			else {
				current = current.getRightChild();
			}
		}
	}
	//finds the closest value to the node being removed
	public Node<K,V> nextPossible(Node<K,V> current) {
		if(current.getLeftChild() == null && current.getRightChild() == null) {
			current = null;
			return null;
		}
		else if(current.getRightChild() == null){
			Node<K,V> temp = current.getLeftChild(), follower = null, keep = temp;
			if(current.getLeftChild().getRightChild() != null) {
				temp = current.getLeftChild().getRightChild();
			}
			//while left child still has right children, go further since it will be closer to the value removed
			while(temp != null && temp.getRightChild() != null) {
				follower = temp;
				temp = temp.getRightChild();
			}
			//no following children, so return value stored and remove
			if(temp.getLeftChild() == null) {
				keep = temp;
				temp = null;
				return keep;
			}
			//one left child, so replace current with left child and return value stored
			if(temp.getLeftChild() != null) {
				keep = temp;
//ERROR HERE nullPointerException
				//follower.setRightChild(temp);
				return keep;
			}
			else {
				return temp;
			}
		}
		else {
			Node<K,V> temp = current.getRightChild(), follower = temp, keep = temp;
			if(current.getRightChild().getLeftChild() != null) {
				temp = current.getRightChild().getLeftChild();
			}
			//while right child still has left children, go further since it will be closer to the value removed
			while(temp != null && temp.getLeftChild() != null) {
				follower = temp;
				temp = temp.getLeftChild();
			}
			//no following children, so return value stored and remove
			if(temp.getRightChild() == null) {
				keep = temp;
				temp = null;
				return keep;
			}
			//one right child, so return value stored and replace with right child
			if(temp.getRightChild() != null) {
				keep = temp;
//ERROR HERE nullPointerException
				//follower.setLeftChild(temp);
				return keep;
			}
			else {
			return temp;
			}
		}
	}

	public int size() {
		return 0;
	}

	public boolean isEmpty() {
		if(count == 0) {
		return true;
		}
		else {
			return false;
		}
	}
	
	public int max(Node<K,V> left, Node<K,V> right) {
		if(height(left) > height(right)) {
			return height(left);
		}
		else {
			return height(right);
		}
	}
	
	public int height(Node<K,V> current) {
		if(current == null) {
			return 0;
		}
		else {
			return current.height;
		}
	}
	//helped by Alan (Allen?)
	private Node<K,V> rightRotation(Node<K,V> root) {
		Node<K,V> newRoot = root.getLeftChild();
		root.setLeftChild(root.getLeftChild().getRightChild());
		newRoot.setRightChild(root);
		root.height = max(root.getLeftChild(), root.getRightChild()) + 1;
		newRoot.height = max(newRoot.getLeftChild(), newRoot.getRightChild()) + 1;
		return newRoot;
	}
	//helped by Alan (Allen?)
	private Node<K,V> leftRotation(Node<K,V> root) {
		Node<K,V> newRoot = root.getRightChild();
		root.setRightChild(root.getRightChild().getLeftChild());
		newRoot.setLeftChild(root);
		root.height = max(root.getLeftChild(), root.getRightChild()) + 1;
		newRoot.height = max(newRoot.getLeftChild(), newRoot.getRightChild()) + 1;
		return newRoot;
	}

}
