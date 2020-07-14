//Ayden Burgoyne
//c3303000
//Comp2240
//Assignment1
import java.util.*;
public class Queue<E>{
  //attributes:
  LinkedList<E> list;
  //constructor:
  public Queue()
  {
    list = new LinkedList<E>(); //creates a new linkedlist.
  }
  public void add(E e) //adds to the linkedlist.
  {
    list.add(e);
  }
  public E poll() //removes from the list and returns the value.
  {
    return list.poll();
  }
  public int size() //returns the queue size.
  {
    return list.size();
  }
  public ListIterator<E> iterator() //returns a list iterator
  {
    return list.listIterator(0);
  }
}
