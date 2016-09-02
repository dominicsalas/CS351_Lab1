package common;

import java.io.BufferedReader;
//import java.io.File; - For getAbsolutePath()
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WordPath
{
	public static boolean DEBUG = true;
  //Only temp
  //public static final int MAX_LEN=12;
  public static ArrayList<String> dictionaryList;
  private static List<List<Node>> edgeList = new ArrayList<>();
  //private static List<Node>[] edgeList = new ArrayList<>[MAX_LEN+1]();
  static final long startTime = System.currentTimeMillis();
  static Node startNode;
  static Node goalNode;

	public static void main(String[] args)
	{
	       //System.out.println("Working Directory = " + System.getProperty("user.dir"));
	       //String basePath = new File("").getAbsolutePath();
	       //System.out.println(basePath);

	       //String path = new File("alkjsdfla").getAbsolutePath();
	       //System.out.println(path);
    buildGraphList();
		checkInput(args);
    createGraph(args);
	}

  public static ArrayList<Node> algorithm(Node startNode, Node goalNode)
  {
    ArrayList<Node> path = new ArrayList<>();
    int priority = 0;
    int new_cost = 0;
    Astar_Comparator comparator = new Astar_Comparator();
    PriorityQueue<Astar_Elements> frontier = new PriorityQueue<>(20, comparator);
    frontier.add(new Astar_Elements(startNode, 0));
    HashMap<Node, Node> came_from = new HashMap<>();
    HashMap<Node, Integer> cost_so_far = new HashMap<>();
    Node current = null;
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
      return null;
    }

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

    long endTime = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    System.out.println(TimeUnit.MILLISECONDS.toMinutes(totalTime));
    System.out.println(TimeUnit.MILLISECONDS.toSeconds(totalTime));
    System.out.println(totalTime);
    return path;
  }

  // Taken from wikipedia - https://en.wikipedia.org/wiki/Levenshtein_distance
  private static int levenshteinDistance(String s, String t)
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

  private static int minimum(int[] array)
  {
    int min = Integer.MAX_VALUE;
    for (Integer num : array)
    {
      if (num < min) min = num;
    }
    return min;
  }


  private static void buildGraphList()
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
    /* Faster solution below
    for(int i=0; i<=MAX_LEN; i++)
    {
      edgeList[i]=new ArrayList<>();
    }
    */
  }

  private static void createGraph(String[] args)
  {
    //arrayList = edgeList.get(length-1);
    List<Node> sublist;

    for (List<Node> array : edgeList)
    {
      int i = 0;
      for (Node node : array)
      //for (int i = 0; i < array.size(); i++)
      {
        sublist = array.subList(i, array.size());
        modificationChecks(node, sublist);
        if (node.length < 12)
        {
          sublist = edgeList.get(node.length);
          modificationChecks(node, sublist);
        }
        //modificationChecks(node, 1, 1);
        //modificationChecks(node, 1, 0);
        i++;
      }
    }
    /*
    for (String s : dictionaryList)
    {
      Node newNode = new Node(s);
      // Checks for same length
      modificationChecks(newNode, 1, 1);
      // Checks for one less
      modificationChecks(newNode, 1, 2);
      // Checks for one greater
      modificationChecks(newNode, 1, 0);
      edgeList.get(newNode.length-1).add(newNode);

      if (newNode.name.equals(args[1]))
      {
        startNode = newNode;
      }
      else if (newNode.name.equals(args[2]))
      {
        goalNode = newNode;
      }
    }
    */
    /*
    final long endTime = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    System.out.println("Total execution time: " + (endTime - startTime));
    System.out.println("Total execution time: " + TimeUnit.MILLISECONDS.toMinutes(totalTime));
    */
    //System.out.print(edgeList);

    Collection path = algorithm(startNode, goalNode);
  }

  private static void modificationChecks(Node node, List<Node> arrayList)
  {
    int matches;
    int numFails;
    int failsAllowed = 1;
    int length = node.length;
    Node largerNode;
    Node smallerNode;
    //List<Node> arrayList;
    //arrayList = edgeList.get(length-1);
    /*
    if (length-arrayIndex < 0 || length-arrayIndex > 11)
    {
      return;
    }
    arrayList = edgeList.get(length-arrayIndex);
    */



    for (Node n : arrayList)
    {
      if (n.name.equals(node.name))
      {
        return;
      }
      numFails = 0;
      matches = 0;

      if (n.length == node.length)
      {
        //for (int i = 0; i < length; i++)
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
    //System.out.println(node.name);
  }

  private static List<Node> createSameLengthList(String word)
  {
    List<Node> sameLenList = new ArrayList<>();
    int numberOfLetterDifferences;

    for (String line : dictionaryList)
    {
      numberOfLetterDifferences = 0;

      if (word.length() == line.length() && !word.equals(line))
      {

        for (int i = 0; i < line.length(); i++)
        {
          if (word.charAt(i) != line.charAt(i))
          {
            numberOfLetterDifferences++;
          }
        }

        if (numberOfLetterDifferences <= 1)
        {
          sameLenList.add(new Node(line));
        }
      }
    }

    return sameLenList;
  }

	// If time, add exceptions instead of error print statements
	private static void checkInput(String[] args)
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
		else if (!validateFile(args))
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

	private static boolean validateFile(String[] args)
	{
		String line = null;
		String fileName = args[0];
		boolean wordOneMatch = false;
		boolean wordTwoMatch = false;
    dictionaryList = new ArrayList<>();

		if (DEBUG)
		{
			//fileName = "src/data/test.txt";
      //fileName = "src/data/example.txt";
      fileName = "src/data/OpenEnglishWordList.txt";
		}

		try
		{
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null)
			{
        // Build ArrayLists of nodes
        Node newNode = new Node(line);
        edgeList.get(newNode.length-1).add(newNode);

        //dictionaryList.add(line);
				if (line.equals(args[1]))
				{
          startNode = newNode;
					wordOneMatch = true;
				}
				
				if (line.equals(args[2]))
				{
          goalNode = newNode;
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
			return false;
		} 
		catch (IOException ex)
		{
			System.out.println("Error reading file '" + fileName + "'");
			return false;
		}

		if (wordOneMatch && wordTwoMatch)
		{
			return true;
		}
		else
		{
			System.out.println("One or both of the words were not found:");
			System.out.println(args[1] + ": " + wordOneMatch);
			System.out.println(args[2] + ": " + wordTwoMatch);
			return false;
		}
	}
}
