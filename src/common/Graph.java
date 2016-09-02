package common;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dominic Salas
 *
 * This is the heart of the program...essentially where all the magic happens.
 *
 * This Class is responsible for building the ArrayList of ArrayLists.
 * It's responsible for handling the input checking. Building the ArrayList
 * of Nodes. Checks the start word and end word. Creates graph. Etc.
 */
public class Graph
{
  private boolean DEBUG = false;
  private static List<List<Node>> edgeList = new ArrayList<>();
  private Node startNode, goalNode;
  private String fileName;

  /**
   * Constructor for creating the 12 different ArrayLists
   */
  public Graph()
  {
    edgeList.add(new ArrayList<>());
    edgeList.add(new ArrayList<>());
    edgeList.add(new ArrayList<>());
    edgeList.add(new ArrayList<>());
    edgeList.add(new ArrayList<>());
    edgeList.add(new ArrayList<>());
    edgeList.add(new ArrayList<>());
    edgeList.add(new ArrayList<>());
    edgeList.add(new ArrayList<>());
    edgeList.add(new ArrayList<>());
    edgeList.add(new ArrayList<>());
    edgeList.add(new ArrayList<>());
  }

  /**
   * Sets the filename that is passed in
   * @param fileName
   */
  public void setFilename(String fileName)
  {
    this.fileName = fileName;
  }

  /**
   * Builds the ArrayLists of Nodes
   */
  public void buildList()
  {
    String line;
    // FileReader reads text files in the default encoding.
    FileReader fileReader;

    try
    {
      fileReader = new FileReader(fileName);

      // Always wrap FileReader in BufferedReader.
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      while ((line = bufferedReader.readLine()) != null)
      {
        // Build ArrayLists of nodes
        Node newNode = new Node(line);
        edgeList.get(newNode.length - 1).add(newNode);
      }

      // Always close files.
      bufferedReader.close();
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("Unable to open file '" + fileName + "'");
      System.exit(0);
    }
    catch (IOException ex)
    {
      System.out.println("Error reading file '" + fileName + "'");
      System.exit(0);
    }
  }

  /**
   * Checks if the startword and goalword are in the dictionary file passed in.
   * @param startWord word to start from
   * @param goalWord target word
   * @return returns True if words are found and False if one or both words
   * are not in the file.
   */
  public boolean checkWords(String startWord, String goalWord)
  {
    String line;
    boolean wordOneMatch = false;
    boolean wordTwoMatch = false;

    // FileReader reads text files in the default encoding.
    FileReader fileReader;
    try
    {
      fileReader = new FileReader(fileName);

      // Always wrap FileReader in BufferedReader.
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      while ((line = bufferedReader.readLine()) != null)
      {
        //Node newNode = new Node(line);

        //dictionaryList.add(line);
        if (line.equals(startWord))
        {
          setStartNode(startWord);
          wordOneMatch = true;
        }

        if (line.equals(goalWord))
        {
          setGoalNode(goalWord);
          wordTwoMatch = true;
        }
        //System.out.println(line);
      }

      // Always close files.
      bufferedReader.close();
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("Unable to open file '" + fileName + "'");
      System.exit(0);
    }
    catch (IOException ex)
    {
      System.out.println("Error reading file '" + fileName + "'");
      System.exit(0);
    }


    if (wordOneMatch && wordTwoMatch)
    {
      return true;
    }
    else
    {
      System.out.println("One or both of the words were not found:");
      System.out.println(startWord + ": " + wordOneMatch);
      System.out.println(goalWord + ": " + wordTwoMatch);
      return false;
    }
  }

  /**
   * Creates the graph of Nodes
   */
  public void createGraph()
  {
    List<Node> sublist;

    for (List<Node> array : edgeList)
    {
      int i = 0;
      for (Node node : array)
      {
        sublist = array.subList(i+1, array.size());
        modificationChecks(node, sublist);
        if (node.length < 12)
        {
          sublist = edgeList.get(node.length);
          modificationChecks(node, sublist);
        }
        i++;
      }
    }
  }

  /**
   * Handles the checking of each Node essentially looking for neighbors
   * @param node the Node we're finding neighbors for.
   * @param arrayList the ArrayList to search against
   */
  public void modificationChecks(Node node, List<Node> arrayList)
  {
    int matches;
    int numFails;
    int failsAllowed = 1;
    Node largerNode;
    Node smallerNode;

    for (Node n : arrayList)
    {
      numFails = 0;
      matches = 0;

      // Checks nodes that are equal in length
      if (n.length == node.length)
      {
        int i = 0;
        while (i != n.length && numFails <= failsAllowed)
        {
          if (n.name.charAt(i) != node.name.charAt(i))
          {
            numFails++;
          }
          else
          {
            matches++;
          }
          i++;
        }

      }
      // Checks nodes that are a difference of one
      else
      {
        if (n.length < node.length)
        {
          largerNode = node;
          smallerNode = n;
        }
        else
        {
          largerNode = n;
          smallerNode = node;
        }

        int j = 0;
        int i = 0;

        while (j != smallerNode.length && i != largerNode.length
                && numFails <= failsAllowed)
        {
          if (smallerNode.name.charAt(j) != largerNode.name.charAt(i))
          {
            i++;
            numFails++;
          }
          else
          {
            i++;
            j++;
            matches++;
          }
        }
      }

      if (numFails <= failsAllowed && matches >= 1)
      {
        node.addNeighbor(n);
        n.addNeighbor(node);
      }
    }
  }

  /**
   * Checks to ensure the arguments passed in are correct and meet the specs.
   * @param args arguments passed in
   */
  public void checkInput(String[] args)
  {
    // Checks if less than 3 args are passed
    if (args.length < 3)
    {
      System.out.println("Error: Program takes at least 3 arguments");
      System.out.println("Exiting program...");
      System.exit(0);
    }
    // Checks if an odd number of words are passed
    else if ((args.length - 1) % 2 != 0)
    {
      System.out.println("Error: Program takes an odd number words");
      System.out.println("Exiting program...");
      System.exit(0);
    }
    // Checks if first arg is a valid file
    else if (!validateFile())
    {
      System.out.println("Exiting program...");
      System.exit(0);
    }
    else
    {
      if (DEBUG)
      {
        System.out.println("Input Checks Completed!");
      }
    }
  }

  /**
   * Verifies the file is valid and finds the absolute path
   * @return returns True if file is valid and False otherwise.
   */
  private boolean validateFile()
  {
    try
    {
      System.setProperty("user.dir", new File(
               WordPath.class.getProtectionDomain().getCodeSource().
                       getLocation().toURI()).getParent());
    }
    catch (URISyntaxException e)
    {
      e.printStackTrace();
    }

    if (DEBUG)
    {
      //fileName = "src/data/test.txt";
      //fileName = "src/data/example.txt";
      fileName = "src/data/OpenEnglishWordList.txt";
    }

    try
    {
      // FileReader reads text files in the default encoding.
      FileReader fileReader = new FileReader(new File(fileName).getCanonicalPath());

      // Always wrap FileReader in BufferedReader.
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      // Always close files.
      bufferedReader.close();
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("Unable to open file '" + fileName + "'");
      return false;
    }
    catch (IOException ex)
    {
      System.out.println("Error reading file '" + fileName + "'");
      return false;
    }

    return true;
  }

  /**
   * Setter for setting our Start Node. Finds the appropriate Node and sets it.
   * @param startString the String that we're looking for
   */
  public void setStartNode(String startString)
  {
    for (Node n : edgeList.get(startString.length()-1))
    {
      if (n.getName().equals(startString))
      {
        startNode = n;
        break;
      }
    }
  }

  /**
   * Setter for setting our Goal Node. Finds the appropriate Node and sets it.
   * @param goalString the String used to find the Node we're looking for.
   */
  public void setGoalNode(String goalString)
  {
    for (Node n : edgeList.get(goalString.length()-1))
    {
      if (n.getName().equals(goalString))
      {
        goalNode = n;
        break;
      }
    }
  }

  /**
   * Getter for grabbing our Starting Node.
   * @return starting Node
   */
  public Node getStartNode()
  {
    return startNode;
  }

  /**
   * Getter for grabbing our Goal Node.
   * @return Goal Node
   */
  public Node getGoalNode()
  {
    return goalNode;
  }
}
