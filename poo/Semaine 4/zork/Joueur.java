//	Semaine_4


/**										<p> AJOUT !!! </p>
 * 	Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode texte.
 * 
 * 	<p>
 * 		Un objet 'joueur' represente un participant du jeu.
 * 		Il possede un nom, un inventaire et une capacite de transport, ainsi que la piece ou il se trouve.
 * 	</p>
 * 
 * 	@author 	LemilCa
 * 	@version	Semaine_4
 */
public class Joueur
{
    /* -------------------------------- Arguments non constants -------------------------------- */

    private String       pseudo;
    private Piece        pieceActuelle;
    private ObjetZork [] inventaire;
    private int          capaciteTransport;


    /* ------------------------------------- Constructeurs ------------------------------------- */

	/**
	 * 	Initialise un joueur decrit par un string et un int specifies.
	 * 
	 * 	@param pseudo				(String)
	 * 	@param capaciteTransport	(int) : a la fois la capacite et la taille de l'inventaire ; si negatif, inventaire de taille 10 et capaciteTransport 0
	 */
    public Joueur (String pseudo, int capaciteTransport)
    {
        this.pseudo            = pseudo;
        this.pieceActuelle     = null;
        this.inventaire        = ( capaciteTransport >= 0 ) ? (new ObjetZork [capaciteTransport]) : (new ObjetZork [10]);
        this.capaciteTransport = ( capaciteTransport >= 0 ) ? (capaciteTransport) : (0);

        return;
    }

	/**
	 * 	Initialise un joueur decrit par un string et deux int specifies.
	 * 
	 * 	@param pseudo				(String)
	 * 	@param nbObjetsMax			(int) : si negatif, inventaire de taille 10.
	 * 	@param capaciteTransport	(int) : si negatif, 0
	 */
    public Joueur (String pseudo, int nbObjetsMax, int capaciteTransport)
    {
        this.pseudo            = pseudo;
        this.pieceActuelle     = null;
        this.inventaire        = ( nbObjetsMax >= 0 ) ? (new ObjetZork [nbObjetsMax]) : (new ObjetZork [10]);
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

	/**
	 * 	Renvoie l'argument 'inventaire'.
	 * 
	 * 	@return			ObjetZork []
	 */
    public ObjetZork [] inventaire () { return this.inventaire; }

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
    
	/**
	 * 	Renvoie l'objet situe au int specifie dans l'inventaire de ce joueur.
	 * 
	 * 	@param indice	(int) : incorrect si < 0 ou >= taille 'inventaire'
	 * 
	 * 	@return			ObjetZork : si indice incorrect, renvoie null
	 */
    public ObjetZork objet (int indice)
	{
		return ( indice >= 0 && indice < this.inventaire.length ) ? ( this.inventaire [indice] ) : (null);
	}

	/**	
	 * 	Renvoie la capacite totale de l'inventaire de ce joueur.
	 * 
	 * 	@return			int
	 */
	public int inventaireCapaciteTotale ()
    {
        return ( this.inventaire.length );
    }

	/**	
	 * 	Renvoie le nombres d'objets presents dans l'inventaire de ce joueur.
	 * 
	 * 	@return			int
	 */
    public int inventaireCapaciteActuelle ()
    {
        int compteur = 0;
		for ( ObjetZork objet : this.inventaire )
			if ( objet != null ) compteur += 1;
		
		return compteur;
    }

	/**
	 * 	Teste si l'inventaire de ce joueur est plein.
	 * 
	 * 	@return			boolean : 'false' si une place de vide, 'true' sinon
	 */
	public boolean inventaireEstPlein ()
	{
		for ( ObjetZork objet : this.inventaire )
			if ( objet == null ) return false;
		
		return true;
	}

	/**
	 * 	Teste si l'inventaire de ce joueur est vide.
	 * 
	 * 	@return			boolean : 'false' si un objet present, 'true' sinon
	 */
    public boolean inventaireEstVide ()
    {
        for ( ObjetZork objet : this.inventaire )
			if ( objet != null ) return false;
		
		return true;
    }

	/**
	 * 	Renvoie l'indice de la premiere case de l'inventaire vide de ce joueur.
	 * 
	 * 	@return			int : -1 si inventaire plein
	 */
    public int inventaireIndicePremierNull ()
    {
        for ( int indice = 0; indice < this.inventaire.length; indice ++ )
			if ( this.inventaire [indice] == null ) return indice;
		
		return -1;
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

	/**
	 * 	Ajoute l'objet specifie dans l'inventaire de ce joueur au int specifie
	 * 
	 * 	@param indice		(int)
	 * 	@param objet		(ObjetZork)
	 */
    public void ajouterObjet (int indice, ObjetZork objet)
    {
        this.inventaire [indice] = objet;
        this.capaciteTransport -= ( objet.poids () );

        return;
    }

	/**
	 * 	Retire l'objet specifie situe au int specifie de l'inventaire de ce joueur.
	 * 
	 * 	@param indice		(int)
	 * 	@param objet		(ObjetZork)
	 */
    public void retirerObjet (int indice, ObjetZork objet)
    {
        this.inventaire [indice] = null;
        this.capaciteTransport += ( objet.poids () );

        return;
    }


}


