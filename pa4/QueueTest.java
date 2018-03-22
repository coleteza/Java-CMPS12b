//QueueTest.java
//Cole Teza
//#1361038
//PA4
import java.util.Scanner;

public class QueueTest{
  public static void main (String[] args){
    Queue A = new Queue();
	Scanner sc = new Scanner(System.in);
	System.out.println("Welcome to QueueTest.java");
	System.out.println("Enter the char representing the test you would like to perform");
	System.out.println("'A' for All Testing");
	System.out.println("'E' for Exception Testing");
	System.out.println("'I' for Insert Testing");
	System.out.println("'D' for Delete Testing");
	System.out.println("'P' for Peek Testing");
	String command = sc.nextLine();
	
	//Insert Tests
	if (command.equals("I")||command.equals("A")){
		System.out.println("\n Insert Testing \n");
		System.out.println("The Array Empty? "+ A.isEmpty());
		
		for(int j=0; j<=10; j++){
			A.enqueue((int) j);
		}
		
		System.out.println("The Array Empty? "+ A.isEmpty());
		System.out.println("Your Array Legth: "+A.length());
		System.out.println("Your Array: "+A);
		A.dequeueAll();
		
	}
	
	//Delete Tests
	if (command.equals("D")||command.equals("A")){
		System.out.println("\n Delete Testing \n");
		for(int j=0; j<=10; j++){
			A.enqueue((int) j);
		}
		System.out.println("Your Array: "+A);
		System.out.println("The Array Empty? "+ A.isEmpty());
		System.out.println("Your Array: "+A);
		A.dequeue();
		System.out.println("Your Array: "+A);;
		A.dequeue();
		System.out.println("Your Array: "+A);
		A.dequeue();
		System.out.println("Your Array: "+A);
		A.dequeueAll();
	}
		//Peek Tests
	if (command.equals("P")||command.equals("A")){
		System.out.println("\n Peek Testing \n");
		for(int j=5; j<=9; j++){
			A.enqueue((int) j);
		}
		System.out.println("Your Array: "+A);
		System.out.println("The Array Empty? "+ A.isEmpty());
		System.out.println("Peek: "+A.peek());
		System.out.println("Your Array: "+A);;
		A.dequeueAll();
	}
		
	
		//Exceptions Test
		
	if (command.equals("E")||command.equals("A")){
		System.out.println("\n Exception Testing \n");
		System.out.println("DequeueAll Test");
		A.dequeueAll();
		System.out.println("Dequeue Test");
		
		A.dequeue();	
	}
  }
}