package common;

import java.io.BufferedReader;
//import java.io.File; - For getAbsolutePath()
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordPath
{
	public static boolean DEBUG = true;
	
	public static void main(String[] args)
	{
	       //System.out.println("Working Directory = " + System.getProperty("user.dir"));
	       //String basePath = new File("").getAbsolutePath();
	       //System.out.println(basePath);

	       //String path = new File("alkjsdfla").getAbsolutePath();
	       //System.out.println(path);
		checkInput(args);
		create2DMatrix(args);
    createGraph(args);
	}

  private static void createGraph(String[] args)
  {

  }

  private static void create2DMatrix(String[] args)
	{
    Node node = new Node();
    //ArrayList<Node[][]> edgeList = new ArrayList<Node>();
    //List<List><Node>> edgeList = new ArrayList<Node>();

        for (int i = 0; i < args[1].length(); i++)
        {
            for (int j = 0; j < args[2].length(); j++)
            {
            }
        }
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

		if (DEBUG)
		{
			fileName = "src/data/test.txt";
		}

		try
		{
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null)
			{
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
