//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
import java.util.*;
import java.io.*;
public class filereader
{
  //attributes:
  private String fName;
  private Integer disp;

  //constructor:
  //initialises the disp and fName.
  public filereader(String fileName)
  {
    fName = fileName;
    disp = null;
  }

  //methods:
  //method is responsible for reading the file and returning a queue of processes from the data.
  public Queue<process> readFile()
  {
    //sets integers that store data for the current process.
    Integer arrivalTime = 0;
    Integer exeTime =0;
    String next;
    Integer idout;
    int count = 0;
    Queue<process> ProcessQueue = new Queue<process>();
    //is used to catch exceptions for an incorrect file name.
    try
    {
      File file = new File(fName); //file object is created from the file name.
      Scanner sc = new Scanner(file); //scanner object is used to scan the file object.
    while(sc.hasNext()) //keeps going while the text document still has more tokens.
    {
      next = sc.next(); //grabs the next string.

      if(next.equals("DISP:")) //checks if the token equals disp
      {
        next = sc.next();
        disp = Integer.parseInt(next); // enters the value of disp.
      }
      //checks if the token equals ID:
      else if(next.equals("ID:"))
      {
        String id = sc.next(); // the next id is equal to the next token.
        String result = id.substring(1); //grabs the second char from the string e.g gets rid of the p.
        idout = Integer.parseInt(result);
        next = sc.next();
        //checks if the token equals Arrive:
        if(next.equals("Arrive:"))
        {
            arrivalTime = sc.nextInt(); //makes the arrivaltime equal to the next int.
        }
        next = sc.next();
        //checks if the token equals ExecSize:
        if(next.equals("ExecSize:"))
        {
           exeTime = sc.nextInt(); //makes exeTime equal to the next int from scanner.
        }
        process newProcess = new process(idout, arrivalTime, exeTime); //creates a new process with the values read.
        ProcessQueue.add(newProcess); //adds the values to the queue.
      }

    }
    }
    catch(Exception e)
    {
      System.out.println(e);
      System.exit(0);
    }
    return ProcessQueue; //returns the array of processes.
  }
  public Integer returnDisp(){ //returns the value of the disp;
    return disp;
  }
}
