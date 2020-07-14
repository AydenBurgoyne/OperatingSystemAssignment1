//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
import java.util.*;
public  class dispatcher //use polymorphism e.g so should be able to have different dispatchers.
{
  //attributes:
  private Queue[] processlist; //array of all the ready queues
  private processor cpu; //cpu object
  private timekeeper time; //timekeeper object.
  private Integer disp; //disp time.
  private Queue<process> exitQueue; //exit queue object
  public dispatcher(timekeeper timeinput, Queue[] input,Integer intdisp, processor cpuinput)
  {
    //initialises the variables to the inputted values.
    time = timeinput;
    processlist = input;
    disp = intdisp;
    cpu = cpuinput;

  }

  //checks if a process has arrived

  //at the end of running, the exit queue is returned.
  //dispatch basically makes the inputted process start processing. This is done through an interupt if it didn't finish executing.
  public void dispatch(process input)
  {

    boolean statusCPU = cpu.getStatus();
    if(statusCPU==true){
      process temp =cpu.interuptProcess();
      processlist[temp.getPriority()].add(temp);
        time.addTime(disp);
      cpu.startProcessing(input);
    }
    else{
        time.addTime(disp);
      cpu.startProcessing(input);
    }
  }
  //interupts the current process and puts it into its relevant ready queue. 
  public void interupt(){
    process temp =cpu.interuptProcess();
    processlist[temp.getPriority()].add(temp);
  }
}
