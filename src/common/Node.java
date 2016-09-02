package common;

import java.util.ArrayList;

/**
 * Dominic Salas
 *
 * This is the Node class that is used for storing the Node info in the Graph.
 */
public class Node
{
  String name;
  ArrayList<Node> neighbors;
  public int length;

  /**
   * Constructor for creating new Nodes.
   * @param name used to create the constructor
   */
  public Node(String name)
  {
    this.name = name;
    this.neighbors = new ArrayList<>();
    this.length = name.length();
  }

  /**
   * Method for adding a neighbor to the Node
   * @param neighbor Node
   */
  public void addNeighbor(Node neighbor)
  {
    this.neighbors.add(neighbor);
  }

  /**
   * Getter for grabbing the Node name
   * @return Node name
   */
  public String getName()
  {
    return this.name;
  }

  /**
   * Getter for grabbing all of the neighbors for the Node
   * @return List of Node's neighbors
   */
  public ArrayList<Node> getNeighbors()
  {
    return this.neighbors;
  }
}
