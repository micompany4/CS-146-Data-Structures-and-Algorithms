package sjsu.Wong.cs146.project1;


/*
 * Simulates a method of granting freedom to a prisoner used by an ancient king
 * Lines up a certain amount of prisoners and then
 * uses an "Eeny, meeny, miny, moe" process of eliminating prisoners until one remains
 * The last prisoner remaining is the winner and granted their freedom
 */
public class CircularLLGame 
{

	private static Node head; 		//creates the head of the linked list
	private static Node current; 	//the node that will be used to traverse the linked list
	private static int size; 		//keeps track of the size of the linked list
	private static boolean empty; 	//boolean expression to show if the list is empty or not


	/*
	 * ctor for CircularLLGame class
	 * creates a circular linked list
	 */
	public CircularLLGame(int total)
	{
		empty = true; 	//signals that the list is empty until prisoners get added
		add(total); 	//adds the prisoners
	}

	/*
	 * adds the specified amount of prisoners to the line
	 * parameter int totalPrisoners is the specified amount of prisoners to be added to the line
	 */
	public static void add(int totalPrisoners)
	{
		size = totalPrisoners; 		//sets the size of the list to the total amount of prisoners to be added

		if(totalPrisoners >= 1)
		{
			head = new Node(1); 	//creates the head of the linked list
			current = head; 		//sets current to the head 
			empty = false; 			//sets the state that the list is no longer empty
		}
		else
		{
			return; 				//if nothing needs to be added, the add method will terminate
		}

		//for loop to add the amount of prisoners to the line
		for(int i = 2; i <= totalPrisoners; i++)
		{
			Node next = new Node(i); 	//creates a new node to be added to the list with the appropriately assigned number
			current.setNext(next); 		//adds the new node after the current node to the list
			current = next; 			//updates the current node as the end of the list

		}

		current.setNext(head); 			//creates the circular linked list by setting the last node's next node as the head

	}

	/*
	 * deletes the node from the linked list after counting off
	 * parameter int countedOut is the number to be used to count off for elimination
	 */
	public static void delete(int countedOut)
	{
		//for loop to reach the node to be deleted
		for(int i = 1; i <= countedOut; i++)
		{
			current = current.getNext(); 		//traverses the list
		}

		//sets the current node's next to the node two nodes after, deleting the skipped node
		current.setNext(current.getNext().getNext()); 
		size--; 
	}

	//gets the current node
	public Node getCurrent()
	{
		return current;
	}

	//returns a boolean expression regarding if the list is empty or not
	public boolean isEmpty()
	{
		return empty;
	}

	//returns the size of the linked list;
	public int size()
	{
		return size;
	}

	//non static method to call delete
	//parameter chosen is the number to be used to count off
	public void eliminate(int chosen)
	{
		delete(chosen);
	}

}

