package sjsu.Wong.cs146.project1;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * Tester for the CircularLLGame class
 * Provided with five specific test cases, the tester will run through the process of elimination
 * and check to see if the correct prisoner got there freedom
 */
public class LinkedListTester 
{
	//test case #1
	@Test
	public void testList1()
	{
		System.out.println("-----------------------------------------");


		int prisonerCount = 6; 		//the number of prisoners to be in the list
		int eliminationNumber = 2; 	//the number to be used to count off 
		int expectedWinner = 1; 	//the expected winner to get their freedom

		System.out.println("Number of prisoners: " + prisonerCount);
		System.out.println("Number to count off: " + eliminationNumber);


		CircularLLGame lineup = new CircularLLGame(0); 	//creates an empty list

		assertTrue("list is not empty", lineup.isEmpty());

		assertEquals("size of list is not 0", 0, lineup.size());

		lineup = new CircularLLGame(prisonerCount); 	//inserts the desired amount of prisoners

		//Does the elimination processes
		for(int i = 0; i < prisonerCount -1; i++)
		{
			lineup.eliminate(eliminationNumber);
		}

		assertEquals("size of list is not 1", 1, lineup.size()); 	

		System.out.println("Expected winner: " + expectedWinner);
		System.out.println("Actual winner: " + lineup.getCurrent().getData());

		assertEquals("the wrong prisoner got their freedom", expectedWinner, lineup.getCurrent().getData()); //checks to see if the right prisoner was chosen

	}

	//test case 2
	@Test
	public void testList2()
	{
		System.out.println("-----------------------------------------");


		int prisonerCount = 1; 		//the number of prisoners to be in the list
		int eliminationNumber = 9; 	//the number to be used to count off 
		int expectedWinner = 1; 	//the expected winner to get their freedom

		System.out.println("Number of prisoners: " + prisonerCount);
		System.out.println("Number to count off: " + eliminationNumber);

		CircularLLGame lineup = new CircularLLGame(0); 	//creates an empty list

		assertTrue("list is not empty", lineup.isEmpty());

		assertEquals("size of list is not 0", 0, lineup.size());

		lineup = new CircularLLGame(prisonerCount); 	//inserts the desired amount of prisoners

		//Does the elimination processes
		for(int i = 0; i < prisonerCount -1; i++)
		{
			lineup.eliminate(eliminationNumber);
		}

		assertEquals("size of list is not 1", 1, lineup.size()); 	

		System.out.println("Expected winner: " + expectedWinner);
		System.out.println("Actual winner: " + lineup.getCurrent().getData());

		assertEquals("the wrong prisoner was chosen", expectedWinner, lineup.getCurrent().getData()); 	//checks to see if the right prisoner was chosen

	}

	//test case #3
	@Test
	public void testList3()
	{
		System.out.println("-----------------------------------------");

		int prisonerCount = 7; 		//the number of prisoners to be in the list
		int eliminationNumber = 7; 	//the number to be used to count off 
		int expectedWinner = 4; 	//the expected winner to get their freedom

		System.out.println("Number of prisoners: " + prisonerCount);
		System.out.println("Number to count off: " + eliminationNumber);

		CircularLLGame lineup = new CircularLLGame(0); 	//creates an empty list

		assertTrue("list is not empty", lineup.isEmpty());

		assertEquals("size of list is not 0", 0, lineup.size());

		lineup = new CircularLLGame(prisonerCount); 	//inserts the desired amount of prisoners

		//Does the elimination processes
		for(int i = 0; i < prisonerCount -1; i++)
		{
			lineup.eliminate(eliminationNumber);
		}

		assertEquals("size of list is not 1", 1, lineup.size()); 	

		System.out.println("Expected winner: " + expectedWinner);
		System.out.println("Actual winner: " + lineup.getCurrent().getData());

		assertEquals("freedom was not granted to the right one", expectedWinner, lineup.getCurrent().getData()); //checks to see if the right prisoner was chosen

	}

	//test case #4
	@Test
	public void testList4()
	{
		System.out.println("-----------------------------------------");


		int prisonerCount = 12; 	//the number of prisoners to be in the list
		int eliminationNumber = 8; 	//the number to be used to count off 
		int expectedWinner = 2; 	//the expected winner to get their freedom

		System.out.println("Number of prisoners: " + prisonerCount);
		System.out.println("Number to count off: " + eliminationNumber);

		CircularLLGame lineup = new CircularLLGame(0); 	//creates an empty list

		assertTrue("list is not empty", lineup.isEmpty());

		assertEquals("size of list is not 0", 0, lineup.size());

		lineup = new CircularLLGame(prisonerCount); 	//inserts the desired amount of prisoners

		//Does the elimination processes
		for(int i = 0; i < prisonerCount -1; i++)
		{
			lineup.eliminate(eliminationNumber);
		}

		assertEquals("size of list is not 1", 1, lineup.size()); 	

		System.out.println("Expected winner: " + expectedWinner);
		System.out.println("Actual winner: " + lineup.getCurrent().getData());

		assertEquals("the bad guy got away", expectedWinner, lineup.getCurrent().getData()); 	//checks to see if the right prisoner was chosen



	}

	//test case #5
	@Test
	public void testList5()
	{
		System.out.println("-----------------------------------------");


		int prisonerCount = 5; 		//the number of prisoners to be in the list
		int eliminationNumber = 1; 	//the number to be used to count off 
		int expectedWinner = 3; 	//the expected winner to get their freedom

		System.out.println("Number of prisoners: " + prisonerCount);
		System.out.println("Number to count off: " + eliminationNumber);

		CircularLLGame lineup = new CircularLLGame(0); 	//creates an empty list

		assertTrue("the list is not empty", lineup.isEmpty());

		assertEquals("size of the list is not 0", 0, lineup.size());

		lineup = new CircularLLGame(prisonerCount); 	//inserts the desired amount of prisoners

		//Does the elimination processes
		for(int i = 0; i < prisonerCount -1; i++)
		{
			lineup.eliminate(eliminationNumber);
		}

		assertEquals("size of the list is not 1", 1, lineup.size()); 	

		System.out.println("Expected winner: " + expectedWinner);
		System.out.println("Actual winner: " + lineup.getCurrent().getData());

		assertEquals("A bad man was let off the hook", expectedWinner, lineup.getCurrent().getData()); 	//checks to see if the right prisoner was chosen

	}

}
