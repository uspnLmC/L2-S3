//	Semaine_5

import java.util.ArrayList;


/**										<p> MODIFICATION !!! </p>
 * 	Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode texte.
 * 
 * 	<p>
 * 		Un objet 'joueur' represente un participant du jeu.
 * 		Il possede un nom, un inventaire, une taille d'inventaire et une capacite de transport, ainsi que la piece ou il se trouve.
 * 	</p>
 * 
 * 	@author 	LemilCa
 * 	@version	Semaine_5 from Semaine_4
 */
public class Joueur
{
    /* -------------------------------- Arguments non constants -------------------------------- */

    private String                  pseudo;
    private Piece                   pieceActuelle;
	private int                     capaciteMax;
    private ArrayList < ObjetZork > inventaire;
    private int                     capaciteTransport;


    /* ------------------------------------- Constructeurs ------------------------------------- */

	/**									<p> MODIFICATION !!! </p>
	 * 	Initialise un joueur decrit par un string et un int specifies.
	 * 
	 * 	@param pseudo				(String)
	 * 	@param capaciteMax			(int) : a la fois la capacite et la taille de l'inventaire ; si negatif, inventaire de taille 10
	 */
    public Joueur (String pseudo, int capaciteMax)
    {
        this.pseudo            = pseudo;
        this.pieceActuelle     = null;
		this.capaciteMax       = ( capaciteMax >= 0 ) ? (capaciteMax) : (10);
        this.inventaire        = new ArrayList < ObjetZork > ( this.capaciteMax );
        this.capaciteTransport = this.capaciteMax;

        return;
    }

	/**									<p> MODIFICATION !!! </p>
	 * 	Initialise un joueur decrit par un string et deux int specifies.
	 * 
	 * 	@param pseudo				(String)
	 * 	@param capaciteMax			(int) : si negatif, inventaire de taille 10.
	 * 	@param capaciteTransport	(int) : si negatif, 0
	 */
    public Joueur (String pseudo, int capaciteMax, int capaciteTransport)
    {
        this.pseudo            = pseudo;
        this.pieceActuelle     = null;
		this.capaciteMax       = ( capaciteMax >= 0 ) ? (capaciteMax) : (10);
        this.inventaire        = new ArrayList < ObjetZork > ( this.capaciteMax );
        this.capaciteTransport = ( capaciteTransport >= 0 ) ? (capaciteTransport) : (0);

        return;
    }


    /* ------------------------------------------ Get ------------------------------------------ */

	/**
	 * 	Renvoie l'argument 'pseudo'.
	 * 
	 * 	@return			String
	 */
    public String pseudo () { return this.pseudo; }

	/**
	 * 	Renvoie l'argument 'pieceActuelle'.
	 * 
	 * 	@return			Piece
	 */
    public Piece pieceActuelle () { return this.pieceActuelle; }

	/**									<p> AJOUT !!! </p>
	 * 	Renvoie l'argument 'capaciteMax'.
	 * 
	 * 	@return			int
	 */
	public int capaciteMax () { return this.capaciteMax; }

	/**									<p> MODIFICATION !!! </p>
	 * 	Renvoie l'argument 'inventaire'.
	 * 
	 * 	@return			ArrayList < ObjetZork >
	 */
    public ArrayList < ObjetZork > inventaire () { return this.inventaire; }

	/**
	 * 	Renvoie l'argument 'capaciteTransport'.
	 * 
	 * 	@return			int
	 */
    public int capaciteTransport () { return this.capaciteTransport; }


    /* ---------------------------------------- Méthodes --------------------------------------- */

	/**
	 * 	Renvoie la description du pseudo de ce joueur.
	 * 
	 * 	@return			String
	 */
    public String descriptionPseudo ()
    {
        return ( "Joueur : " + this.pseudo );
    }

	/**
	 * 	Renvoie la description de l'inventaire de ce joueur.
	 * 
	 * 	@return			String : si inventaire vide, renvoie "Ton inventaire est vide"
	 */
    public String descriptionInventaire ()
    {
        String descriptionInventaire = "Inventaire :";
        boolean auMoinsUnPasNull     = false;

        for ( ObjetZork objet : this.inventaire )
            if ( objet != null )
            {
                auMoinsUnPasNull = true;
                descriptionInventaire += ( "\n\t- " + objet.descriptionGlobale () );
            }

        return (auMoinsUnPasNull) ? (descriptionInventaire) : ("Ton inventaire est vide.");
    }

	/**
	 * 	Renvoie la description de la capacite de ce joueur.
	 * 
	 * 	@return			String : si objets plein, renvoie "Tu n'as plus de place." ; si plus de capacite, renvoie "Tu n'as pas la force de porter plus."
	 */
    public String descriptionCapacite ()
    {
        String descriptionCapacite = "";
        int nbPlacesRestantes = this.inventaireCapaciteTotale () - this.inventaireCapaciteActuelle ();

        descriptionCapacite += (nbPlacesRestantes > 0) ? ( "Places restantes : " + nbPlacesRestantes) : ("Tu n'as plus de place.");
        descriptionCapacite += ("\n");

        descriptionCapacite += (capaciteTransport > 0) ? ( "Capacite restante : " + capaciteTransport ) : ("Tu n'as pas la force de porter plus.");

        return descriptionCapacite;
    }


	/**
	 * 	Renvoie la description globale de ce joueur.
	 * 
	 * 	@return			String
	 */
    public String descriptionGlobale ()
    {
        String stringGlobal = "";

        stringGlobal += ( this.descriptionPseudo     () + "\n" );
        stringGlobal += ( this.descriptionInventaire () + "\n" );
        stringGlobal += ( this.descriptionCapacite   () );

        return stringGlobal;
    }

    					/* -------------------------------------------- */
    
	/**									<p> MODIFICATION !!! </p>
	 * 	Renvoie l'objet situe au int specifie dans l'inventaire de ce joueur.
	 * 
	 * 	@param indice	(int) : incorrect si < 0 ou >= taille 'inventaire'
	 * 
	 * 	@return			ObjetZork : si indice incorrect, renvoie null
	 */
    public ObjetZork objet (int indice)
	{
		return ( indice >= 0 && indice < this.inventaire.size () ) ? ( this.inventaire.get (indice) ) : (null);
	}

	/**									<p> MODIFICATION !!! </p>	
	 * 	Renvoie la capacite totale de l'inventaire de ce joueur.
	 * 
	 * 	@return			int
	 */
	public int inventaireCapaciteTotale ()
    {
        return ( this.capaciteMax );
    }

	/**									<p> MODIFICATION !!! </p>	
	 * 	Renvoie le nombres d'objets presents dans l'inventaire de ce joueur.
	 * 
	 * 	@return			int
	 */
    public int inventaireCapaciteActuelle ()
    {
        return ( this.inventaire.size () );
    }

	/**									<p> MODIFICATION !!! </p>
	 * 	Teste si l'inventaire de ce joueur est plein.
	 * 
	 * 	@return			boolean : 'false' si une place de vide, 'true' sinon
	 */
	public boolean inventaireEstPlein ()
	{
		return ( this.capaciteMax == this.inventaire.size () );
	}

	/**									<p> MODIFICATION !!! </p>
	 * 	Teste si l'inventaire de ce joueur est vide.
	 * 
	 * 	@return			boolean : 'false' si un objet present, 'true' sinon
	 */
    public boolean inventaireEstVide ()
    {
        return ( this.inventaire.isEmpty () );
    }

	/**									<p> MODIFICATION !!! </p>
	 * 	Renvoie l'indice de la premiere case de l'inventaire vide de ce joueur.
	 * 
	 * 	@return			int : -1 si inventaire plein
	 */
    public int inventaireIndicePremierNull ()
    {
        return ( ! this.inventaireEstPlein () ) ? ( this.inventaire.size () ) : (-1);
    }

                        /* -------------------------------------------- */
    
	/**
	 * 	Deplace ce joueur dans la piece specifiee.
	 * 
	 * 	@param pieceSuivante	(Piece)
	 */
    public void deplacer (Piece pieceSuivante)
    {
        this.pieceActuelle = pieceSuivante;

        return;
    }

	/**									<p> MODIFICATION !!! </p>
	 * 	Ajoute l'objet specifie dans l'inventaire de ce joueur au int specifie
	 * 
	 * 	@param objet		(ObjetZork)
	 */
    public void ajouterObjet (ObjetZork objet)
    {
        this.inventaire.add (objet);
        this.capaciteTransport -= ( objet.poids () );

        return;
    }

	/**									<p> MODIFICATION !!! </p>
	 * 	Retire l'objet specifie situe au int specifie de l'inventaire de ce joueur.
	 * 
	 * 	@param indice		(int)
	 * 	@param objet		(ObjetZork)
	 */
    public void retirerObjet (int indice, ObjetZork objet)
    {
        this.inventaire.remove (indice);
        this.capaciteTransport += ( objet.poids () );

        return;
    }


}


