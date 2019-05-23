package sjsu.Nguyen_Wong.cs146.project2;

import java.util.ArrayList;

/*
 * This class has no impact on the actual maze generating/solving program
 * It just makes our lives easier for the JUnit test cases 
 */
public class GraphToString 
{
	/*
	 * Main method that creates the expected output so that we can just copy and paste it in the JUnit testers
	 * instead of manually typing them in one string at a time into the 2D array 
	 */
	public static void main(String[] args) 
	{

		String m = "+-+-+-+-+-+-+-+ +";
		ArrayList<Character> list = new ArrayList<>();
		for( int i = 0; i < m.length(); i++)
		{
			list.add(m.charAt(i));
		}
		ArrayList<String> graph = new ArrayList<>();
		for(int i = 0; i < list.size(); i++)
		{
			graph.add("\"" + list.get(i) + "\", ");
		}
		for (int i = 0; i < graph.size(); i++)
		{
			System.out.print(graph.get(i));
		}

	}

}

