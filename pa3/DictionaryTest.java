//DictionaryTest.java
//Cole Teza
//#1361038
//CMPS 12b PA3

public class DictionaryTest {

  public static void main(String[] args) {
    Dictionary A = new Dictionary();
	
      System.out.println(A.isEmpty());
	  
      A.insert("2","C");
      System.out.println(A.isEmpty());
	  
	  
      A.insert("1","L");

      System.out.println(A.size());
	  
      A.insert("6","E");
      A.insert("9","1");
      A.insert("5","3");

      System.out.println(A);
      System.out.println(A.size());
	  
      A.delete("6");
      System.out.println(A.size());
	  
      System.out.println(A);
	  
      System.out.println(A.lookup("5"));
	  
      A.makeEmpty();
      System.out.println(A.size() + " " + A.isEmpty());
  }
}