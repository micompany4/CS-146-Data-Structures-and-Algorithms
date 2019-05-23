package sjsu.Nguyen_Wong.cs146.project2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


/*
 * The maze class that controls all the operations and functionalities of the maze itself
 * The maze is generated through a "search and destroy" implementation of walls and 
 * then connecting vertices in the process.
 * Connections enable DFS and BFS to run and solve the maze. 
 */
public class Maze 
{

	private Random myRandGen;  //the random number to be used
	private String maze[][]; //represents the maze as a string[][]

	private Vertex[] treeMaze; 	//holds the vertices of a given maze 
	private int counter = 0;	//counter to track how many vertices there are
	private int rowLength;		//notifies the length of each row in terms of vertices

	//a getter for the random number
	public double myRandom()
	{
		return myRandGen.nextDouble(); 
	}

	/*
	 * ctor for the Maze class. All mazes are square in shape(# of rows == # of columns)
	 * @param n, the number of rows and columns the maze will have
	 * Creates a default maze with all wall intact and vertices disconnected
	 */
	public Maze(int n) 
	{
		rowLength = n;
		myRandGen = new java.util.Random(0); //initialization of the random generator
		//comment out the line below if you want to test program against the example provided
		myRandGen.setSeed(1); //to create a different maze from the example provided, we choose seed 1
		treeMaze = new Vertex[n*n];		//creates the array with the appropriate size

		//creates the vertices for the maze assigning its index and row/column coordinates
		for(int r= 1; r < n*2; r+=2)
		{
			for(int c = 1; c < n*2; c+=2)
			{
				treeMaze[counter] = new Vertex(counter, r, c);
				counter++;
			}
		}

		treeMaze[0].setOpen(true);

		//assigns vertices with vertices that are nearby and can potentially be connected if a wall is broken
		int c =0;
		for(int row = 1; row < n*2; row+=2)
		{
			for(int col = 1; col < n*2; col+=2)
			{
				this.findNearby(treeMaze[c], row, col);
				c++;
			}
		}

		//Creates the string[][] ASCII maze by following a grid pattern for the 2D array
		maze = new String[(n * 2) + 1][(n * 2) + 1];
		//moves from row to column
		for (int i = 0; i < (n * 2) + 1; i++) 
		{
			for (int j = 0; j < (n * 2) + 1; j++) 
			{
				if (i % 2 == 0) //row is even
				{
					if (i % 2 != 0 && j == 0) //row is not even and column is even
					{
						maze[i][0] = "|";

					}

					if (j % 2 == 0) //column is even
					{
						maze[i][j] = "+";

					} 
					else //column is odd
					{	
						maze[i][j] = "-";

					}
				} 
				else //row is odd
				{
					if (j % 2 == 0) //column is even
					{
						maze[i][j] = "|";

					} 
					else //column is odd
					{
						maze[i][j] = " ";

					}
				}
			}
		}
		// creates opening in top left room and bottom right room.
		maze[0][1] = " ";
		maze[(n*2)][(n*2)-1] = " ";

	}

	/*
	 * Finds the nearby vertices of the central vertex 
	 * Nearby vertices do not include diagonal vertices to the central one
	 * Order of adding: vertex below, vertex right, vertex above, vertex left
	 * @param home the vertex subject to finding vertices nearby it
	 * @param row/column the row/column of the vertex
	 */
	public void findNearby(Vertex home, int row, int column)
	{	
		//checks if there is a vertex below to add
		if(find(treeMaze, row+2, column) != -1)
		{
			home.addNearby(treeMaze[find(treeMaze, row+2, column)]);
		}

		//checks if there is a vertex to the right to add
		if(find(treeMaze, row, column+2) != -1)
		{
			home.addNearby(treeMaze[find(treeMaze, row, column+2)]);
		}

		//checks if there is a vertex above to add
		if(find(treeMaze, row-2, column) != -1)
		{
			home.addNearby(treeMaze[find(treeMaze, row-2, column)]);
		}

		//checks if a there is a vertex to the left to add
		if(find(treeMaze, row, column-2) != -1)
		{
			home.addNearby(treeMaze[find(treeMaze, row, column-2)]);
		}

	}

	/*
	 * Utilizes a stack to find cells in the maze that are inaccessible and calls breakWall to break a wall
	 * to make it accessible
	 * Uses the random number generator to randomly pick which wall to break 
	 * method runs in terms of the vertex
	 * @param m the maze to break walls
	 */
	public void wallBreaker(Maze m)
	{
		//makes the initial set up for the "search and destroy" process to break down the walls
		Stack<Vertex> cellStack = new Stack<>();	//creates a new stack
		int totalCells = counter;					//sets the totalCells to the total amount of cells gotten from the Maze ctor
		Vertex currentCell = treeMaze[0];			//currentCell initialized as the first cell in the vertex[]
		int visitedCells = 1;						//sets how many cells we've visited as one, i.e. already visited the first cell

		//loops until we visited every cell
		while(visitedCells < totalCells)
		{
			//creates an arraylist to hold the vertices that have all walls surrounding it
			ArrayList<Vertex> near = new ArrayList<>();
			for(Vertex v : currentCell.getNearby())
			{
				//adds the vertex if all four walls surround it 
				if(!v.getOpen())
				{
					near.add(v);
				}

			}

			//if there are vertices nearby with all their walls intact, proceed to break them
			if(near.size() > 0)
			{
				//selects a random vertex from those nearby to connect with the currentCell
				Vertex victim = near.get((int)(myRandom() * near.size()));

				//vertex above it is selected
				if(victim.getIndex() == currentCell.getIndex()-rowLength)
				{
					m.breakWalls(maze, victim.getRow()+1, victim.getColumn()); //breaks the wall above it and connects them
					//the two cells are no longer disconnected
					currentCell.getNearby().remove(victim);
					victim.getNearby().remove(currentCell);
					//the two cells are now open
					victim.setOpen(true);
					currentCell.setOpen(true);
				}
				else if(victim.getIndex() == currentCell.getIndex()+rowLength)	//vertex below is selected
				{
					m.breakWalls(maze, victim.getRow()-1, victim.getColumn());	//breaks the wall below it and connects them
					//the two cells are no longer disconnected
					currentCell.getNearby().remove(victim);
					victim.getNearby().remove(currentCell);
					//the two cells are now open
					victim.setOpen(true);
					currentCell.setOpen(true);
				}
				else if(victim.getIndex() == currentCell.getIndex()+1)	//vertex to the left is selected
				{
					m.breakWalls(maze, victim.getRow(), victim.getColumn()-1);	//breaks the wall to the left of it and connects them
					//the two cells are no longer disconnected
					currentCell.getNearby().remove(victim);
					victim.getNearby().remove(currentCell);
					//the two cells are now open
					victim.setOpen(true);
					currentCell.setOpen(true);
				}
				else
				{
					m.breakWalls(maze, victim.getRow(), victim.getColumn()+1);	//vertex to the right is selected
					//the two cells are no longer disconnected
					currentCell.getNearby().remove(victim);
					victim.getNearby().remove(currentCell);
					//the two cells are now open
					victim.setOpen(true);
					currentCell.setOpen(true);
				}

				cellStack.push(currentCell); //pushes the currentCell onto the stack
				currentCell = victim; //changes the current cell
				visitedCells++;

			}
			else //if the currentCell and its nearby cell(s) are connected, we are done with it
			{
				currentCell = cellStack.pop();
			}
		}

	}


	/*
	 * Does the actual breaking of the walls and connects the two vertices with an "edge"
	 * row and column is in terms of the wall
	 * @param m the string[][] that displays the maze
	 * @param row the row that the wall is located
	 * @param column the column that the wall is located
	 */
	public void breakWalls(String[][] m, int row, int column)
	{
		//checks to see if a wall is being passed and not a vertex or something out of bounds
		if((column %2 != 0 && row %2 != 0) || (column %2 == 0 && row %2 == 0))
		{
			System.out.println("Cannot break them. Cannot tear. Can't do it. I want walls!");
			return;
		}
		m[row][column] = " "; //breaks the wall

		//if the row is even then it is a "|" wall that is being broken 
		if(row %2 == 0)
		{
			//the vertex to the left and the vertex to the right of the wall are connected and are now neighbors
			treeMaze[find(treeMaze, row+1, column)].addNeighbors(treeMaze[find(treeMaze, row-1, column)]);
			treeMaze[find(treeMaze, row-1, column)].addNeighbors(treeMaze[find(treeMaze, row+1, column)]);

			//adds them to an adjacency list
			treeMaze[find(treeMaze, row-1, column)].addAdjacent(treeMaze[find(treeMaze, row+1, column)]);

		}
		else //an odd row indicates that a "-" wall is being broken
		{
			//the vertex above and below the wall are connected and are now neighbors
			treeMaze[find(treeMaze, row, column+1)].addNeighbors(treeMaze[find(treeMaze, row, column-1)]);
			treeMaze[find(treeMaze, row, column-1)].addNeighbors(treeMaze[find(treeMaze, row, column+1)]);

			//adds them to an adjacency list
			treeMaze[find(treeMaze, row, column-1)].addAdjacent(treeMaze[find(treeMaze, row, column+1)]);

		}

	}

	//gets the maze object
	public String[][] getMaze()
	{
		return maze;
	}

	//gets the vertex array 
	public Vertex[] getTreeMaze()
	{
		return treeMaze;
	}

	/*
	 * Finds a vertex based on its (row, column) coordinates
	 * @param vertices the array that holds all the vertices
	 * @param row the row coordinate of the vertex you want to find
	 * @param column the column coordinate of the vertex you want to find
	 */
	public static int find(Vertex[] vertices, int row, int column)
	{
		//loops through the array of vertices to find a vertex with matching row, column coordinates
		for(Vertex v: vertices)
		{
			if(v.getRow() == row && v.getColumn() == column)
			{
				return v.getIndex();
			}
		}
		return -1; //insures that an arrayOutOfBoundsException will be thrown if such a vertex cannot be found
	}

}
