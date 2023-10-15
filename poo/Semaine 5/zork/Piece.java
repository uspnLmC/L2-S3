//	Semaine_5

import java.util.ArrayList;
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
 * 	@version	Semaine_5 from Semaine_4
 */
public class Piece
{
    /* -------------------------------- Arguments non constants -------------------------------- */

    private String                       description;
	private int                          capaciteMax;
	private ArrayList < ObjetZork >      objets;
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

	/**									<p> MODIFICATION !!! </p>
	 * 	Initialise une piece decrite par le string et le int specifies.
	 * 
	 * 	@param description		(String)
	 * 	@param capaciteMax		(int) : si negatif, 10
	 */
	public Piece (String description, int capaciteMax)
	{
		this.description = description;
		this.capaciteMax = ( capaciteMax >= 0 ) ? (capaciteMax) : (10);
		this.objets      = new ArrayList < ObjetZork > (capaciteMax);
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

	/**									<p> AJOUT !!! </p>
	 * 	Renvoie l'argument 'capaciteMax'.
	 * 
	 * 	@return			int
	 */
	public int capaciteMax () { return this.capaciteMax; }
	
	/**									<p> MODIFICATION !!! </p>
	 * 	Renvoie l'argument 'objets'.
	 * 
	 * 	@return			ArrayList < ObjetZork >
	 */
	public ArrayList < ObjetZork > objets () { return this.objets; }

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

	/**									<p> MODIFICATION !!! </p>
	 * 	Renvoie la description des objets de cette piece.
	 * 
	 * 	@return			String : si objets vide, renvoie "Pas d'objets"
	 */
	public String descriptionObjets ()
	{
		if ( this.objets.size () == 0 ) return ("Pas d'objets");

		String descriptionObjets = "Objets : ";

		for ( ObjetZork objet : this.objets )
			descriptionObjets += ( "\n\t- " + objet.descriptionGlobale () );
		
		return descriptionObjets;
	}

	/**	
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


	/**	
	 * 	Renvoie la description globale de cette piece.
	 * 
	 * 	@return			String
	 */
    public String descriptionGlobale ()
    {
        String descriptionGlobale = "";

        descriptionGlobale += ( this.descriptionLongue  () + "\n" );
		descriptionGlobale += ( this.descriptionObjets () + "\n" );
		descriptionGlobale += ( this.descriptionCapacite () + "\n" );
        descriptionGlobale += ( this.descriptionSorties () );

        return descriptionGlobale;
    }

    					/* -------------------------------------------- */
    
	/**									<p> AJOUT !!! </p>
	 * 	Definie les objets de cette piece.
	 * 
	 * 	@param objets	(ObjetZork [])
	 */
	public void initialiserObjets (ObjetZork ... objets)
	{
		int indice = 0;
		while ( indice < this.capaciteMax && indice < objets.length )
			{ this.objets.add (objets [indice]); indice += 1; }

		return;
	}

	/**
	 * 	Definie les sorties de cette piece.
	 * 
	 * 	@param nord		(Piece)
	 * 	@param est		(Piece)
	 * 	@param sud		(Piece)
	 * 	@param ouest	(Piece)
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
    
	/**									<p> MODIFICATION !!! </p>
	 * 	Renvoie l'objet situe au int specifie dans le tableau d'objet de cette piece.
	 * 
	 * 	@param indice	(int) : incorrect si < 0 ou >= taille 'objets'
	 * 
	 * 	@return			ObjetZork : si indice incorrect, renvoie null
	 */
	public ObjetZork objet (int indice)
	{
		return ( indice >= 0 && indice < this.objets.size () ) ? ( this.objets.get (indice) ) : (null);
	}

	/**									<p> MODIFICATION !!! </p>
	 * 	Renvoie la capacite totale du tableau d'objet de cette piece.
	 * 
	 * 	@return			int
	 */
	public int objetsCapaciteTotale ()
	{
		return ( this.capaciteMax );
	}

	/**									<p> MODIFICATION !!! </p>
	 * 	Renvoie le nombre d'objets presents dans cette piece.
	 * 
	 * 	@return			int
	 */
	public int objetsCapaciteActuelle ()
	{
		return ( this.objets.size () );
	}

	/**									<p> MODIFICATION !!! </p>
	 * 	Teste si cette piece est remplie.
	 * 
	 * 	@return			boolean : 'false' si une place de vide, 'true' sinon
	 */
	public boolean objetsEstPlein ()
	{
		return ( this.capaciteMax == this.objets.size () );
	}

	/**									<p> MODIFICATION !!! </p>
	 * 	Teste si cette piece est vide.
	 * 
	 * 	@return			boolean : 'false' si un objet present, 'true' sinon
	 */
	public boolean objetsEstVide ()
	{
		return ( this.objets.isEmpty () );
	}

	/**									<p> MODIFICATION !!! </p>
	 * 	Renvoie l'indice du premier emplacement vide de cette piece.
	 * 
	 * 	@return			int : -1 si objets plein
	 */
	public int objetsIndicePremierNull ()
	{
		return ( ! this.objetsEstPlein () ) ? ( this.objets.size () ) : (-1);
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

	/**									<p> MODIFICATION !!! </p>
	 * 	Ajoute l'objet specifie dans cette piece a l'endroit specifie.
	 * 
	 * 	@param indice		(int)
	 * 	@param objet		(ObjetZork)
	 */
	public void ajouterObjet (ObjetZork objet)
	{
		this.objets.add (objet);

		return;
	}

	/**									<p> MODIFICATION !!! </p>
	 * 	Retire l'objet situe a l'endroit specifiee de cette piece.
	 * 
	 * 	@param indice		(int)
	 */
	public void retirerObjet (int indice)
	{
		this.objets.remove (indice);

		return;
	}


}


