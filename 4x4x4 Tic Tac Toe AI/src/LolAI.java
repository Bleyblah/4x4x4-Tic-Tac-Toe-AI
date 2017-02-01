import java.util.ArrayList;
import java.util.Random;

public class LolAI implements PlayerInt
{
	private char letter;
	private String name;

	// Pre:		receives a char letter
	// Post: 	sets the name to "Random AI" and the letter to the letter received
	LolAI(char letter)
	{
		name 		= "LolAI";
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
		Random rand = new Random();
		ArrayList<Location> zeros = new ArrayList<Location>();
		ArrayList<Location> singles = new ArrayList<Location>();
		ArrayList<Location> doubles = new ArrayList<Location>();
		ArrayList<Location> triples = new ArrayList<Location>();
		ArrayList<Location> quadruples = new ArrayList<Location>();

		ArrayList<Location> ozeros = new ArrayList<Location>();
		ArrayList<Location> osingles = new ArrayList<Location>();
		ArrayList<Location> odoubles = new ArrayList<Location>();
		ArrayList<Location> otriples = new ArrayList<Location>();
		ArrayList<Location> oquadruples = new ArrayList<Location>();

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
							oquadruples.add(new Location(z,y,x));
						}
						else if(ls.getOtherTriples() >0)
						{
							otriples.add(new Location(z,y,x));
						}
						else if(ls.getOtherDoubles() >0)
						{
							odoubles.add(new Location(z,y,x));
						}
						else if(ls.getOtherSingles() >0)
						{
							osingles.add(new Location(z,y,x));
						}
						else
						{
							ozeros.add(new Location(z,y,x));
						}
					}
				}
		if(quadruples.size() > 0)
		{
			return quadruples.remove(rand.nextInt(quadruples.size()));
		}
		else if(oquadruples.size()>0)
		{
			return oquadruples.remove(rand.nextInt(oquadruples.size()));
		}
		else if(triples.size() > 0)
		{
			return triples.remove(rand.nextInt(triples.size()));
		}
		else if(otriples.size() > 0)
		{
			return otriples.remove(rand.nextInt(otriples.size()));
		}
		else if(doubles.size() > 0)
		{
			return doubles.remove(rand.nextInt(doubles.size()));
		}
		else if(singles.size() > 0)
		{
			return singles.remove(rand.nextInt(singles.size()));
		}
		else if(zeros.size() > 0)
		{
			return zeros.remove(rand.nextInt(zeros.size()));
		}
		else
		{
			return new Location(rand.nextInt(4),rand.nextInt(4),rand.nextInt(4));
		}
	
		
		
	}

	// Pre:		method is called
	// Post: 	returns the name of the player
	public String getName()
	{
		return name;
	}
}