package common;

import java.util.Comparator;

/**
 * Dominic Salas
 *
 * Class used by our Astar Algo. Used to compare the priorities of our Nodes.
 */
public class Astar_Comparator implements Comparator<Astar_Elements>
{
  @Override
  public int compare(Astar_Elements o1, Astar_Elements o2)
  {
    if(o1.priority < o2.priority) {
      return -1;
    }
    else if(o1.priority > o2.priority) {
      return 1;
    }
    else {
      return 0;
    }
  }
}
