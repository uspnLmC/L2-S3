//	Semaine_4 ; Semaine_5


/** 
 * 	Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode texte.
 * 
 * 	<p>
 * 		Un objet 'ObjetZork' represente un objet dans le jeu.
 * 		Il possede un nom, une description, un poids et une "transportabilite".
 * 	</p>
 * 
 * 	@author		LemilCa
 * 	@version	Semaine_4 from Semaine_3
 */
public class ObjetZork
{
    /* -------------------------------- Arguments non constants -------------------------------- */

    private String  nom;
    private String  description;
    private int     poids;
    private boolean estTransportable;


    /* ------------------------------------- Constructeurs ------------------------------------- */

	/**
	 * 	Initialise un objet decrit par deux strings, un int et un boolean specifie.
	 * 
	 * 	@param nom					(String)
	 * 	@param description			(String)
	 * 	@param poids				(int) : si negatif, poids = valeur maximale du type int
	 * 	@param estTransportable		(boolean)
	 */
    public ObjetZork (String nom, String description, int poids, boolean estTransportable)
    {
        this.nom              = nom;
        this.description      = description;
        this.poids            = ( poids >= 0 ) ? (poids) : ( Integer.MAX_VALUE );
        this.estTransportable = estTransportable;

        return;
    }


    /* ------------------------------------------ Get ------------------------------------------ */

	/**
	 * 	Renvoie l'argument 'nom'.
	 * 
	 * 	@return			String
	 */
    public String nom () { return this.nom; }
    
	/**
	 * 	Renvoie l'argument 'description'.
	 * 
	 * 	@return			String
	 */
    public String description () { return this.description; }

	/**
	 * 	Renvoie l'argument 'poids'.
	 * 
	 * 	@return			int
	 */
    public int poids () { return this.poids; }

	/**
	 * 	Renvoie l'argument 'estTransportable'.
	 * 
	 * 	@return			boolean
	 */
    public boolean estTransportable () { return this.estTransportable; }


    /* ---------------------------------------- Méthodes --------------------------------------- */

	/**	
	 * 	Teste si cet objet est le même que l'objet specifie.
	 * 
	 * 	@param objet	(ObjetZork)
	 * 	
	 * 	@return			boolean : 'true' si ce sont les memes, 'false' sinon
	 */
	public boolean equals (ObjetZork objet)
	{
		if ( this == objet )                                   return true;

		if ( ! this.nom.equals ( objet.nom ) )                 return false;

		if ( ! this.description.equals ( objet.description ) ) return false;

		if ( this.poids != objet.poids )                       return false;

		if ( this.estTransportable == objet.estTransportable ) return false;

		return true;
	}

	/**	
	 * 	Renvoie le hashCode de cet objet.
	 * 
	 * 	@return			int
	 */
	public int hashCode ()
	{
		int hashCode = 0;

		hashCode += ( this.nom.hashCode         ()  );
		hashCode += ( this.description.hashCode ()  );
		hashCode += ( this.poids                    );
		hashCode += ( this.estTransportable ) ? 1 : 0;

		return hashCode;
	}

	                    /* -------------------------------------------- */

	/**
	 * 	Renvoie la description globale de cet objet.
	 * 
	 * 	@return			String
	 */
    public String descriptionGlobale ()
    {
        String descriptionGlobale = "";

        descriptionGlobale += ( this.nom + " : " );
        descriptionGlobale += ( this.description + " (" );
        descriptionGlobale += ( this.estTransportable ) ? ( "poids : " + this.poids ) : ("pas transportable");

        return ( descriptionGlobale + ")" );
    }


}


