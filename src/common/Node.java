package common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsalas on 8/29/16.
 */
public class Node
{
  String name;
  ArrayList<Node> neighbors;
  boolean visited;
  int costSoFar;
  int edit;
  int length;
  int priority;

  public Node(String name)
  {
    this.name = name;
    this.neighbors = new ArrayList<>();
    this.visited = false;
    this.costSoFar = 0;
    this.edit = 0;
    this.length = name.length();
    this.priority = 0;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void addNeighbor(Node neighbor)
  {
    this.neighbors.add(neighbor);
  }

  public void setVisited(boolean visited)
  {
    this.visited = visited;
  }

  public void setCostSoFar(int costSoFar)
  {
    this.costSoFar = costSoFar;
  }

  public void setEdit(int edit)

  {
    this.edit = edit;
  }

  public String getName()
  {
    return this.name;
  }

  public ArrayList<Node> getNeighbors()
  {
    return this.neighbors;
  }

  public boolean getVisited()
  {
    return this.visited;
  }

  public int getCostSoFar()
  {
    return this.costSoFar;
  }

  public int getEdit()

  {
    return this.edit;
  }

  public int getLength()
  {
    return this.length;
  }

}
