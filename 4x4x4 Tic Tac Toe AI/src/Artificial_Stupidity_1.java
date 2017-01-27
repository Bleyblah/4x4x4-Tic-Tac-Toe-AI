import java.util.ArrayList;
import java.util.Random;

public class Artificial_Stupidity_1 implements PlayerInt
{
	private char letter;
	private String name;
	public static boolean isReset;
	private int randX;
	private int randY;
	private int randZ;

	// Pre:		receives a char letter
	// Post: 	sets the name to "Random AI" and the letter to the letter received
	Artificial_Stupidity_1(char letter)
	{
		name = "Artificial Stupidity 1";
		this.letter	= letter;
	}


	public char getLetter()
	{
		return letter;
	}

	// Pre:		method is called
	// Post: 	returns the Location where the player wants to move
	public LocationInt getMove(BoardInt board)
	{
		ArrayList<Location> zeros = new ArrayList<Location>();
		ArrayList<Location> singles = new ArrayList<Location>();
		ArrayList<Location> doubles = new ArrayList<Location>();
		ArrayList<Location> triples = new ArrayList<Location>();
		ArrayList<Location> quadruples = new ArrayList<Location>();
		
		ArrayList<Location> otherZeros = new ArrayList<Location>();
		ArrayList<Location> otherSingles = new ArrayList<Location>();
		ArrayList<Location> otherDoubles = new ArrayList<Location>();
		ArrayList<Location> otherTriples = new ArrayList<Location>();
		ArrayList<Location> otherQuadruples = new ArrayList<Location>();


		for(int x = 0; x < board.numRows(); x++)
			for(int y = 0; y < board.numRows(); y++)
				for(int z = 0; z < board.numRows(); z++)
				{
					if(board.isEmpty(new Location(z,y,x)))
					{
						LocationScore ls = new LocationScore(board,new Location(z,y,x),letter);
						if(ls.getSelfQuadruples() >0)
						{
							quadruples.add(new Location(z,y,x));
						}
						else if(ls.getSelfTriples() >0)
						{
							triples.add(new Location(z,y,x));
						}
						else if(ls.getSelfDoubles() >0)
						{
							doubles.add(new Location(z,y,x));
						}
						else if(ls.getSelfSingles() >0)
						{
							singles.add(new Location(z,y,x));
						}
						else
						{
							zeros.add(new Location(z,y,x));
						}
					}
				}
		for(int x = 0; x < board.numRows(); x++)
			for(int y = 0; y < board.numRows(); y++)
				for(int z = 0; z < board.numRows(); z++)
				{
					if(board.isEmpty(new Location(z,y,x)))
					{
						LocationScore ls = new LocationScore(board,new Location(z,y,x),letter);
						if(ls.getOtherQuadruples() >0)
						{
							otherQuadruples.add(new Location(z,y,x));
						}
						else if(ls.getOtherTriples() >0)
						{
							otherTriples.add(new Location(z,y,x));
						}
						else if(ls.getOtherDoubles() >0)
						{
							otherDoubles.add(new Location(z,y,x));
						}
						else if(ls.getOtherSingles() >0)
						{
							otherSingles.add(new Location(z,y,x));
						}
						else
						{
							otherZeros.add(new Location(z,y,x));
						}
					}
				}
		if(isReset)
		{
			isReset = false;
			while(true)
			{
				randX = (int)(Math.random()*3);
				randY = (int)(Math.random()*3);
				randZ = (int)(Math.random()*3);
				if(board.isEmpty(new Location(randZ,randY,randX)))
				{
					return new Location(randZ,randY,randX);
				}
			}
		}
		else
		{
			if(quadruples.size()!=0)
			{	
				Location tempLoc = quadruples.get(0);
				quadruples.remove(0);
				return tempLoc;
			}
			if(otherQuadruples.size()!=0)
			{
				Location tempLoc = otherQuadruples.get(0);
				otherQuadruples.remove(0);
				return tempLoc;
			}
			else
			{
				if(triples.size()!=0)
				{
					Location tempLoc = triples.get(0);
					triples.remove(0);
					return tempLoc;
				}
				else if(doubles.size()!=0)
				{
					Location tempLoc = doubles.get(0);
					doubles.remove(0);
					return tempLoc;
				}
				else
				{
					Location tempLoc = singles.get(0);
					singles.remove(0);
					return tempLoc;
				}
			}
		}
	}

	// Pre:		method is called
	// Post: 	returns the name of the player
	public String getName()
	{
		return name;
	}
}