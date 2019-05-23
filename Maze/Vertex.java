package sjsu.Nguyen_Wong.cs146.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/*
 * The class that creates the vertex object
 * Complete with everything it needs from boolean flags, int pointers and arraylists of other vertices
 * Implements comparable so that it can sort the arraylists
 */
public class Vertex implements Comparable<Vertex>
{
	private int index, row, column;		 //each vertex will have a (x, y) coordinate and an assigned index as a form of ID
	private Vertex vertex;				 //another vertex that will be used to declare a previous vertex
	private ArrayList<Vertex> neighbors; //vertices that are connected with this vertex due to a wall break
	private ArrayList<Vertex> nearby;	 //vertices that are not connected with this vertex, but potentially can be if a wall is broken

	private LinkedList<Vertex> adjacencyList[];   //array of linked list to represent an adjacency list
	private boolean visited; 	//flag to tell if the vertex has been visited yet
	private boolean open;		//flag to tell if the vertex is enclosed by walls 

	/*
	 * ctor for the vertex class that initializes everything
	 * @param index the index of this vertex
	 * @param row points which row this vertex is in relative to the String[][] that the maze is being made with
	 * @param column points which column this vertex is in relative to the String[][] that the maze is being made with
	 */
	public Vertex(int index, int row, int column)
	{
		this.index = index;
		this.row = row;
		this.column = column;

		neighbors = new ArrayList<>();
		nearby = new ArrayList<>();
		this.open = false;
		this.visited = false;
		adjacencyList = new LinkedList[64];
		for(int i = 0; i < 64; i++)
		{
			adjacencyList[i] = new LinkedList<>();
		}
	}

	//gets the row of vertex
	public int getRow()
	{
		return row;
	}

	//sets the row of the vertex
	public void setRow(int r)
	{
		this.row = r;
	}

	//gets the column of the vertex
	public int getColumn()
	{
		return column;
	}

	//sets the column of the vertex
	public void setColumn(int c)
	{
		this.column = c;
	}

	//gets the neighbors of the is Vertex as an arraylist
	public ArrayList<Vertex> getNeighbors()
	{
		//sorts the arraylist in ascending order instead of making it arbitrary
		//sorting the list enabled bfs and dfs to find the solution quicker and more efficiently
		Collections.sort(neighbors);

		return neighbors;
	}

	//adds vertices to the arraylist of neighbors
	public void addNeighbors(Vertex v)
	{
		//prevents adding the same neighbor twice and self loops
		if(neighbors.contains(v) || this.equals(v))
		{
			return;
		}

		neighbors.add(v);
	}

	//gets the arraylist of nearby vertices
	public ArrayList<Vertex> getNearby()
	{
		return nearby;
	}

	//adds the vertex to the list
	public void addNearby(Vertex v)
	{
		nearby.add(v);
	}

	//creates the adjacency list representation of the vertex
	public void addAdjacent(Vertex adj)
	{
		adjacencyList[this.getIndex()].add(adj);
		adjacencyList[adj.getIndex()].add(this);

	}

	//gets the adjacency list representation of the vertex
	public LinkedList<Vertex>[] getAdjacencyList()
	{
		return adjacencyList;
	}

	//gets the index of this vertex
	public int getIndex()
	{
		return index;
	}

	//sets the index of this vertex
	public void setIndex(int i)
	{
		index = i;
	}

	//sets the previous vertex
	public void setPrevVertex(Vertex v)
	{
		this.vertex = v;
	}

	//gets the previous vertex
	public Vertex getPrevVertex()
	{
		return vertex;
	}

	//sets the vertex to see if it has been visited yet
	public void visited(boolean b)
	{
		visited = b;
	}

	//returns a boolean expression regarding if the vertex has been seen or not
	public boolean seen()
	{
		return visited;
	}

	//sets the status of the vertex's surrounding walls
	public void setOpen(boolean b)
	{
		open = b;
	}

	//gets the boolean expression to see if the vertex is closed off or not
	public boolean getOpen()
	{
		return open;
	}


	@Override
	//overrides the compareTo method so that the neighbors list is sorted in ascending order
	public int compareTo(Vertex other) 
	{

		if(this.getIndex() < other.getIndex())
		{
			return -1;
		}
		if(this.getIndex() > other.getIndex())
		{
			return 1;
		}
		return 0;
	}


}
