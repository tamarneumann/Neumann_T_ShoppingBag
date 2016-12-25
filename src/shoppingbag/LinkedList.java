package shoppingbag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import Exceptions.*;

//this is an unsorted linked list, doesn't check for duplicates
public class LinkedList<T extends Comparable<T> & Serializable> implements Serializable,Iterable<T> {
  private Node<T> head;
  
  public LinkedList(){
	  head = null;
  }
  
  public void add(T data){
	  //set up a new Node that references this data
	  Node<T> newNode = new Node<T>(data);
	  //if the linked list is empty , place this node at the head
	  if (head == null){
		  head = newNode;
	  }
	  else{
		  //must iterate through the list to find the last node
		  Node<T> currentNode = head;
		  Node<T> previousNode = head;
		  while (currentNode != null){
			  previousNode = currentNode;
			  currentNode = currentNode.getNext();
		  }
		  //found the end of the list
		  previousNode.setNext(newNode);
	  }
  }
  
 public boolean remove(T data){
	  //find the Node that contains the data and remove it from the list
	  if (head == null){
		 return false;  //there was no data to remove
	  }
	  Node<T> currentNode = head;
	  Node<T> previousNode = head;
	  while (currentNode != null){
		  if (currentNode.getData().compareTo(data)==0){
			   //found the data, reset links
              if (currentNode == head){
				  //have to reset head
				  head = currentNode.getNext();
			  }
              else{ //somewhere in the middle
			     previousNode.setNext(currentNode.getNext());
              }
			 
			  return true;   //job well done!
		  }
		  previousNode = currentNode;
		  currentNode = currentNode.getNext(); //proceed to next Node in the list
	  }
	  //didn't find the data
	  return false;
  } 
 public void reverse()
 {
	 LinkedList<Integer> linked=new LinkedList();
	 ArrayList<Integer> al=new ArrayList();
	 
	 linked.add(5);
	 linked.add(15);
	 linked.add(20);
	 linked.add(25);
	 
	 Node current=head;
	 Node previous=head;
	 while(head!=null)
	 {
	 while(current!=null)
	 {
		 previous=current;
		 current=current.getNext();
		 
		 
	 }
	   al.add((Integer) previous.getData());
	 }
	 
	 for(int i=0;i<al.size();i++)
	 {
		 System.out.println(al.get(i));
	 }
 } 
  
     public Iterator<T> iterator(){
    	 return new LinkedListIterator();
     }
  
      class LinkedListIterator implements Iterator<T>{
    		
    		private Node<T> current;
    		private boolean removeCalled;
    		
    		private Node<T> previous;
    		private Node<T> beforeprevious;
    		
    		public LinkedListIterator(){
    			//initialize variables
    			this.removeCalled = false;
    		    this.current =  head;
    			this.previous = null;   //doesn't point anywhere yet
    			this.beforeprevious = null; //doesn't point anywhere yet
    		}
    		
    		public boolean hasNext(){
    			if (current !=null){
    				return true;
    			}
    			else{
    				return false;
    			}
    		}
    		
    		public T next(){
    			
    			if (hasNext()){
    			 
    			  T currentData = current.getData(); //data that will be returned
    			  
    			  //must continue to keep track of the Node that is in front of the
    			  //current Node whose data is must being returned , in case 
    			  //its nextNode must be reset to skip the Node whose data is just being returned
    			  beforeprevious = previous;
    			   //must continue keep track of the Node that is referencing the data
    			  //that was just returned in case the user wishes to remove() the
    			  //data that was just returned
    			  previous = current;
    			  //get ready to point to the Node with the next data value
    			  current = current.getNext();  //move to next Node in the chain, get ready to point to the 
    			                                //next data item in the list
    			  
    			  this.removeCalled = false;	//now pointing to next value in the list
    			                                //which is not the one that may have been removed
    			  return currentData; 
    		}
    			else{
    				throw new NoSuchElementException();
    			}
    		}
    		
    		//note: this method won't work unless the class has access to the
    		//LinkedList head so that it can reset it if remove() requires removal of the
    		//first data value in the LinkedList
    		public void remove(){
    			//can't process remove()
    			// a. if the iterator is pointing to the end of the list
    			// b. if the node the iterator is pointing to was just removed.
    			//if current == null - the iterator is pointing to the end of the list
    			//if removeCalled == true then current node has been removed.
    			//to keep track if the remove() method has been called twice in a row
    			//to do so set up a boolean , removeCalled, that will be set
    			//whenever remove() is called and reset to false when the pointer
    			//progresses to the next node in the linkedlist
    		    if (this.removeCalled || previous == null){
    				throw new IllegalStateException();
    			}
    		    
    		    //the data that must be removed is referenced by the 
    		    //Node that is before current since the next() method moves the 
    		    //pointer over after it returns what was pointed to by "current"
    			else{
    				
    				//remove the data that is referenced by the first node, special case
    				if (previous == head){
    					//must reset head but the head 
    					head = previous.getNext();
    					previous = null;//there is no Node in front of the new head of the list
    					
    				}
    				else{
    					//remove somewhere in the middle of the list
    					//have to set the previous node 
    					previous = beforeprevious;
    					beforeprevious.setNext(current);
    				}
    			 
    				this.removeCalled = true;  //we are in the process of removing an element
    				
    			}
    			
    		}

    	}

}
