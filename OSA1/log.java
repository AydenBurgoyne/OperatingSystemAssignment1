//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
//purpose is to store info about time when the process starts getting processed by the cpu.
public class log
{
  //Attributes
  private Integer id; //stores the id of the processes.
  private Integer time; //stores the time the process enters the cpu.
  public log(Integer pID, Integer pTime) //initialises the log with the values.
  {
    id = pID;
    time = pTime;
  }
  //getters
  public Integer getID()
  {
    return id;
  }
  public Integer getTime()
  {
    return time;
  }
}
