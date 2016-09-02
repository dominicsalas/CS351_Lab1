package common;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Dominic Salas
 *
 * This is the main class that calls the other classes to begin the program.
 */

public class WordPath
{
  // Used for debugging code. True for debugging on and False to disable.
	private static boolean DEBUG = false;
  static final long startTime = System.currentTimeMillis();

  /**
   * This is the main method that initiates all of the calls to the other
   * classes. I start off by calling the Graph class. This class handles
   * things like input checking and building the actual graph.
   * @param args arguments that are passed into the program.
   */
	public static void main(String[] args)
	{
    Graph newGraph = new Graph();
    newGraph.setFilename(args[0]);
    newGraph.checkInput(args);
    newGraph.buildList();
    newGraph.createGraph();

    if (DEBUG)
    {
      long endTime = System.currentTimeMillis();
      long totalTime = endTime - startTime;
      System.out.println(TimeUnit.MILLISECONDS.toMinutes(totalTime));
      System.out.println(TimeUnit.MILLISECONDS.toSeconds(totalTime));
      System.out.println(totalTime);
    }

    // Creates a list of the word pairs that are passed in
    List<String> allArgs = Arrays.asList(args).subList(1, args.length);

    // Iterates through the word pairs and finds the shortest path for each pair
    for (int i = 0; i < allArgs.size(); i+=2)
    {
      String startWord = allArgs.get(i);
      String endWord  = allArgs.get(i+1);

      if (newGraph.checkWords(startWord, endWord))
      {
        Node startNode = newGraph.getStartNode();
        Node goalNode = newGraph.getGoalNode();

        new Astar(startNode, goalNode);
      }
    }
	}
}
