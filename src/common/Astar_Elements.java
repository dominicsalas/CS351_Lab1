package common;

/**
 * Dominic Salas
 *
 * Class used by our Astar Algo when finding the path. Probably could've used
 * Node, however, this keeps things a little simpler.
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
