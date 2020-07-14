//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
//scheduler is an abstract class that all the algortithm versions implement.
import java.util.*;
public abstract class scheduler {
  //attributes:
  protected Queue[] processlist; //stores the ready queues.
  protected Queue<process> arrivalQueue; //arrival queue stores the processes before they're ready for the ready queue.
  protected timekeeper time; // timekeeer object
  protected processor cpu; //cpu
  protected dispatcher dispatcher; //dispatcher object.
  protected Queue<process> exitQueue; //queue that stores finished objects.



  //checks if a process has arrived
  public void checkArrival()
  {
    int arrivalTime; //int that stores the arrival time from the processes so that it can be checked if they're ready.
    Queue<process> timeperiod = new Queue<process>();
    ListIterator<process> it = arrivalQueue.iterator(); //Gets an iterator for the arrivalqueue.
    while(it.hasNext()) //keeps going till the entire arrival queue has been iterated.
    {
        process temp = it.next(); //grabs the next process.
        arrivalTime = temp.getArrival(); //grabs arrival time from the process.
        if(arrivalTime<=time.getTime()){ //checks if the arrival time is smaller than the current time e.g should've arrived already.
          timeperiod.add(temp);

          //processlist[0].add(temp); //adds the process to the ready queue.
          it.remove(); //removes the added process.
        }

    }
    //sorting out which process should go first.
    while(timeperiod.size()!=0)
    {
      ListIterator<process> tp = timeperiod.iterator(); // iterator is here to prevent modification errors e.g where fx messes with it. 
      process low = tp.next();
      while(tp.hasNext()){
        process temp = tp.next();
        if(temp.getID()<low.getID()){
          low = temp;
        }

      }
      //finding correct process adding it to ready queue then deleting.
    while(true){
      ListIterator<process> fx = timeperiod.iterator();
      process temp = fx.next();
      if(temp.getID()==low.getID()){
        processlist[0].add(temp);
        fx.remove();
        break;
        }

      }
  }
  }
  //abstract for runScheduling.
    public abstract Queue<process> runScheduling();


}
