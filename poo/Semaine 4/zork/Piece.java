//	Semaine_4

import java.util.EnumMap;


/**										<p> MODIFICATION !!! </p>
 * 	Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode texte.
 * 
 * 	<p>
 * 		Un objet 'Piece' represente un des lieux dans lesquels se deroule l'action du jeu.
 * 		Elle possede des objets que l'utilisateur peut apercevoir.
 * 		Elle est reliee a au plus quatre autres 'Piece' par des sorties (nord, est, sud, ouest).
 * 	</p>
 * 
 * 	@author		LemilCa
 * 	@version	Semaine_4 from Semaine_3
 */
public class Piece
{
    /* -------------------------------- Arguments non constants -------------------------------- */

    private String                       description;
	private ObjetZork []                 objets;
    private EnumMap < Direction, Piece > sorties;


    /* ------------------------------------- Constructeurs ------------------------------------- */

	/**									<p> NE PLUS UTILISER !!! </p>
	 * 	Initialise une piece decrite par le string specifie.
	 * 
	 * 	@param description		(String)
	 */
    public Piece (String description)
    {
        this.description = description;
        this.sorties     = new EnumMap < Direction, Piece > ( Direction.class );

        return;
    }

	/**	
	 * 	Initialise une piece decrite par le string et le tableau d'objetZork specifies.
	 * 
	 * 	@param description		(String)
	 * 	@param objets			(ObjetZork []) : si null, tableau ObjetZork de taille 10 vide
	 */
	public Piece (String description, ObjetZork [] objets)
	{
		this.description = description;
		this.objets      = ( objets != null ) ? objets : new ObjetZork [10];
		this.sorties     = new EnumMap < Direction, Piece > ( Direction.class );

		return;
	}


    /* ------------------------------------------ Get ------------------------------------------ */

	/**
	 * 	Renvoie l'argument 'description'.
	 * 
	 * 	@return			String
	 */
    public String description () { return this.description; }

	/**	
	 * 	Renvoie l'argument 'objets'.
	 * 
	 * 	@return			ObjetZork []
	 */
	public ObjetZork [] objets () { return this.objets; }

	/**
	 * 	Renvoie l'argument 'sorties'.
	 * 
	 * 	@return			EnumMap < Direction, Pieces >
	 */
    public EnumMap < Direction, Piece > sorties () { return this.sorties; }


    /* ---------------------------------------- Méthodes --------------------------------------- */

	/**
	 * 	Renvoie la description longue de cette piece.
	 * 
	 * 	@return			String
	 */
    public String descriptionLongue ()
    {
        return ( "Vous etes " + this.description + "." );
    }

	/**	
	 * 	Renvoie la description des objets de cette piece.
	 * 
	 * 	@return			String : si objets vide, renvoie "Pas d'objets"
	 */
	public String descriptionObjets ()
	{
		String descriptionObjets = "Objets : ";
		boolean auMoinsUnPasNull = false;

		for ( ObjetZork objet : this.objets )
			if ( objet != null )
			{
				auMoinsUnPasNull = true;
				descriptionObjets += ( "\n\t- " + objet.descriptionGlobale () );
			}
		
		return (auMoinsUnPasNull) ? (descriptionObjets) : ("Pas d'objets");
	}

	/**									<p> AJOUT !!! </p>
	 * 	Renvoie la description de la capacite de cette piece.
	 * 
	 * 	@return			String : si objets plein, renvoie "Il n'y a plus de place dans la piece."
	 */
	public String descriptionCapacite ()
	{
		int nbPlacesRestantes = this.objetsCapaciteTotale () - this.objetsCapaciteActuelle ();

		return (nbPlacesRestantes > 0) ? ( "Places restantes : " + nbPlacesRestantes) : ("Il n'y a plus de place dans la piece.");
	}
	
	/**
	 * 	Renvoie la description des sorties de cette piece.
	 * 
	 * 	@return			String
	 */
    public String descriptionSorties ()
    {
        String descriptionSorties = "Sorties :";

        for ( Direction sortie : this.sorties.keySet () )
            descriptionSorties += ( " " + sortie );

        return descriptionSorties;
    }


	/**									<p> MODIFICATION !!! </p>
	 * 	Renvoie la description globale de cette piece.
	 * 
	 * 	@return			String
	 */
    public String descriptionGlobale ()
    {
        String descriptionGlobale = "";

        descriptionGlobale += ( this.descriptionLongue   () + "\n" );
		descriptionGlobale += ( this.descriptionObjets   () + "\n" );
		descriptionGlobale += ( this.descriptionCapacite () + "\n" );
        descriptionGlobale += ( this.descriptionSorties  () );

        return descriptionGlobale;
    }

    					/* -------------------------------------------- */
    
	/**
	 * 	Definie les sorties de cette piece.
	 * 
	 * 	@param nord		Piece
	 * 	@param est		Piece
	 * 	@param sud		Piece
	 * 	@param ouest	Piece
	 */
    public void initialiserSorties (Piece nord, Piece est, Piece sud, Piece ouest)
    {
        if ( nord  != null ) this.sorties.put ( Direction.NORD , nord  );

        if ( est   != null ) this.sorties.put ( Direction.EST  , est   );
        
        if ( sud   != null ) this.sorties.put ( Direction.SUD  , sud   );
        
        if ( ouest != null ) this.sorties.put ( Direction.OUEST, ouest );
        
        return;
    }

    					/* -------------------------------------------- */
    
	/**	
	 * 	Renvoie l'objet situe au int specifie dans le tableau d'objet de cette piece.
	 * 
	 * 	@param indice	(int) : incorrect si < 0 ou >= taille 'objets'
	 * 
	 * 	@return			ObjetZork : si indice incorrect, renvoie null
	 */
	public ObjetZork objet (int indice)
	{
		return ( indice >= 0 && indice < this.objets.length ) ? ( this.objets [indice] ) : (null);
	}

	/**	
	 * 	Renvoie la capacite totale du tableau d'objet de cette piece.
	 * 
	 * 	@return			int
	 */
	public int objetsCapaciteTotale ()
	{
		return ( this.objets.length );
	}

	/**	
	 * 	Renvoie le nombre d'objets presents dans cette piece.
	 * 
	 * 	@return			int
	 */
	public int objetsCapaciteActuelle ()
	{
		int compteur = 0;
		for ( ObjetZork objet : this.objets )
			if ( objet != null ) compteur += 1;
		
		return compteur;
	}

	/**	
	 * 	Teste si cette piece est remplie.
	 * 
	 * 	@return			boolean : 'false' si une place de vide, 'true' sinon
	 */
	public boolean objetsEstPlein ()
	{
		for ( ObjetZork objet : this.objets )
			if ( objet == null ) return false;
		
		return true;
	}

	/**	
	 * 	Teste si cette piece est vide.
	 * 
	 * 	@return			boolean : 'false' si un objet present, 'true' sinon
	 */
	public boolean objetsEstVide ()
	{
		for ( ObjetZork objet : this.objets )
			if ( objet != null ) return false;
		
		return true;
	}

	/**									<p> AJOUT !!! </p>
	 * 	Renvoie l'indice du premier emplacement vide de cette piece.
	 * 
	 * 	@return			int : -1 si objets plein
	 */
	public int objetsIndicePremierNull ()
	{
		for ( int indice = 0; indice < this.objets.length; indice ++ )
			if ( this.objets [indice] == null ) return indice;
		
		return -1;
	}

    					/* -------------------------------------------- */
    
	/**
	 * 	Renvoie la piece qui se trouve dans la direction specifiee.
	 * 
	 * 	@param direction	(Direction)
	 * 
	 * 	@return				Piece : piece presente dans cette direction
	 */
    public Piece pieceSuivante (Direction direction)
    {
        return ( this.sorties.get (direction) );
    }

	/**									<p> AJOUT !!! </p>
	 * 	Ajoute l'objet specifie dans cette piece a l'endroit specifie.
	 * 
	 * 	@param indice		(int)
	 * 	@param objet		(ObjetZork)
	 */
	public void ajouterObjet (int indice, ObjetZork objet)
	{
		this.objets [indice] = objet;

		return;
	}

	/**									<p> AJOUT !!! </p>
	 * 	Retire l'objet situe a l'endroit specifiee de cette piece.
	 * 
	 * 	@param indice		(int)
	 */
	public void retirerObjet (int indice)
	{
		this.objets [indice] = null;

		return;
	}


}


