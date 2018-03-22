//Cole Teza
//#1361038
//CMPS 12b PA3

public class Dictionary implements DictionaryInterface{
	//create Node class and object instance
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
// create a marker for the head or front of the list
	private Node head = new Node(null, null);
//keep track of the length of the linked list
	private int listLength;
// create an instance of a linked list	
	public Dictionary(){
		head=null;
		listLength=0;
	}
	
//ADT OPERATIONS
//-------------------------------------------------------------------

//Test to see if the list is empty or not
// return true if empty return false if not

	public boolean isEmpty(){
		if (listLength!=0){
			return false;
		}	
		else{
			return true;
		}
	}
	
//check the size of the array
//returns the size	
	
	public int size(){
		return listLength;
	}
	
//looks thorugh list for a certain key and returns the key if found else return null

	public String lookup(String key){
		Node temp = head;
		while (temp != null){
			if(temp.key.equals(key)){
			return temp.key;
			}
			else {
				temp = temp.next;
			}
		}
		return null;
	}
	
// inserts a new node in the link list at the head posistion (like a stack)
// the new node takes the posistion of head and pushes the head to the next posistion
	public void insert(String key, String value){
		if (head==null){
			Node temp= new Node(key, value);
			temp.next= this.head;
			this.head= temp;
			listLength++;
		}
	// check for duplicates
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
	// non existant command exception
    if( lookup(key) == null){
		throw new KeyNotFoundException("cannot delete non-existent key");
    }
		else {
			//checks for removing the first element if applicable
			if(listLength <= 1){
				Node temp = head;
				head = head.next;
				temp.next = null;
				listLength--;
			} 
			//parses through linked list compounding .next until value is found and the .next 
			//of the previous node is set to the following node to free the value
			else {
				Node temp = head;
				if(temp.key.equals(key)){
					head = temp.next;
					listLength--;
				}
		
				else {
					while(!temp.next.key.equals(key)) {
						temp = temp.next;
					}
					temp.next = temp.next.next;
					listLength--;
				}
			}
		}
    }
	//dissassociate head and length to delete node and send through garbage collection
	public void makeEmpty(){
		head=null;
		listLength=0;
	
	}

	//build strings using string buffer
	public String toString(){
		
		StringBuffer bufferBuilder = new StringBuffer();
		Node temp = this.head;

		for(temp = this.head; temp != null; temp = temp.next){
			bufferBuilder.append(temp.key).append(" ").append(temp.value).append("\n");
		}
		
		return new String(bufferBuilder);
	}
}
	