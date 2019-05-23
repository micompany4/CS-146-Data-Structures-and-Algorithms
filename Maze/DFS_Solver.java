package sjsu.Nguyen_Wong.cs146.project2;

import java.util.Stack;


/*
 * Class that handles the dfs algorithm for solving the maze
 */
public class DFS_Solver 
{

	private int counter = 0;			//counter to count the steps of dfs
	private Stack<Vertex> CellStack;	//stack to implement the process of dfs

	//ctor for the class: creates a stack
	public DFS_Solver()
	{
		CellStack = new Stack<>();
	}

	/*
	 * Implements the DFS algorithm utilizes a stack to prevent recursion
	 * @param start the vertex to start at
	 * @param finish the goal of the search where dfs stops
	 * @param m the maze to show the path
	 */
	public void doDFS(Vertex start, Vertex finish, String[][] m)
	{
		CellStack.push(start);
		start.visited(true);

		//loops through the stack until it's empty
		while(!CellStack.isEmpty())
		{
			Vertex current = CellStack.pop();
			//System.out.print(current.getIndex() + ", ");

			m[current.getRow()][current.getColumn()] = "" + counter; //sets the steps for the maze solution
			counter++;
			//changes the counter so that it's always single digit
			if(counter == 10)
			{
				counter = 0;
			}
			//checks to see if the searching algorithm reached its destination
			if(current.equals(finish)) 
			{
				return;
			}

			//loops through the neighbors of the vertex 
			for(Vertex v: current.getNeighbors())
			{

				if(!v.seen())
				{
					v.visited(true); //sets the marker that we've visited this vertex
					v.setPrevVertex(current);
					CellStack.push(v);	//pushes neighbors to the stack so that it moves on to that cell
				}

			}
		}
	}

	/*
	 * Uses DFS to get the shortest path and displays the maze solution with #'s
	 */
	public void shortestPath(Vertex start, Vertex finish, String[][] m)
	{

		CellStack.push(start);
		start.visited(true);

		//loops through the stack until it is empty
		while(!CellStack.isEmpty())
		{
			Vertex current = CellStack.pop();


			//checks to see if the searching algorithm reached its destination
			if(current.equals(finish))
			{
				m[finish.getRow()][finish.getColumn()] = "#";
				return;
			}

			//marks the nodes with #'s 
			m[current.getRow()][current.getColumn()] = "#";
			for(Vertex v: current.getNeighbors())
			{
				if(!v.seen())
				{
					v.visited(true); //marks cell as visited
					v.setPrevVertex(current);
					CellStack.push(v);	//pushes it on the stack to progress through the stack. 
				}

			}
		}


	}

	/*
	 * Fills in the gaps between #'s in the outputted String[][]
	 * @param maze the String[][] to hold the shortest path
	 */
	public void check(String[][] maze)
	{

		//loops through the rows of the maze
		for(int i = 1; i < maze.length-1; i+=2)
		{
			//loops through the columns of the maze
			for(int j = 1; j < maze.length-1; j+=2)
			{
				//checks to add a # between two #'s in a row if possible
				//array bounds, presence of two adjacent #'s and a broken wall
				if(i+2 < maze.length && maze[i][j] == "#" && maze[i+2][j] == "#" && maze[i+1][j] == " ")
				{
					maze[i+1][j] = "#";
				}
				//checks to add a # between two #'s in a column if possible
				//array bounds, presence of two adjacent #'s and a broken wall
				if(j+2 < maze.length && maze[i][j] == "#" && maze[i][j+2] == "#" && maze[i][j+1] == " ")
				{
					maze[i][j+1] = "#";
				}

			}
		}

	}

}


