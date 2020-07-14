//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
import java.util.*;
public class timekeeper
{
  //Attributes:
  private PriorityQueue<Integer> eventQueue;
  private Integer currentTime;

//time keeper constructor sets the current time to 0 and the eventQueue priority queue is created.
  public timekeeper()
  {
    currentTime = 0;
    eventQueue = new PriorityQueue<Integer>();
  }

//addQueue is used to add times to the time event priority queue.
  public void addQueue(Integer newTime)
  {
    eventQueue.add(newTime);

  }
  //used to update time.
  public void updateTime()
  {
    currentTime = eventQueue.poll();
  }
  //checks if the queue is empty.
  public boolean isEmpty()
  {
    if(eventQueue.size()==0)
      {
        return true;
      }
      return false;
  }
  //returns the time.
  public Integer getTime()
  {
    return currentTime;
  }
  //allows the current time to be changed by x amount.
  public void addTime(int timeadded)
  {
    currentTime = timeadded+currentTime;
  }

}
