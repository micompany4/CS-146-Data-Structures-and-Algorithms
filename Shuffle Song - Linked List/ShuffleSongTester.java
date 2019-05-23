package sjsu.Wong.cs146.project1;


import static org.junit.Assert.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * Testers for the MusicShuffle Class
 * Takes two files and sees if they are equal to each other line by line
 */
public class ShuffleSongTester 
{
	//testing using "r.setSeed()" method
	@Test
	public void testShuffle() throws IOException 
	{
		//the file containing the shuffled list of songs created by the MusicShuffle class
		BufferedReader Out = new BufferedReader(new FileReader("Files/WongMichaelPlaylist.txt"));

		//the file containing the expected shuffle list of songs
		BufferedReader In = new BufferedReader(new FileReader("Files/Target2a.txt"));

		String expectedLine; 	//creates a string to hold the expected song names

		System.out.println("Begining test for file equivalence.");

		//parses through the expected file until all the songs are read
		while((expectedLine = In.readLine()) != null) 	
		{
			String actualLine = Out.readLine(); 							//creates a string to hold the actual song names
			assertEquals("Lines do not match", expectedLine, actualLine); 	//checks to see if each individual line of the expected file equals the lines of the actual file
		}

		System.out.println("Files are equivalent.");

		Out.close();
		In.close();	

	}


	//testing using "myRandGen" method
	@Test
	public void testShuffle2() throws IOException 
	{

		//the file containing the shuffled list of songs created by the MusicShuffle class
		BufferedReader Out = new BufferedReader(new FileReader("Files/WongMichaelPlaylist2.txt"));

		//the file containing the expected shuffle list of songs
		BufferedReader In = new BufferedReader(new FileReader("Files/Target1a.txt"));

		String expectedLine; //creates a string to hold the expected song names

		System.out.println("Begining test for file equivalence.");

		while((expectedLine = In.readLine()) != null) //parses through the expected file until all the songs are read
		{
			String actualLine = Out.readLine(); //creates a string to hold the actual song names
			assertEquals("These lines do not match",expectedLine, actualLine); //checks to see if each individual line of the expected file equals the lines of the actual file
		}

		System.out.println("Files are equivalent.");

		Out.close();
		In.close();

	}

}
