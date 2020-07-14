//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
public class schedulerFCFS extends scheduler
{
  public schedulerFCFS(Queue<process> inputQueue, Integer disp, Queue<log> output)
  {
    processlist = new Queue[1]; //creates an array of size of 1.
    time = new timekeeper(); //creates a timekeeper.
    arrivalQueue = inputQueue;//makes the arrivalqueue to the inputqueue.
     exitQueue = new Queue<process>(); //creates a new queue to store processes that have finished.
    cpu = new processor(time,exitQueue,output);//creates a new processor.
    processlist[0] = new Queue<process>(); //processlist 0 initialised a new process queue.
    arrivalQueue = inputQueue; //arrivalqueue to be equal to input queue.
    dispatcher = new dispatcher(time,processlist,disp,cpu); //dispatcher is created.



    //adding arrival time of processes to the eventQueue.
    Queue<process> newQueue = new Queue<process>();
    while(arrivalQueue.size()!= 0)
    {
      process temp = arrivalQueue.poll();
      Integer atime = temp.getArrival();
      time.addQueue(atime);
      newQueue.add(temp);
    }
    arrivalQueue = newQueue;
  }

  public Queue<process> runScheduling()
  {
    while(true)
    {
    //first part of the cycle is to check if there is any processes arriving.
    checkArrival();
    //then need to check if anything is being processed by the cpu.
    boolean cpuStat = cpu.getStatus();
    if(cpuStat == false&&processlist[0].size()!=0) //if false start processing a process
    {
      process temp2 = (process)processlist[0].poll(); //returning first process from the ready list. should give to dispatcher.
      dispatcher.dispatch(temp2);
    }
    else if(cpuStat == true)
    {
      cpu.checkIfFinished();
      cpuStat = cpu.getStatus();
      if(cpuStat==false&&processlist[0].size()!=0){
        process temp2 = (process)processlist[0].poll(); //returning first process from the ready list. should give to dispatcher.
      dispatcher.dispatch(temp2);
    }
    }
    if(time.isEmpty()==false) //simulation is done.
    {
      time.updateTime();
    }
    else{
      break;
    }
  }
    return exitQueue;

  }
}
