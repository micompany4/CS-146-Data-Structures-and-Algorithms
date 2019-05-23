package sjsu.Wong.cs146.project1;

/*
 * Creates an Object called Node to be used in the linked list
 * It contains getters and setters for the Node
 */
public class Node 
{
	private int i; //the data that the node with take on 
	private Node node; //the node that will be acted on

	//ctor for class Node that assigns the node an integer
	public Node(int i)
	{
		this.i = i;
	}

	//gets the node after it
	public Node getNext()
	{
		return node;
	}

	//sets what the next node is
	public void setNext(Node node)
	{
		this.node = node;
	}

	//sets the node's data
	public void setData(int i)
	{

		this.i = i;
	}

	//gets the data of the node
	public int getData()
	{
		return i;
	}

}
