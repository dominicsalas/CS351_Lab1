package common;

/**
 * Created by dsalas on 8/29/16.
 */
public class Node
{
  String name;
  boolean visited;
  int costSoFar;
  int edit;

  public Node(String name)
  {
    this.name = name;
    this.visited = false;
    this.costSoFar = 0;
    this.edit = 0;
  }

  public Node(String name, boolean visited, int costSoFar, int edit)
  {
    this.name = name;
    this.visited = visited;
    this.costSoFar = costSoFar;
    this.edit = edit;
  }

  public void setName(String name)
  {
    this.name = name;
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

}
