//Simulation.java
//Cole Teza
//#1361038
//PA4

import java.io.*;
import java.util.Scanner;

public class Simulation{
	  public static void main(String[] args) throws IOException{
		
		Scanner fileInput = null;
		PrintWriter report = null;
		PrintWriter trace = null;
		Queue qStore = new Queue();
		
		int n;  	
		int m;  	// used for number of jobs and not changed
		int time; 	//time variable... obviously
		
		//Check for 3 Arguments (arrival, durration, finish);
		if (args.length+1 <= 3){
		  System.err.println("Usage: Incorrect Arguments (arrival, durration, finish)");
		  System.exit(1);
		}
		
		//Open files for reading and writting 
		fileInput = new Scanner(new File(args[0]));
		report = new PrintWriter( new FileWriter(args[1]+".rpt"));
		trace = new PrintWriter( new FileWriter(args[2]+ ".trc"));
	
		//Read in m jobs from input file
		m= numOfJobs(fileInput)+1;
	
		// while jobs still exist continue adding to queue
		while(fileInput.hasNext()){
				qStore.enqueue((Job)getJob(fileInput));

		}
		System.out.println("Made it to like 37");
		trace.println("Trace File: "+ args[2]+".trc");
		trace.println(m + " Jobs:");
		trace.println(qStore+"\n");
		
		// Declare and initialize an array of n processor Queues and any necessary storage Queues
		//for loop through all the following processor checks then increment a processor
		  for( n=1; n<m-1; n++){
			  Queue[] simulate= new Queue[n+1];
			  time = 0;
			  for(int x=0; x<n+1; x++){
				  simulate[x]= new Queue();
			  }
			 
			//PRINT processor notifications to file
			//Check if single processor or multiple
			trace.println("*****************************");
			trace.println(n + " processor:");
			trace.println("*****************************");
			
			  
			simulate[0]=qStore;
			
			//while unprocessed jobs remain  
			while( ((Job)simulate[0].peek()).getFinish() == -1  || simulate[0].length()!=m){
				
				//check storage arrival time if empty
				//print to the Trace and update trace file for what is being run in the processor 
				//get arrival time to solve for time 
				if(time==0){
					tracePrinter(trace,simulate,n,time);
					//Start time counting, reset time value using same variable used for if statment
							time= ((Job)simulate[0].peek()).getArrival();
							simulate[1].enqueue(simulate[0].dequeue());
							Job Jtemp= (Job)simulate[1].peek();
							//End time counting
							Jtemp.computeFinishTime(time);
							System.out.println("This -2");
				}
				
				// if the value at 0 in the queue array reutrns does not -1 via the casting through Job 
				//else if arrival time is not -1 or 0 (as checked previously)
				else if(((Job)simulate[0].peek()).getFinish()!=-1){
					
					int indx= getIndex(simulate);
					
					time=((Job)simulate[indx].peek()).getFinish();
					simulate[0].enqueue(simulate[indx].dequeue());
					tracePrinter(trace, simulate, n ,time);
					System.out.println("This -1");
					time=-6;
					break;
				}
				if (time==-6){
					break;
				}
				
				//if arrival time is not -1 or not given then...
				else{
					int temp= getIndex(simulate);
					System.out.println("TEMP= " +temp);
					//print progress
					tracePrinter(trace, simulate,n,time);
					System.out.println("This 1");
					//if the youre on the last job
					if( simulate[temp].length() == 0){
					   time = ((Job)simulate[0].peek()).getArrival();
					   simulate[temp].enqueue(simulate[0].dequeue());
					   Job Jtemp = (Job)simulate[temp].peek();
					   Jtemp.computeFinishTime(time);
					  
					   tracePrinter(trace,simulate,n,time);
					   temp = getIndex(simulate)-1;
					   time = ((Job)simulate[temp].peek()).getFinish();
					   simulate[0].enqueue(simulate[temp].dequeue());
					   tracePrinter(trace,simulate,n,time);
					   
					  
					   temp = getIndex(simulate);
					   time = ((Job)simulate[0].peek()).getArrival();
					   simulate[temp].enqueue(simulate[0].dequeue());
					   Jtemp = (Job)simulate[temp].peek();
					   Jtemp.computeFinishTime(time);
					   tracePrinter(trace,simulate,n,time);
					   
					   time = ((Job)simulate[temp+1].peek()).getFinish();
					   simulate[0].enqueue(simulate[temp+1].dequeue());
					   tracePrinter(trace,simulate,n,time);
				   
					   temp = getIndex(simulate);
					   time = ((Job)simulate[temp-1].peek()).getFinish();
					   simulate[0].enqueue(simulate[temp-1].dequeue());
					   tracePrinter(trace,simulate,n,time);
					   Jtemp = (Job)simulate[temp-1].peek();
					   Jtemp.computeFinishTime(time);
					   System.out.println("This 2");
					   
						break;
					}
					
					//Check if arrival time is less than or equal to finish time
					else if( ((Job)simulate[0].peek()).getArrival() <= ((Job)simulate[temp].peek()).getFinish() ){
						time = ((Job)simulate[0].peek()).getArrival();
						simulate[temp].enqueue(simulate[0].dequeue());
						//print progress
						tracePrinter(trace,simulate,n,time);
						
						time = ((Job)simulate[temp].peek()).getFinish();
						simulate[0].enqueue(simulate[temp].dequeue());
						Job Jtemp = (Job)simulate[temp].peek();
						Jtemp.computeFinishTime(time);
						//print progress
						tracePrinter(trace,simulate,n,time);
						System.out.println("This 3");
					}
					else{
						Job Jtemp = (Job)simulate[temp].peek();
						Jtemp.computeFinishTime(time);
						//print progress
						tracePrinter(trace,simulate,n,time);
						
						time = ((Job)simulate[temp].peek()).getFinish();
						simulate[0].enqueue(simulate[temp].dequeue());
						//print progress
						tracePrinter(trace,simulate,n,time);
						System.out.println("This 4");
            
					}
				//Close else	
				}
			//Close While Loop
			}
		
			
			//compute the total wait, maximum wait, and average wait for all Jobs, then reset finish times
			//print to report file the results of the processor
			//(i.e.) totalWait, maxWait, and averageWait for how ever many processors were used
			//if this is the first processor print out the header
			 if(n==1){
        report.println("Report file: "+ args[1] + ".rpt");
        report.println( m + " Jobs:");
        report.println(simulate[0] +"\n");
        report.println("************************************************");
      }
	  Queue qHold = new Queue();
	  int maxWait=0;
      int mTemp=0;
      float averageWait=0;
      int totalWait=0;
      
      while( simulate[0].length() != 0 ){
        mTemp = ((Job)simulate[0].peek()).getWaitTime();
        if( maxWait < mTemp){
          maxWait = mTemp;
        }
        totalWait += ((Job)simulate[0].peek()).getWaitTime();
        qHold.enqueue((Job)simulate[0].dequeue());
      }
      averageWait =(float)totalWait/m;
      report.println( n + " processor: totalWait="+ totalWait+ " maxWait="+maxWait+" averageWait="+averageWait);
      
      while(qHold.length() != 0){
        ((Job)qHold.peek()).resetFinishTime();
        qStore.enqueue((Job)qHold.dequeue());
      }
      trace.println();
      
    }
		//Finalize files
		fileInput.close();
		report.close();
		trace.close();
	}
	//METHODS:-------------------------------------------------------------------------------------------------------------
	// GET JOB , GET INDEX , TRACEPRINTER , NUMOFJOBS
	
	  
  
  
  public static void tracePrinter(PrintWriter trace, Queue[] qtp, int n, int time){
    trace.println("time = " +time);
	 System.out.println("time = " +time);
    for(int i =0; i<n+1;i++){
      trace.println(i+": "+ qtp[i]);
	  System.out.println(i+": "+ qtp[i]);

    }
  }
  
  public static int getIndex(Queue[] qIndex){
    int index =0;
    if( ((Job)qIndex[index].peek()).getFinish() == -1){
        index = 1;
		
      }
    for(int i =1; i<qIndex.length;i++){
      if(qIndex[i].length() < qIndex[index].length()){
        if(qIndex[i].length() == qIndex[index].length()){
         
        }
        else{
          index = i;
		 
        }
      }
      else if( qIndex[i].length() < qIndex[index].length() && ((Job)qIndex[index].peek()).getFinish() !=-1){
        
      }
	  
    }
    return index;
  }
    public static Job getJob(Scanner input){
	//look for space to break up values coming in on the input file
    String[] inVal = input.nextLine().split(" ");
	
    int arrival = Integer.parseInt(inVal[0]);
    int durration = Integer.parseInt(inVal[1]);
	
    return new Job(arrival,durration);
  }
 
  public static int numOfJobs(Scanner input){
    int x;
    String jVal = input.nextLine();
    x = Integer.parseInt(jVal);
    return x;
  }
  

}
