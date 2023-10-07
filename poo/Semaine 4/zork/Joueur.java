

public class Joueur
{

	private String nom;
	private Piece pieceActuelle;
	private int capaciteRestante;
	private ObjetZork [] objets;

	/* ----------------------------------------------------------------------------------------- */

	public Joueur (String nom, int capaciteMax)
	{
		this.nom = nom;
		this.pieceActuelle = null;
		this.capaciteRestante = capaciteMax;
		this.objets = (capaciteMax < 0) ? null : new ObjetZork [capaciteMax];

		return;
	}

	/* ----------------------------------------------------------------------------------------- */

	public String nom () { return this.nom; }
	public Piece pieceActuelle () { return this.pieceActuelle; }
	public int capaciteRestante () { return this.capaciteRestante; }
	public ObjetZork [] objets () { return this.objets; }
	public ObjetZork objet (int indice) { return this.objets [indice]; }

	/* ----------------------------------------------------------------------------------------- */

	public String descriptionNom ()
	{
		return ( "Joueur : " + this.nom );
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
		return ( "Capacite restante : " + this.capaciteRestante );
	}

	public String descriptionTotale ()
	{
		return ( this.descriptionNom () + "\n" + this.descriptionObjets () + "\n" + this.descriptionCapacite () );
	}


	public boolean objetsVide ()
	{
		if ( this.objets == null ) return true;

		for ( ObjetZork objet : this.objets )
			if ( objet != null ) return false;

		return true;
	}

	public void enleveObjet (int indice)
	{
		this.capaciteRestante += this.objets [indice].poids ();
		this.objets [indice] = null;

		return;
	}

	public void ajouteObjet (ObjetZork objet)
	{
		this.capaciteRestante -= objet.poids ();

		for ( int indiceObjet = 0; indiceObjet < this.objets.length; indiceObjet ++ )
			if ( this.objets [indiceObjet] == null )
				{ this.objets [indiceObjet] = objet; return; }

		return;
	}


	public void seDeplace (Piece pieceSuivante)
	{
		this.pieceActuelle = pieceSuivante;

		return;
	}

}