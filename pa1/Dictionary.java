//Cole Teza
//#1361038
//CMPS 12b PA3

public class Dictionary implements DictionaryInterface{
	
	private class Node {
		String key;
		String value;
		Node next;
	
		Node(String key, String value){
			this.key=key;
			this.value=value;
			next=null;
		}
	}

	private Node head = new Node(null, null);;
	private int listLength;
	
	public Dictionary(){
		head=null;
		listLength=0;
	}
	
//ADT OPERATIONS
//-------------------------------------------------------------------

	public boolean isEmpty(){
		if (listLength!=0){
			return false;
		}	
		else{
			return true;
		}
	}
	
	public int size(){
		return listLength;
	}
	
	public String lookup(String key){
		Node temp = new Node(this.head.key, this.head.value);
		System.out.println("checkpoint 1");
		while (temp.key != null){
			System.out.println("checkpoint 2");
			if (temp.key==key){
				System.out.println("checkpoint 3");
				return temp.key;
			}
			System.out.println("checkpoint 4");
			temp = temp.next;
			System.out.println("checkpoint 5");
		}	
		return null;
	}
	

	

	public void insert(String key, String value){
		if (head==null){
			Node temp= new Node(key, value);
			temp.next= this.head;
			this.head= temp;
			listLength++;
		}
	
		else if (this.lookup(key) != null){
			throw new DuplicateKeyException("cannot insert duplicate keys");
		}
		else{
			Node temp= new Node(key, value);
			temp.next= this.head;
			this.head= temp;
			listLength++;
		}	 
	}
	
	public void delete(String key){
		if (lookup(key) == null){
			throw new KeyNotFoundException("cannot delete a non-existent key");
		}
		if(this.head.key==key){
			this.head=this.head.next;
		}
		
		Node current= new Node(this.head.key, this.head.value);
		Node previous= new Node(this.head.key, this.head.value);
		current=head;
		
		while(current.key!=key){
			previous=current;
			current= current.next;	
		}
		previous.next=current.next;
		listLength--;
	}
	
	public void makeEmpty(){
		head=null;
		listLength=0;
	
	}
	
	public String toString(){
		
		StringBuffer bufferBuilder = new StringBuffer();
		Node temp = this.head;

		for(temp = this.head; temp != null; temp = temp.next){
			bufferBuilder.append(temp.key).append(" ").append(temp.value).append("\n");
		}
		
		return new String(bufferBuilder);
	}
}
	