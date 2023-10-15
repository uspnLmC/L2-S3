//	Base

import java.util.EnumMap;


/**
 * 	Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode texte.
 * 
 * 	<p>
 * 		Un objet 'Piece' represente un des lieux dans lesquels se deroule l'action du jeu.
 * 		Elle est reliee a au plus quatre autres 'Piece' par des sorties (nord, est, sud, ouest).
 * 	</p>
 * 
 * 	@author		LemilCa
 * 	@version	Base
 */
public class Piece
{
    /* -------------------------------- Arguments non constants -------------------------------- */

    private String                       description;
    private EnumMap < Direction, Piece > sorties;


    /* ------------------------------------- Constructeurs ------------------------------------- */

	/**
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


    /* ------------------------------------------ Get ------------------------------------------ */

	/**
	 * 	Renvoie l'argument 'description'.
	 * 
	 * 	@return			String
	 */
    public String description () { return this.description; }

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
        descriptionGlobale += ( this.descriptionSorties () );

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


}


