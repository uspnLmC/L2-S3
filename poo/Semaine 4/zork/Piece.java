import java.util.EnumMap;

public class Piece
{

	private String description;
	private ObjetZork [] objets;
	private EnumMap < Direction, Piece > sorties;

	/* ----------------------------------------------------------------------------------------- */

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
	}

	/* ----------------------------------------------------------------------------------------- */

	public String description () { return this.description; }
	public ObjetZork [] objets () { return this.objets; }
	public ObjetZork objet (int indice) { return this.objets [indice]; }
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

	public String descriptionObjets ()
	{
		if ( this.objetsVide () )
			return ( "Pas d'objets" );
		
		String returnString = "Objets :";

		for ( ObjetZork objet : this.objets )
			if ( objet != null ) returnString = returnString + " <" + objet.nom_poids ( " " ) + ">";
		
		return returnString;
	}

	public String descriptionCapacite ()
	{
		return ( "Places restantes : " + ( this.objets.length - this.nbObjets () ) );
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
		return ( this.desciptionLongue () + "\n" + this.descriptionObjets () + "\n" + this.descriptionCapacite () + "\n" + this.descriptionSorties () );
	}


	public boolean objetsVide ()
	{
		if ( this.objets == null ) return true;

		for ( ObjetZork objet : this.objets )
			if ( objet != null ) return false;

		return true;
	}

	public int nbObjets ()
	{
		if ( this.objets == null ) return 0;

		int compteur = 0;
		for ( ObjetZork objet : this.objets )
			if ( objet != null ) compteur += 1;

		return compteur;
	}

	public void enleveObjet (int indice)
	{
		this.objets [indice] = null;

		return;
	}

	public void ajouteObjet (ObjetZork objet)
	{
		for ( int indiceObjet = 0; indiceObjet < this.objets.length; indiceObjet ++ )
			if ( this.objets [indiceObjet] == null )
				{ this.objets [indiceObjet] = objet; return; }

		return;
	}


	public Piece pieceSuivante (Direction direction)
	{
		return ( this.sorties.get (direction) );
	}

}