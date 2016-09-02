Dominic Salas - CS351 Lab 1 - Wordpath Program

The program is structured with two packages. One is called common which houses all of java files (the code). 
The other one is called data, which has the data files I used for testing. I created another one called views 
in the event I wanted to create a GUI. 


I spent the last day cleaning up the code and refactoring. Initially, I had most of the code under Wordpath, but I 
wanted to break it up to make it easier to read and debug. The program runs in about 2.5 mins on average. I couldn't figure
out how to get it below 1 minute. I'm kind of thinking that I would have to change data structures. 

I have 6 classes. The class with Main in is called Wordpath. This class calls the other classes. The primary classes are
the Graph class, which handles the input checking along with building the graph. The other primary one is the Astar class, 
which finds the shortest path between two nodes and prints it out. 
