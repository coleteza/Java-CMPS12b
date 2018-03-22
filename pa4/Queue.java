//Queue.java
//Cole Teza
//#1361038
//PA4
//-----------------------------------------------------------------

public class Queue implements QueueInterface{
	
	private Node front;
	private Node back;
	private int numItems;
	
	//Queue Constructor
	Queue(){
		//Number of items in Queue
		numItems = 0;
		//front of Queue (i.e. First Entered)
		front = null;
		//back of Queue (i.e. Last Entered)
		back  = null;
		
	} 
	
	private class Node{
		Object item;
		Node next;
    
		Node(Object item){
			this.item = item;
			next = null;
		}
	}

	public boolean isEmpty(){
		if(numItems==0) return true;
		else return false;
	}

	public int length(){
	return numItems;
	}

	public void enqueue(Object newItem){
		if(front == null){
			front = new Node(newItem);
			back=front;
			numItems++;
		}	
		else{
			Node T= front;
			while(T.next!= null){
				T=T.next;
			}
			T.next =new Node(newItem);
			back=T.next;
			numItems++;
		}
		
	}

	public Object dequeue(){
		if(front == null){
			throw new QueueEmptyException("Usage: You can not dequeue() and empty queue");
		}
		else{
			Object retVal= front.item;
			front=front.next;
			numItems--;
			return retVal;
		}
	}

	public Object peek(){
		if(front == null){
			throw new QueueEmptyException("Usage: You can not peek() on an empty queue");
		}
		else{
			return front.item;
		}
	}

	public void dequeueAll(){
		if(front == null){
			System.out.println("NOTE: You just ran dequeueAll() on an empty queue");
		}
		front = null;
		back = null;
		numItems = 0;	
	}

	public String toString(){
		//System.out.println("Made it");
		String output = "";
		Node temp= front;
		//While temp is not null parse through the array saving the value of item to the output string
		while( temp!= null){
			//System.out.println(temp.item); //**
			output= output+" "+ temp.item.toString();
			//System.out.println(output); //**
			temp=temp.next;
		}
		//return String with total Queue 
		return output;
	}
}
	
	
