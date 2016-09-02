package common;

/**
 * Created by dsalas on 9/1/16.
 */
public class Astar_Elements
{
  Node node = new Node("0");
  int priority;

  /**
   * Constructor
   * @param node the node being used
   * @param priority its priority
   */
  public Astar_Elements(Node node, int priority)
  {
    this.node = node;
    this.priority = priority;
  }

}
