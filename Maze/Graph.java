package sjsu.Nguyen_Wong.cs146.project2;



/*
 * This was a class to implement the graphs for our program, but 
 * we didn't end up using it. 
 * This class has no effect on the project objectives
 */
public class Graph 
{

	/*
	 * A main method that we were using to check and test parts of our program
	 */
	public static void main(String[] args)
	{
		int rows = 8;
		Maze m = new Maze(rows);


		BFS_Solver gino = new BFS_Solver(); //creates a BFS object to run BFS on
		DFS_Solver kale = new DFS_Solver(); //creates a DFS object to run DFS on


		System.out.println("Before \n");
		for(int i = 0; i < rows*2 +1; i++)
		{
			for(int j = 0; j < rows*2 +1; j++)
			{
				System.out.print(m.getMaze()[i][j]);
			}
			System.out.println("");
		}

		System.out.println("\nAfter: \n");

		m.wallBreaker(m);
		

		//gino.doBFS(m.getTreeMaze()[0], m.getTreeMaze()[(rows*rows)-1], m.getMaze());
		kale.doDFS(m.getTreeMaze()[0], m.getTreeMaze()[(rows*rows)-1], m.getMaze());


		//kale.shortestPath(m.getTreeMaze()[0], m.getTreeMaze()[(rows*rows)-1], m.getMaze());
		//gino.shortestPath(m.getTreeMaze()[0], m.getTreeMaze()[(rows*rows)-1], m.getMaze());

		//kale.check(m.getMaze());
		System.out.println("");
		for(int i = 0; i < rows*2 +1; i++)
		{
			for(int j = 0; j < rows*2 +1; j++)
			{
				System.out.print(m.getMaze()[i][j]);
			}
			System.out.println("");
		}

		

	}

}


