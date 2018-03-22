//COLE TEZA
//#1361038
//CMPS12B PA2
//04/12/2016

import java.util.Scanner;
import java.io.*;

public class Search{
	
	// **SET-UP**
	public static void main(String[] args) throws IOException{
		Scanner dataIn = null;
		int nLines = 0;
		int[] lnum = null;
		String current = null;
		String[] keyVal = null;
   
    
	//Check if all arguments were entered 
    if(args.length < 2){
		System.err.println("ERROR: Incomplete Entry, must provide filename and desired targetWord(s) ");
		System.exit(1);
    }
    
    //Loop through file increasing counter for number of lines in the file leading to array length for merge sort
    dataIn = new Scanner(new File(args[0]));
	
    while( dataIn.hasNextLine() ){
		nLines++;
		current = dataIn.nextLine();
    }
    
    //set inital values 
	lnum = new int[nLines];
    keyVal = new String[nLines];
	
	//Scans the file again
    dataIn = new Scanner(new File(args[0]));
    
    //parses through the array
    for(int i=1; i<=lnum.length; i++){
		lnum[i-1] = i;
    }
    //parses through file and moves words to String
    for(int i =0; dataIn.hasNextLine(); i++){
		current = dataIn.nextLine();
		keyVal[i] = current;
    }
    //Run Merge Sort to get String alphabetical before Binary Search
    mergeSort(keyVal,lnum, 0, keyVal.length-1);
	
    //Prints out where the entered targetWord was found (if found)
    for(int i=1; i<args.length; i++){
		System.out.println( binarySearch(keyVal, lnum, 0, keyVal.length-1, args[i]));
    }
    
	//exit program
	dataIn.close();
    
  }
 
  //**BINARY SEARCH**
  //can not be done until merge sort sorts the array alphabetically, then the tree can begin to form and the search can begin
  public static String binarySearch(String[] phraseArray, int[] lnum, int x, int z, String targetWord){
	//initialize y variable for temporary use
	int y;
    if( x == z ){
		return targetWord + " not found";
    }
    else{
		y = (x+z)/2;
		if( phraseArray[y].compareTo(targetWord) == 0){
			//if target word return word and location to print command for user
			return targetWord + " found on current " + lnum[y];
		}
		else if( phraseArray[y].compareTo(targetWord)<0 ) {
			// recursion if not target word
			return binarySearch(phraseArray, lnum, x, y, targetWord);
		}
		else{ 
			//recursion if not target word
			return binarySearch(phraseArray, lnum, y+1, z, targetWord);
		}
	}
    }

  
  //**MERGE SORT**
  
  //Splitting
  
  static void mergeSort(String[] phraseArray, int[] lnum, int x, int z){
	//initialize y variable for temporary use for middle of array split value
    int y;
	
    if(x<z){
		//find the middle 
		y = (x+z)/2;
		//recurrsivly call it like done in PA1
		mergeSort(phraseArray, lnum, x, y);
		mergeSort(phraseArray, lnum, y+1, z); 
		// after recursion and entire tree is constructed call merge to sort on the way back up the tree using compareTo()
		merge(phraseArray, lnum, x, y, z);
    }
  }
  
   //Merging Up
   
  static void merge(String[] phraseArray, int[] lVal, int p, int q, int r){
		//Find Center and set bounds
		int b1 = q-p+1; //bound 1
		int b2 = r-q;   //bound 2
		//create subarrays to form a tree creating left and right
		String[] left = new String[b1];
		String[] right = new String[b2];
		//specific integer values for location to assist the string values
		int[] leftVal = new int[b1];
		int[] rightVal = new int[b2];
		//temporary use variables
		int i, j, k;
		// use forloop to parse through the words starting at 0 incrementing the "phraseArray"
		for(i=0; i<b1; i++){
			left[i] = phraseArray[p+i];
			leftVal[i] = lVal[p+i];
		}
		//do the same as the above for loop but for the oposing branch of the tree
		for(j=0; j<b2; j++){
			right[j] = phraseArray[q+j+1];
			rightVal[j] = lVal[q+j+1];
		}
		// reset temporary variables for next loop keep 'k' value
		j = 0;
		i = 0;
		
		// for statment runs setting k equal to lnum sent iun through p from mergesort() 
		for(k=p; k<=r; k++){
			
			if( i<b1 && j<b2){
				// compare left value to right value on tree to decide order before moving up to the larger branch
				if( left[i].compareTo(right[j])>0 ){
					// if greater than 0 AKA greater than alphabetically
					phraseArray[k] = left[i];
					lVal[k] = leftVal[i]; 
					//incrememt
					i++;
				}
				// else keep parsing and recurring until value is found and placed in appropriate list value 
			else{
				
				lVal[k] = rightVal[j];
				phraseArray[k] = right[j];
				//increment
				j++;
			} 
		}
		
		else if( i<b1){
			
			lVal[k] = leftVal[i];
			phraseArray[k] = left[i];
			//increment
			i++;
		}
		
		else{
			
			lVal[k] = rightVal[j];
			phraseArray[k] = right[j];
			//increment
			j++;
		} 
    }
  }
}