//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
public class schedulerFB extends scheduler
{
    //variables
    private Integer initTime; //timeslice end period.
    public schedulerFB(Queue<process> inputQueue, Integer disp, Queue<log> output)
    {
      initTime = 0;
      time = new timekeeper(); //creates the timekeeper.
      processlist = new Queue[6]; // creates the array with 6 spots for each level of ready queue.
      arrivalQueue = inputQueue; //arrival queue is equal to inputqueue.
       exitQueue = new Queue<process>(); //exitqueue is created.
      cpu = new processor(time,exitQueue,output); //cpu processor is initialised.
      for(int i = 0;i<6;i++){  //for loop for 6 times.
        processlist[i] = new Queue<process>(); //creates a new process queue in each spot of the array.
      }

      arrivalQueue = inputQueue; //arrival queue is equal to input queue.
      dispatcher = new dispatcher(time,processlist,disp,cpu); //creates the dispatcher object.



      //adding arrival time of processes to the eventQueue
      //adds all the arrival times to the timekeeper object.
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

    public Queue<process> runScheduling(){
        while(true)
        {
          checkArrival(); //checks to see if the proceses are ready to move to the arrival queue.
            boolean cpuStat = cpu.getStatus(); //gets the cpu status.
          if(initTime<=time.getTime()&&cpuStat){ //if the time period is up and the cpu is processing a process.
            dispatcher.interupt(); //interupts the processes and puts into respective queue.
          }
          int priority = 0; //
          cpuStat = cpu.getStatus();
          if(cpuStat==true){
            priority = cpu.getCurrentPriority();
            cpu.checkIfFinished();//checks if the current process has finished processing.

          }
          cpuStat = cpu.getStatus(); //gets the cpu status to see if it changed.
          //0------------------------------------------------------------------------
        if(processlist[0].size()!=0){ //checks if the processlist 0 was not 0.
          if(cpuStat == false&&processlist[0].size()!=0) // checks if the cpu is not running and the processlist 0 is not 0 e.g readyqueues.
          {
            process temp2 = (process)processlist[0].poll(); // grabs the process from the first ready queue 0
            initTime = time.getTime()+5; //makes the timeslice = the current time + 5;
            time.addQueue(initTime); //adding the end timeslice time to the time queue.
            temp2.setPriority(1); //set the priority of the process to 1.
            dispatcher.dispatch(temp2); //dispatching temp2.
          }
          //checks if the time period of the time slice is finished. and the ready queue 0 is not empty and that the cpu is still processing.
          if(initTime<=time.getTime()&&processlist[0].size()!=0&&cpuStat ==true){
            cpu.checkIfFinished(); //checks if the cpu has finished processing.
            process temp2 = (process)processlist[0].poll(); //grabs the process from the ready queue0.
            initTime = time.getTime()+5; //makes the timeslice = the current time + 5;
            time.addQueue(initTime);//adding the end timeslice time to the time queue.
            temp2.setPriority(1);//set the priority of the process to 1.
            dispatcher.dispatch(temp2);//dispatching temp2.
          }
        }

        //all the below sections are the same as above except they check for different priority queues.
          //1------------------------------------------------------------------------
        else if(processlist[1].size()!=0){
          if(cpuStat == false&&processlist[1].size()!=0)
          {
            process temp2 = (process)processlist[1].poll();
            initTime = time.getTime()+5;
            time.addQueue(initTime);
            temp2.setPriority(2);
            dispatcher.dispatch(temp2);
          }
          if(initTime<=time.getTime()&&processlist[1].size()!=0&&cpuStat ==true){
            cpu.checkIfFinished();
            process temp2 = (process)processlist[1].poll();
            initTime = time.getTime()+5;
            time.addQueue(initTime);
            temp2.setPriority(2);
            dispatcher.dispatch(temp2);
          }
        }
          //2------------------------------------------------------------------------
        else if(processlist[2].size()!=0){
          if(cpuStat == false&&processlist[2].size()!=0)
          {
            process temp2 = (process)processlist[2].poll();
            initTime = time.getTime()+5;
            time.addQueue(initTime);
            temp2.setPriority(3);
            dispatcher.dispatch(temp2);
          }
          if(initTime<=time.getTime()&&processlist[2].size()!=0&&cpuStat ==true){
            cpu.checkIfFinished();
            process temp2 = (process)processlist[2].poll();
            initTime = time.getTime()+5;
            time.addQueue(initTime);
            temp2.setPriority(3);
            dispatcher.dispatch(temp2);
          }
        }
          //3------------------------------------------------------------------------
        else if(processlist[3].size()!=0){
          if(cpuStat == false&&processlist[3].size()!=0)
          {
            process temp2 = (process)processlist[3].poll();
            initTime = time.getTime()+5;
            time.addQueue(initTime);
            temp2.setPriority(4);
            dispatcher.dispatch(temp2);
          }
          if(initTime<=time.getTime()&&processlist[3].size()!=0&&cpuStat ==true){
            cpu.checkIfFinished();
            process temp2 = (process)processlist[3].poll();
            initTime = time.getTime()+5;
            time.addQueue(initTime);
            temp2.setPriority(4);
            dispatcher.dispatch(temp2);
          }
        }
          //4------------------------------------------------------------------------
        else if(processlist[4].size()!=0){
          if(cpuStat == false&&processlist[4].size()!=0)
          {
            process temp2 = (process)processlist[4].poll();
            initTime = time.getTime()+5;
            time.addQueue(initTime);
            temp2.setPriority(5);
            dispatcher.dispatch(temp2);
          }
          if(initTime<=time.getTime()&&processlist[4].size()!=0&&cpuStat ==true){
            cpu.checkIfFinished();
            process temp2 = (process)processlist[4].poll();
            initTime = time.getTime()+5;
            time.addQueue(initTime);
            temp2.setPriority(5);
            dispatcher.dispatch(temp2);
          }
        }
          //5------------------------------------------------------------------------
          else if(processlist[5].size()!=0){
            if(cpuStat == false&&processlist[5].size()!=0)
            {
              process temp2 = (process)processlist[5].poll();
              initTime = time.getTime()+5;
              time.addQueue(initTime);
              temp2.setPriority(5);
              dispatcher.dispatch(temp2);
            }
            if(initTime<=time.getTime()&&processlist[5].size()!=0&&cpuStat ==true){
              cpu.checkIfFinished();
              process temp2 = (process)processlist[5].poll();
              initTime = time.getTime()+5;
              time.addQueue(initTime);
              temp2.setPriority(5);
              dispatcher.dispatch(temp2);
            }
          }


          //checks if the time event queue is not empty and if it isn't update the time.
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
