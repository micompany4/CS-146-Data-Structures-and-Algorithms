package sjsu.Nguyen_Wong.cs146.project2;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Class that handles the BFS algorithm for solving the maze
 */
public class BFS_Solver 
{

	private Queue<Vertex> cellQueue;	//queue to implement the bfs searching algorithm
	private int counter = 1; 			//counter to count the steps of bfs

	//ctor for the class: creates a new queue of linked lists
	public BFS_Solver()
	{
		cellQueue = new LinkedList<>();
	}

	/*
	 * Does the BFS algorithm on the maze
	 * @param start the starting vertex to run bfs on
	 * @param finish the finishing vertex for bfs to terminate
	 * @param m the String[][] to hold the steps of bfs
	 */
	public void doBFS(Vertex start, Vertex finish, String[][] m)
	{
		cellQueue.add(start);
		start.visited(true);
		m[1][1] = "0";

		//loop through the queue until it's empty
		while(!cellQueue.isEmpty())
		{
			Vertex current = cellQueue.remove();

			//loops thorugh the neighbors of the current vertex starting from the bottom 
			for(int i = current.getNeighbors().size()-1; i >= 0; i--)
			{

				if(!current.getNeighbors().get(i).seen())
				{
					current.getNeighbors().get(i).visited(true); //marks the vertex as visited
					cellQueue.add(current.getNeighbors().get(i)); 
					current.getNeighbors().get(i).setPrevVertex(current);
					//terminates the loop if the finishing destination is reached
					if(current.getNeighbors().get(i).equals(finish))
					{
						m[current.getNeighbors().get(i).getRow()][current.getNeighbors().get(i).getColumn()] = "" + counter;
						return;
					}
					//marks the steps of bfs
					m[current.getNeighbors().get(i).getRow()][current.getNeighbors().get(i).getColumn()] = "" + counter;
					counter++;
					//changes the counter to always be single digit
					if(counter == 10)
					{
						counter = 0;
					}

				}


			}
		}
	}

}
