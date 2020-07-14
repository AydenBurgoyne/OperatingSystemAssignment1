//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1

public class schedulerRR extends scheduler{
  //attributes
  private Integer initTime; //stores the value for when the time slice ends.
  //constructor
  public schedulerRR(Queue<process> inputQueue, Integer disp, Queue<log> output)
  {
    time = new timekeeper(); //new timekeeper object
    processlist = new Queue[1]; //creates a processlist array with size of 1.
    arrivalQueue = inputQueue; //arrivalqueue is given the reference of the inputqueue.
     exitQueue = new Queue<process>(); //creates a queue used to store the finished processes.
    cpu = new processor(time,exitQueue,output); //creates the processer object.
    processlist[0] = new Queue<process>(); //initialises the first position of the array with the queue.
    arrivalQueue = inputQueue; //makes the arrival queue = the input queue.
    dispatcher = new dispatcher(time,processlist,disp,cpu); //creates the dispatcher.



    //adding arrival time of processes to the eventQueue.
    Queue<process> newQueue = new Queue<process>(); //creates the new queue that is used to store proccesses after their arrival times have been put into the time object.
    while(arrivalQueue.size()!= 0) //while loop keeps going till the arrivalqueue size is 0
    {

      process temp = arrivalQueue.poll(); // returns the process from the start of the queue.
      Integer atime = temp.getArrival(); // gets the value for the processes arrival time.
      time.addQueue(atime); //adds the arrival time to the time event queue.
      newQueue.add(temp); //adds the process to the new queue.
    }
    arrivalQueue = newQueue; //makes the arrival queue = to the new queue.
  }

  public Queue<process> runScheduling()
  {
    while(true)
    {
      checkArrival(); //checks for the arrival of any new processes to the ready queue.
      boolean cpuStat = cpu.getStatus(); //checks the status of the cpu.
      if(cpuStat==true){
        cpu.checkIfFinished(); //if the cpu is currently processing then check if the process is ready to finish.

      }

      cpuStat = cpu.getStatus(); //gets the status of the cpu again just incase it finished processing.
      if(cpuStat == false&&processlist[0].size()!=0) // if the cpu is not processing anything and the ready queue has processes.
      {
        process temp2 = (process)processlist[0].poll(); // grab the process from the ready queue.
        initTime = time.getTime()+5; //make the time slice finish time now + 5. The reason it is not 4 is because when the dispatcher is called it will take 1 second so essentially it compensates.
        time.addQueue(initTime); //adds the timeslice finish time to the time queue.
        dispatcher.dispatch(temp2); //calls the dispatch to start dispatching.
      }
      //checks whether the timeslice is up and the ready queue is not empty and that the cpu is currently processing.
      if(initTime<=time.getTime()&&processlist[0].size()!=0&&cpuStat ==true){
        cpu.checkIfFinished(); //checks if the cpu is finished.
        process temp2 = (process)processlist[0].poll(); //grabs a process from the ready queue.
        initTime = time.getTime()+5; //calculates the timeslice finish time.
        time.addQueue(initTime); //adds the timeslice finish time to time event queue.
        dispatcher.dispatch(temp2); //runs the dispatcher.
      }
      //checks if the time event is empty. If it isn't update the time.
      if(time.isEmpty()==false) //simulation is done.
      {
        time.updateTime();
      }
      else{
        break; //break the while loop if there is not more time objects.
      }
    }
    return exitQueue; //return the exit queue. 
  }



    }
