//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
public class process
{
  //attributes
  private Integer id; //stores the value for id e.g p1 just stores the int.
  private Integer arrivalTime; //stores the value for when the process should arrive in the ready queue.
  private Integer exitTime; //stores the time the process finished processing.
  private Integer timeRemaining; //the time remaining is equal to the amount of time left for executing.
  private Integer exeTime; //stores the value for execution from the start.
  private Integer priority; //has the priority of the process e.g starts at zero increases when needed. This is for FB
  private Integer TimeSlice; //stores the timeslice it will run for. USed for NRR.

  //constructor:
  //constructor just initialises all the values.
  public process(Integer newID, Integer aT, Integer tR)
  {
    id = newID;
    priority = 0;
    arrivalTime = aT;
    timeRemaining = tR;
    exitTime = null;
    exeTime = tR;
    TimeSlice = 5;
  }
  //Methods:
  //Getters:
  public Integer getTimeSlice(){
    return TimeSlice;
  }
  public Integer getPriority()
  {
    return priority;
  }
  public Integer getExeTime() //original execution time.
  {
    return exeTime;
  }
  public Integer getID()
  {
    return id;
  }
  public Integer getArrival()
  {
    return arrivalTime;
  }
  public Integer getExit()
  {
    return exitTime;
  }
  public Integer getRTime()
  {
    return timeRemaining;
  }

  //setters:
  public void setPriority(Integer priorityint)
  {
    priority = priorityint;
  }
  public void setID(Integer setID)
  {
    id=setID;
  }
  public void setArrival(Integer arrival)
  {
    arrivalTime = arrival;
  }
  public void setExit(Integer exit)
  {
    exitTime = exit;
  }
  public void setRTime(Integer RTime)
  {
    timeRemaining = RTime;
  }
  //increments the time slice down by one. 
  public void incrementTimeSlice(){
    if(3<TimeSlice){
      TimeSlice--;
    }
  }

}
