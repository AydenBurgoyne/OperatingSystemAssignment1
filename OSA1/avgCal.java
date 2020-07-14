//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
public class avgCal{
  //Attributes
  private Double turnaround; //turnaround time is the average turnaround time for an algorithm.
  private Double waiting; //average waiting time of an algorithm.
  public avgCal(Double turnaroundint, Double waitingint) //initialises the avgCal object with the values.
  {
    turnaround = turnaroundint;
    waiting = waitingint;
  }
  public Double getTurnaround() //returns avg turnaround.
  {
    return turnaround;
  }
  public Double getWaiting()//returns avg waiting.
  {
    return waiting;
  }
}
