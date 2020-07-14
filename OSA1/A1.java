//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
import java.util.*;
public class A1
{
  public static void main(String [] args)
  {
    //log queues:
    Queue<log> loginput = new Queue<log>();
    Queue<log> loginput2 = new Queue<log>();
    Queue<log> loginput3 = new Queue<log>();
    Queue<log> loginput4 = new Queue<log>();

    //arrays
    Queue[] loglist = new Queue[4];
    Queue[] output = new Queue[4];

    //puttting loginput into the array
    loglist[0] = loginput;
    loglist[1] = loginput2;
    loglist[2] = loginput3;
    loglist[3] = loginput4;

    //initialising filereader
    filereader read = new filereader(args[0]);
    Queue<process> input = read.readFile();
    Integer disp = read.returnDisp();

    //initialising schedulers
    scheduler schedulerFCFC = new schedulerFCFS(input,disp,loginput);
    input = read.readFile();
    scheduler schedulerRR = new schedulerRR(input,disp,loginput2);
    input = read.readFile();
    scheduler schedulerFB = new schedulerFB(input,disp,loginput3);
    input = read.readFile();
    scheduler schedulerNRR = new schedulerNRR(input,disp,loginput4);

    //running the scheduler for each one
    output[3] =  schedulerNRR.runScheduling();
    output[2] = schedulerFB.runScheduling();
    output[1] = schedulerRR.runScheduling();
    output[0] = schedulerFCFC.runScheduling();

    //initialsing and printing result
    resultPrinter print = new resultPrinter(output,loglist);
    print.printResults();
  }
}
