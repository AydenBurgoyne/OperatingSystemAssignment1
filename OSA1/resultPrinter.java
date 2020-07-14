//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
//source https://www.geeksforgeeks.org/different-ways-reading-text-file-java/ used this and a java text book didn't copy the code just how to do it. 
import java.util.*;
public class resultPrinter
{
  //attributes:
  private Queue[] outputlist;
  private Queue[] logQueue;
  //constructor:
  public resultPrinter(Queue[] output, Queue[] loglist)
  {
    logQueue = loglist; //makes the logQueue equal to the loglist.
    outputlist = output; //makes the output lsit equal to the inputtted one.
  }
  //print results prints the data like the example outputs.
  public void printResults()
  {
    process temppro;
    orderResults(); //orders the processes in the correct way.
    Queue<avgCal> averages = new Queue<avgCal>(); //is a queue that stores avgCal objects which store the average for turnaround and wait time for each algorthim.
    //goes through all the results queue
    for(int i = 0;i<outputlist.length;i++)
    {
      Queue<process> temp = outputlist[i]; //makes the temp process queue equal to the relevant position in teh array.
      Queue<log> templog = logQueue[i]; //grabs the queue from the array.
      log Templogobj; //declares the temp forlog.

      //makes the system println for the respective queue.
      if(i==0){
        System.out.println("FCFS:");
      }
      else if(i==1)
      {
        System.out.println("RR:");
      }
      else if(i==2)
      {
        System.out.println("FB (constant):");
      }
      else if(i==3)
      {
        System.out.println("NRR:");
      }
      //first section;
      //writes the first section of the output e.g what time period the process is put into the processor.
      while(templog.size()!=0){
        Templogobj = templog.poll();
      System.out.println("T"+Templogobj.getTime()+": p"+Templogobj.getID());
      }
      //second section:
      System.out.println("");
      System.out.println("Process Turnaround Time Waiting Time");
      Double turnaroundAVG = 0.0;
      Double waitingAVG= 0.0;
      int tempSize = temp.size();
      while(temp.size()!=0)
      {
        temppro = temp.poll();
        int turnaround = temppro.getExit()-temppro.getArrival();
        int waiting = turnaround-temppro.getExeTime();
        turnaroundAVG += turnaround;
        waitingAVG += waiting;
        System.out.println(String.format("%-8s","p"+Integer.toString(temppro.getID()))+String.format("%-11s",Integer.toString(turnaround))+String.format("%-10s",Integer.toString(waiting)));

      }
      turnaroundAVG = (turnaroundAVG/tempSize);
      waitingAVG = (waitingAVG/tempSize);
      avgCal logTemp = new avgCal(turnaroundAVG,waitingAVG);
      averages.add(logTemp);
      System.out.println("");

    }
    //prints out the summary of turnaround and waiting times for each algorithim
    System.out.println("Summary");
    System.out.println("Algorithm      Average Turnaround Time  Average Waiting Time");
    String[] alg = {"FCFS","RR","FB (constant)","NRR"};
    int COUNT = 0;
    while(averages.size()!=0){
      avgCal avgCalTemp = averages.poll();
      String avg = alg[COUNT];
      String turn = String.format("%.2f",avgCalTemp.getTurnaround());
      String wait = String.format("%.2f",avgCalTemp.getWaiting());
      System.out.println(String.format("%-15s",avg)+String.format("%-25s",turn)+String.format("%1s",wait));
      COUNT++;
    }

  }
  public void orderResults()
  {
    for(int i = 0; i<outputlist.length;i++){
       Queue<process> temp = outputlist[i];
       Queue<process> temp2 = new Queue<process>();
       int count = 0;
       process tempProcess;
       while(temp.size()!=0)
       {
         count++;
         ListIterator<process> it = temp.iterator();
            while(it.hasNext())
            {
              tempProcess = it.next();
              if(tempProcess.getID()==count){
                temp2.add(tempProcess);
                it.remove();
                break;
              }
            }
       }
       outputlist[i] = temp2; //puts the orderedlist back into the array.
  }
  }
}
