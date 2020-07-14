//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
public class schedulerNRR extends scheduler //extends the abstract class scheduler
{
  //variables:
  private Integer initTime;  //initTime is the variable that contains the end time current round robin period.
  //constructor:
  //requires the queue of processes that are going to be computed, the disp time for the dispatcher and the queue that logs will be put into.
  public schedulerNRR(Queue<process> inputQueue, Integer disp, Queue<log> output)
  {
    //creates a new timekeeper;
    time = new timekeeper();
    //creates an array of the size of one that contains readyqueues. This has one as NRR only has one ready queue.
    processlist = new Queue[1]; //
    arrivalQueue = inputQueue; //makes the arrival queue equal to the input queue. This is essentially the spot for processes that haven't arrived yet.
     exitQueue = new Queue<process>(); //creates an exitqueue for processes that have been finished processing from the cpu.
    cpu = new processor(time,exitQueue,output); //creates an initialises a new processor object that has the time, exitqueue and output objects.
    processlist[0] = new Queue<process>(); //initialises a new ready queue into the first position of the array.
    arrivalQueue = inputQueue;
    dispatcher = new dispatcher(time,processlist,disp,cpu); //creates a new dispatcher object with the time, processlist object, dispatcher time, and cpu.



    //adding arrival time of processes to the eventQueue.
    Queue<process> newQueue = new Queue<process>(); //creates a new Queue to put all the values back into after taking out of arrival queue.
    while(arrivalQueue.size()!= 0) //keeps looping until the arrival queue is empty.
    {

      process temp = arrivalQueue.poll(); //removes a proces from the front of the queue.
      Integer atime = temp.getArrival(); // gets the arrival time of the process from it.
      time.addQueue(atime); //adds the arrival time to the time object.
      newQueue.add(temp); // adds the process to the new queue.
    }
    arrivalQueue = newQueue; //makes the arrivalqueue equal the reference of newQueue.
  }

//runScheduling method just means computing and doing all the scheduling till all the processes are processed.
  public Queue<process> runScheduling()
  {
    //loop keeps going till no more time objects. E.g anything that could be done is finished.
    while(true)
    {
      checkArrival(); //checks for the arrival of any new processes that should be put into the ready queue.
      boolean cpuStat = cpu.getStatus(); //checks the status of the cpu e.g true if the cpu has a process.
      if(cpuStat==true){
        cpu.checkIfFinished(); // checks whether the cpu process has finished processing the process. E.g has it finished executing.

      }

      cpuStat = cpu.getStatus(); //gets the status again to see if it finished processing.
      //if it is true then get process from the front of the queue.
      if(cpuStat == false&&processlist[0].size()!=0) //checks whether the ready queue has processes and the cpu is not processing.
      {
        process temp2 = (process)processlist[0].poll();
        initTime = time.getTime()+temp2.getTimeSlice(); //sets the time finished as current time + the current timeslice for that processes.
        time.addQueue(initTime); //adds this finish time to the time object.
        temp2.incrementTimeSlice(); //deincrements the timeslice by 1 until it goes to 3(essentially 2 as it gets reduced when the dispatcher is called).
        dispatcher.dispatch(temp2); //calls the dispatcher for the processes.
      }
      //Checks if the time period is up, the readyqueue is not 0 and that the cpu is processing.
      if(initTime<=time.getTime()&&processlist[0].size()!=0&&cpuStat ==true){
        cpu.checkIfFinished(); //checks if the process has finished.
        process temp2 = (process)processlist[0].poll(); // removes the process from the ready queue.
        initTime = time.getTime()+temp2.getTimeSlice(); //sets the time finished as current time + the current timeslice for that processes.
        time.addQueue(initTime); //adds this finish time to the time object.
        temp2.incrementTimeSlice(); //deincrements the timeslice by 1 until it goes to 3(essentially 2 as it gets reduced when the dispatcher is called).
        dispatcher.dispatch(temp2);//calls the dispatcher for the processes.
      }
      if(time.isEmpty()==false) //simulation is done.
      {
        time.updateTime(); //if still stuff in the time queue then update the time.
      }
      else{
        break; //if the time is empty then break the while loop.
      }
    }
    return exitQueue; //return the queue that finished processes.
  }



    }
