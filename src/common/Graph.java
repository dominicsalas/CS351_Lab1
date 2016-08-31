package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dsalas on 8/30/16.
 */
public class Graph
{
  /*
  private static boolean validateFile(String[] args)
  {
    String line = null;
    String fileName = args[0];
    boolean wordOneMatch = false;
    boolean wordTwoMatch = false;
    //dictionaryList = new ArrayList<>();

    if (DEBUG)
    {
      //fileName = "src/data/test.txt";
      fileName = "src/data/example.txt";
    }

    try
    {
      // FileReader reads text files in the default encoding.
      FileReader fileReader = new FileReader(fileName);

      // Always wrap FileReader in BufferedReader.
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      while ((line = bufferedReader.readLine()) != null)
      {
        //dictionaryList.add(line);
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
    } catch (FileNotFoundException ex)
    {
      System.out.println("Unable to open file '" + fileName + "'");
      return false;
    } catch (IOException ex)
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
  */
}
