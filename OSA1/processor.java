//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
public class processor
{
  //attributes:
  timekeeper time;
  process currentprocess; //Is the current process being processed by the cpu.
  Integer processEnter;
  Integer processExit; //Used to calculate the amount of time elapsed if the process is interupted.
  Queue<process> ExitQueue;
  Queue<log> log;
  //constructor:
  public processor(timekeeper newTime, Queue exitQueue,Queue<log> logoutput)
  {
     time = newTime;
     log = logoutput;
    processEnter = null;
    processExit = null;
    currentprocess = null;
    ExitQueue = exitQueue;
  }

  //Methods:
  //Getters:
  //Grabs the status of the cpu. E.g returns true if currently processing, and false if it isn't.
  public boolean getStatus()
  {
    if(currentprocess!=null)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  public Integer getCurrentPriority(){
    return currentprocess.getPriority();
  }

  //Action Methods:
  //Method is used by the dispatcher to interupt the cpu.
  //This updates the process remaining time, this is based on the enter and interupt time of the process.
  public process interuptProcess()
  {
    processExit = time.getTime();
    Integer timeR = currentprocess.getRTime();
    Integer timeprocessed = processExit-processEnter;
    Integer newTimeRemaining = timeR - timeprocessed;
    currentprocess.setRTime(newTimeRemaining);
    process temp = currentprocess;
    currentprocess = null;
    return temp;

  }
  //start processing the process. creates a new log detail for the process and the time it entered the cpu again.
  public void startProcessing(process inputtedprocess){
    log temp = new log(inputtedprocess.getID(),time.getTime());
    log.add(temp);
    processEnter = time.getTime();
    currentprocess = inputtedprocess;
    Integer finishTime = time.getTime() + currentprocess.getRTime();
    time.addQueue(finishTime); //finish time is the time that the process would finish assuming there are no interupts.
  }
  //returns true if the process is been finished processing will then transfer the process to the exit queue.
  public boolean checkIfFinished(){
    processExit = time.getTime();
    Integer timeR = currentprocess.getRTime();
    Integer timeprocessed = processExit-processEnter;
    Integer newTimeRemaining = timeR - timeprocessed;
    //currentprocess.setRTime(newTimeRemaining);
    //return true if the time remaining is 0.
    if(newTimeRemaining==0){
      currentprocess.setExit(time.getTime());
      ExitQueue.add(currentprocess);
      currentprocess = null;
      return true;
    }
    else{
      return false;
    }
  }

}
