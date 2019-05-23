package sjsu.Wong.cs146.project1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/*
 * This program simulates a shuffle function for a playlist of songs
 * It takes in a list of songs from a text file and 
 * then creates a new text file containing a shuffled list of songs. 
 */
public class MusicShuffle 
{
	private static String[] songs; 			//an array to contain the list of songs

	private static Random myRandGen;		//a random number to be assigned with a "seed" 

	//gets the random number's value as a double
	public static double myRand()
	{
		return myRandGen.nextDouble();
	}

	//ctor for the random number
	public MusicShuffle(int dimension)
	{
		myRandGen = new java.util.Random(dimension);
	}


	/*
	 * finds out how many songs are in the text file
	 * returns the total amount of songs as an integer
	 */
	public static int playlistLength(File f) throws IOException
	{
		BufferedReader bf = new BufferedReader(new FileReader(f)); 		//buffered reader to read through the songs

		String songtitle = new String(); 								//string to store the songs 
		int length = 0; 												//keeps track of the number of songs in the file
		while((songtitle = bf.readLine()) != null) 						//reads through the file until it reaches the end
		{
			length++;
		}
		bf.close();  
		return length;
	}

	/*
	 * reads the songs from the file and inserts them into an array
	 * parameters are the file containing the songs and the amount of songs in that file
	 */
	public static void read(File f, int numOfSongs) throws IOException 
	{
		songs = new String[numOfSongs]; 	//creates an array with the given size of the file

		BufferedReader bf = new BufferedReader(new FileReader(f)); 		//buffered reader to read through the songs

		String song = new String(); 									//creates new string to store the songs in 
		int counter = 0; 												//keeps track of the index for the array
		while((song = bf.readLine()) != null) 							//reads through the file until it reaches the end
		{
			songs[counter] = song; 		//inserts the song title into the specified array index
			counter++; 
		}

		bf.close();

	}

	/*
	 * shuffles the array using a set seed of 20 by using the Fisher-Yates algorithm
	 * parameter is an array of strings that will be shuffled
	 */
	public static void shuffle1(String[] A)
	{
		Random r = new Random(0); 		//creates a pseudorandom number 
		r.setSeed(20); 					//sets the seed that controls the "randomness" of the random numbers

		for(int i = A.length - 1; i > 0; i--) 	// for loop that implements the Fisher-Yates algorithm
		{
			int index = r.nextInt(i); 		//creates the random number 

			//Does the swapping
			String temp = A[index]; //temp var for the song at the random index to be swapped later
			A[index] = A[i]; 		//the random song is now at the latest desired index
			A[i] = temp; 			//the latest indexed song is now the randomly acquired song
		}

	}

	/*
	 * Shuffles the array using an alternative method 
	 * Still uses the Fisher-Yates algorithm
	 * parameters are the array to be shuffled and the random number to be used
	 */
	public static void shuffle2(String[] A, Random r)
	{

		for(int i = A.length - 1; i > 0; i--) // for loop that implements the Fisher-Yates algorithm
		{
			double d = myRand();			 //creates the random number
			int index = (int)(d * A.length); //cast the random number as an int

			String temp = A[index]; 	//temp var for the song at the random index to be swapped later
			A[index] = A[i]; 			//the random song is now at the latest index
			A[i] = temp; 				//the latest indexed song is now the randomly acquired song
		}

	}


	/*
	 * writes the contents of the shuffled array to a given file
	 * parameters are the shuffled array of songs and a file to write those songs into
	 */
	public static void write(String[] shuffled, File f) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(f)); 	//buffered writer to write the songs into a new file

		//for loop to write the contents of the shuffled array into a file
		for(int i = 0; i < shuffled.length; i++)
		{
			writer.write(shuffled[i]); 		//writes the content of the array's index to the file
			writer.newLine(); 				//moves on to the next songs
		}

		writer.flush();
		writer.close();
	}


	/*
	 * Main method to execute the shuffling program
	 * Once the shuffling is complete, JUnit can be used to test if it was done correctly.
	 */
	public static void main(String[] args) throws IOException
	{
		MusicShuffle shuffleSeed = new MusicShuffle(20); //constructs the random number with a "seed" to control randomness

		Random r = myRandGen;

		File sortedPlaylist = new File("Files/Playlist2.txt"); 					//the file containing the sorted playlist
		File shuffledPlaylist = new File("Files/WongMichaelPlaylist.txt"); 		//the file to contain the shuffled playlist once the program is done executing
		File shuffledPlaylist2 = new File("Files/WongMichaelPlaylist2.txt");	//the file to contain the shuffled playlist once the program is done executing

		read(sortedPlaylist, playlistLength(sortedPlaylist));
		shuffle1(songs);
		write(songs, shuffledPlaylist);

		read(sortedPlaylist, playlistLength(sortedPlaylist));
		shuffle2(songs, r);
		write(songs, shuffledPlaylist2);

		System.out.println("Done"); 	//Signals the end of the program
	}

}
