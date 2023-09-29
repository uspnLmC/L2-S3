import java.util.Map;
import java.util.EnumMap;


public class Piece
{

	private String description;
	private ObjetZork [] objets;
	private EnumMap < Direction, Piece > sorties;


	public Piece (String description)
	{
		this.description = description;
		this.objets = null;
		this.sorties = new EnumMap < Direction, Piece > ( Direction.class );

		return;
	}
	public Piece (String description, ObjetZork [] objets)
	{
		this.description = description;
		this.objets = objets;
		this.sorties = new EnumMap < Direction, Piece > ( Direction.class );

		return;
	}
	public Piece (String description, int objetsTaille)
	{
		this.description = description;
		this.objets = ( objetsTaille < 0 ) ? null : new ObjetZork [objetsTaille];
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
	public void setObjets (ObjetZork ... objets) // ObjetZork ... objets permet de creer un tableau avec un nombre non défini d'élements (similaire à ce que permet la lib 'stdarg.h' en C)
	{
		int maxIndex = ( objets.length < this.objetsTaille () ) ? objets.length : this.objetsTaille ();

		for ( int i = 0; i < maxIndex; i ++ )
			this.objets [i] = objets [i];
		
		return;
	}


	public String description () { return this.description; }
	public EnumMap < Direction, Piece > sorties () { return this.sorties; }

	public String descriptionLongue ()
	{
		return ( "Vous etes " + this.description + ".\n" + this.descriptionSorties () + "\n" + this.descriptionObjets () );
	}
	public String descriptionSorties ()
	{
		String returnString = "Sorties :";
		
		for ( Direction sortie : this.sorties.keySet () )
			returnString += ( " " + sortie );
		
		return returnString;
	}
	public String descriptionObjets ()
	{
		if ( this.objetsEstNull () )
			return ( "Pas d'objets" );
		
		String returnString = "Objets :";

		for ( int i = 0; i < this.objetsTaille (); i ++ )
			if ( this.objet (i) != null )
				returnString += ( " " + this.objets [i].nomPoids() );

		return returnString;
	}


	public boolean objetsEstNull ()
	{
		return ( this.objets == null );
	}
	public int objetsTaille ()
	{
		if ( this.objetsEstNull () ) 
			return -1;

		return this.objets.length;
	}
	public ObjetZork objet (int i)
	{
		if ( this.objetsEstNull () )
			return null;
		
		return this.objets [i];
	}


	public void enleverObjet (int i)
	{
		if ( this.objetsEstNull () )
			return;
		
		this.objets [i] = null;

		return;
	}


	public Piece pieceSuivante (Direction direction)
	{
		return ( this.sorties.get (direction) );
	}

	
}

