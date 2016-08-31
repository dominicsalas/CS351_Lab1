package common;

import java.io.BufferedReader;
//import java.io.File; - For getAbsolutePath()
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordPath
{
	public static boolean DEBUG = true;
  //Only temp
  public static ArrayList<String> dictionaryList;
  private static List<List<Node>> edgeList = new ArrayList<>();

	public static void main(String[] args)
	{
	       //System.out.println("Working Directory = " + System.getProperty("user.dir"));
	       //String basePath = new File("").getAbsolutePath();
	       //System.out.println(basePath);

	       //String path = new File("alkjsdfla").getAbsolutePath();
	       //System.out.println(path);
		checkInput(args);
    buildGraphList();
    createGraph(args);
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
  }

  private static void createGraph(String[] args)
  {

    for (String s : dictionaryList)
    {
      Node newNode = new Node(s);
      // Checks for same length
      modificationChecks(newNode, 1, 1);
      // Checks for one less
      modificationChecks(newNode, 2, 2);
      // Checks for one greater
      modificationChecks(newNode, 1, 0);
      edgeList.get(newNode.length-1).add(newNode);
    }
    System.out.print(edgeList);


    List<List<Node>> edgeListOne = new ArrayList<>();
    List<List<Node>> edgeListTwo = new ArrayList<>();
    List<Node> wordOneSameLenList = new ArrayList<>();
    List<Node> wordTwoSameLenList = new ArrayList<>();


    wordOneSameLenList = createSameLengthList(args[1]);
    edgeListOne.add(wordOneSameLenList);
    wordTwoSameLenList = createSameLengthList(args[2]);
    edgeListTwo.add(wordTwoSameLenList);
    System.out.print(edgeListOne);
  }

  private static void modificationChecks(Node node, int failsAllowed, int arrayIndex)
  {
    int length = node.length;
    List<Node> arrayList;
    //arrayList = edgeList.get(length-1);
    if (length-arrayIndex < 0 || length-arrayIndex > 11)
    {
      return;
    }
    arrayList = edgeList.get(length-arrayIndex);
    int numFails;

    for (Node n : arrayList)
    {
      numFails = 0;

      if (n.length == node.length)
      {
        for (int i = 0; i < length; i++)
        {
          if (n.name.charAt(i) != node.name.charAt(i))
          {
            numFails++;
          }
        }

      }
      else if (n.length < node.length)
      {
        int j = 0;
        int i = 0;

        while (j != n.length && i != node.length)
        {
          if (n.name.charAt(j) != node.name.charAt(i))
          {
            i++;
            numFails++;
          }
          else
          {
            i++;
            j++;
          }
        }
      }
      else if (n.length > node.length)
      {
        int j = 0;
        int i = 0;

        while (i != n.length && j != node.length)
        {
          if (n.name.charAt(i) != node.name.charAt(j))
          {
            i++;
            numFails++;
          }
          else
          {
            j++;
            i++;
          }
        }
      }

      if (numFails <= failsAllowed)
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
        dictionaryList.add(line);
				if (line.equals(args[1]))
				{
					wordOneMatch = true;
				}
				
				if (line.equals(args[2]))
				{
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
