import java.util.EnumMap;

public class Piece
{

	private String description;
	private EnumMap < Direction, Piece > sorties;

	/* ----------------------------------------------------------------------------------------- */

	public Piece (String description)
	{
		this.description = description;
		this.sorties = new EnumMap < Direction, Piece > ( Direction.class );

		return;
	}

	/* ----------------------------------------------------------------------------------------- */

	public String description () { return this.description; }
	public EnumMap < Direction, Piece > sorties () { return this.sorties; }

	/* ----------------------------------------------------------------------------------------- */

	public void initialiseSorties (Piece nord, Piece est, Piece sud, Piece ouest)
	{
		if ( nord != null ) this.sorties.put ( Direction.NORD, nord );
		
		if ( est != null ) this.sorties.put ( Direction.EST, est );
		
		if ( sud != null ) this.sorties.put ( Direction.SUD, sud );

		if ( ouest != null ) this.sorties.put ( Direction.OUEST, ouest );

		return;
	}


	public String desciptionLongue ()
	{
		return ( "Vous etes " + this.description + "." );
	}
	public String descriptionSorties ()
	{
		String returnString = "Sorties :";

		for ( Direction sortie : this.sorties.keySet () )
			returnString = returnString + " " + sortie;
		
		return returnString;
	}
	public String descriptionTotale ()
	{
		return ( this.desciptionLongue () + "\n" + this.descriptionSorties () );
	}


	public Piece pieceSuivante (Direction direction)
	{
		return ( this.sorties.get (direction) );
	}

}