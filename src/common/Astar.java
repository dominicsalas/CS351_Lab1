package common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Dominic Salas
 *
 * The infamous Astar Algo that everyone has been waiting for. I basically
 * replicated the site that Joel gave on the site. It's a little fancy, but
 * it was basically given to us.
 */
public class Astar
{
  /**
   * Constructor for calling the Astar class
   * @param startNode our Starting Node
   * @param goalNode our Goal Node
   */
  public Astar(Node startNode, Node goalNode)
  {
    ArrayList<Node> path = new ArrayList<>();
    int priority;
    int new_cost = 0;
    Astar_Comparator comparator = new Astar_Comparator();
    PriorityQueue<Astar_Elements> frontier = new PriorityQueue<>(20, comparator);
    frontier.add(new Astar_Elements(startNode, 0));
    HashMap<Node, Node> came_from = new HashMap<>();
    HashMap<Node, Integer> cost_so_far = new HashMap<>();
    Node current;
    String lastVisited = null;

    current = frontier.peek().node;
    cost_so_far.put(current, new_cost);

    while (!frontier.isEmpty())
    {
      current = frontier.poll().node;

      //override equals if failing
      if (current.equals(goalNode))
      {
        lastVisited = current.name;
        break;
      }

      //Loop for algorithm
      for (Node next : current.getNeighbors())
      {
        new_cost = cost_so_far.get(current) + 1;
        if (cost_so_far.containsKey(next) == false ||
                (new_cost < cost_so_far.get(next)))
        {
          cost_so_far.put(next, new_cost);
          priority = new_cost + levenshteinDistance(goalNode.name, next.name);
          frontier.add(new Astar_Elements(next, priority));
          came_from.put(next, current);
        }
      }
    }

    if (lastVisited == null)
    {
      System.out.println("No Path exists!");
    }
    else
    {

      new_cost = 0;
      while (current != startNode)
      {
        new_cost += 1;
        current = came_from.get(current);
        path.add(current);
      }

      Collections.reverse(path);

      for (Node p : path)
      {
        System.out.print(p.name);
        System.out.print(" ");
      }
      System.out.println(goalNode.name);
    }
  }

  // Taken from wikipedia - https://en.wikipedia.org/wiki/Levenshtein_distance

  /**
   * Our Heuristic used by the Astar Algo.
   * Taken from wikipedia - https://en.wikipedia.org/wiki/Levenshtein_distance.
   * @param s starting string
   * @param t goal string.
   * @return
   */
  private int levenshteinDistance(String s, String t)
  {
    // degenerate cases
    if (s.equals(t)) return 0;
    if (s.length() == 0) return t.length();
    if (t.length() == 0) return s.length();

    // create two work vectors of integer distances
    int[] v0 = new int[t.length() + 1];
    int[] v1 = new int[t.length() + 1];

    // initialize v0 (the previous row of distances)
    // this row is A[0][i]: edit distance for an empty s
    // the distance is just the number of characters to delete from t
    for (int i = 0; i < v0.length; i++)
      v0[i] = i;

    for (int i = 0; i < s.length(); i++)
    {
      // calculate v1 (current row distances) from the previous row v0

      // first element of v1 is A[i+1][0]
      //   edit distance is delete (i+1) chars from s to match empty t
      v1[0] = i + 1;

      // use formula to fill in the rest of the row
      for (int j = 0; j < t.length(); j++)
      {
        int cost = (s.charAt(i) == t.charAt(j)) ? 0 : 1;
        int[] array = { v1[j] + 1, v0[j + 1] + 1, v0[j] + cost };
        v1[j + 1] = minimum(array);
      }

      // copy v1 (current row) to v0 (previous row) for next iteration
      for (int j = 0; j < v0.length; j++)
        v0[j] = v1[j];
    }

    return v1[t.length()];
  }

  /**
   * Required by the LevenshteinDistance
   * @param array
   * @return
   */
  private int minimum(int[] array)
  {
    int min = Integer.MAX_VALUE;
    for (Integer num : array)
    {
      if (num < min) min = num;
    }
    return min;
  }
}
