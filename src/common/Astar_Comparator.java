package common;

import java.util.Comparator;

/**
 * Created by dsalas on 9/1/16.
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
