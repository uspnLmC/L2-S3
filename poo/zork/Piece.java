import java.util.Map;
import java.util.EnumMap;


public class Piece
{

	private String description;
	private EnumMap < Direction, Piece > sorties;


	public Piece (String description)
	{
		this.description = description;
		this.sorties = new EnumMap < Direction, Piece > ( Direction.class );

		return;
	}
	public void setSorties (Piece nord, Piece est, Piece sud, Piece ouest)
	{
		if ( nord != null ) 
			this.sorties.put ( Direction.NORD, nord );
		
		if ( est != null )
			this.sorties.put ( Direction.EST, est );
		
		if ( sud != null )
			this.sorties.put ( Direction.SUD, sud );
		
		if ( ouest != null )
			this.sorties.put ( Direction.OUEST, ouest );
		
		return;
	}


	public String description () { return this.description; }
	public EnumMap < Direction, Piece > sorties () { return this.sorties; }

	public String descriptionLongue ()
	{
		return ( "Vous etes " + this.description + ".\n" + this.descriptionSorties () );
	}
	public String descriptionSorties ()
	{
		String returnString = "Sorties :";
		
		for ( Direction sortie : this.sorties.keySet () )
			returnString += ( " " + sortie );
		
		return returnString;
	}


	public Piece pieceSuivante (Direction direction)
	{
		return ( this.sorties.get (direction) );
	}

	
}

