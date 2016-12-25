package FancyShoppingBag;


import java.io.Serializable;

public class Node<T extends Comparable<T> & Serializable> implements Comparable<Node<T>>,Serializable{
	private T data; //this is the data the Node is referencing
	private Node<T> nextNode; //this is the Node that this Node is pointing to , next on the chain
	

	public Node(T data){
		this.data = data;
		this.nextNode = null; //last node on the chain
	}
	
	public void setNext(Node<T> next){
		this.nextNode = next;
	}
	
	public Node<T> getNext(){
		return nextNode;
	}
	
	public T getData(){
		return this.data;
	}
	
	public int compareTo(Node<T> node){
		return this.data.compareTo(node.data); //comparison is based on the data of the Nodes
	}
}