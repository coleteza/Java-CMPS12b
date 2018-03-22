//-----------------------------------------------------------------------------
// Recursion.java
// Template for CMPS 12B pa1.  Fill in the five recursive functions below.
//-----------------------------------------------------------------------------

//Cole Teza
//cs12b
//#1361038
//PA#1

class Recursion {
   
   // reverseArray1()
   // Places the leftmost n elements of X[] into the rightmost n positions in
  
   // Y[] in reverse order
    static void reverseArray1(int[] X, int n, int[] Y){
     int counter=n-1;				//index for the y array, the output array
	for( int i=0; i<=n-1; i++){		// for loop as i increases to the array length
		int temp_transfer;			// temporary holding variable for transfering to other array
		
		temp_transfer= X[i];		//set holding variable
	   Y[counter]= temp_transfer;  	//set y array to the holding variable
	   counter--; 					//decrease counter to move index on Y array
    }
   }
   
   // reverseArray2()
   // Places the rightmost n elements of X[] into the leftmost n positions in
   // Y[] in reverse order.  
   static void reverseArray2(int[] X, int n, int[] Y){
	   int counter=0;				//index for the y array, the output array
	   int i=0;						//set i for forloop to 0
	for( i=n-1; i>=0; i--){			//for loop as i decreases from the array length to 0
		int temp_transfer;			//temporary holding variable for transfering to other array
		
		temp_transfer= X[i];		//set holding variable
	   Y[counter]= temp_transfer;  	//set y array to the holding variable
	   counter++; 					//increase counter to move index on Y array
    }
   }
   
   // reverseArray3()
   // Reverses the subarray X[i...j].
   static void reverseArray3(int[] X, int i, int j){
	   int iTemp;			//set temporary holding i varaible
	   int jTemp;			//set temporary holding j varaible
	   while(j>=i){			//while loop
		   iTemp= X[i];		//initializing temporary holding variable
		   jTemp= X[j];		//initializing temporary holding variable
		   X[j]=iTemp;		//set value of array to opposite holding variable as set
		   X[i]=jTemp;		//set value of array to opposite holding variable as set
		   i++;				//increase i
		   j--;				//decrease j eventually meeting in the middle on the same value if odd or adjacent if even numbered array
	   }

   }
   
   // maxArrayIndex()
   // returns the index of the largest value in int array X
   static int maxArrayIndex(int[] X, int p, int r){
		int q=(p+r)/2;
		int s1Max;
		int s2Max;
		
		if (p==r){							//if array length is 1 return
			return p;
		}
		
		s1Max= maxArrayIndex(X, p, q);		//set s1max and s2max to the greatest return of the recurrsive call all the way dow the tree
		s2Max=maxArrayIndex(X, q+1, r);
		
		if (X[s1Max]>X[s2Max]){				//compare the larger value and return to previous layer
			return s1Max;
		}
		else{
			return s2Max;
		}
	
    }
   
   // minArrayIndex()
   // returns the index of the smallest value in int array X
   static int minArrayIndex(int[] X, int p, int r){
		int q=(p+r)/2;
		int s1Min;
		int s2Min;
		
		if (p==r){							//if array length is 1 return
			//System.out.println(X[p]);
			return p;
		}
		
		s1Min= minArrayIndex(X, p, q);		//set s1min and s2min to the greatest return of the recurrsive call all the way dow the tree
		s2Min=minArrayIndex(X, q+1, r);
		
		if (X[s1Min]<X[s2Min]){				//compare the smaller value and return to previous layer
			return s1Min;
		}
		else{
			return s2Min;
		}
		
    }
   
   // main()
   public static void main(String[] args){
      
      int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
      int[] B = new int[A.length];
      int[] C = new int[A.length];
      int minIndex = minArrayIndex(A, 0, A.length-1);
      int maxIndex = maxArrayIndex(A, 0, A.length-1);
	  
      for(int x: A) System.out.print(x+" ");
      System.out.println(); 
      
      System.out.println( "minIndex = " + minIndex );  
      System.out.println( "maxIndex = " + maxIndex );  

      reverseArray1(A, A.length, B);
      for(int x: B) System.out.print(x+" ");
      System.out.println();
      
      reverseArray2(A, A.length, C);
      for(int x: C) System.out.print(x+" ");
      System.out.println();
      
      reverseArray3(A, 0, A.length-1);
      for(int x: A) System.out.print(x+" ");
      System.out.println();  
      
   }
   
}
/* Output:
-1 2 6 12 9 2 -5 -2 8 5 7
minIndex = 6
maxIndex = 3
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
*/