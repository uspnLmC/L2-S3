

public class ObjetZork
{
	
	private String nom;
	private String description;
	private int poids;
	private boolean transportable;

	/* ----------------------------------------------------------------------------------------- */

	public ObjetZork (String nom, String description, int poids, boolean transportable)
	{
		this.nom = nom;
		this.description = description;
		this.poids = ( poids < 0 ) ? Integer.MAX_VALUE : poids;
		this.transportable = transportable;

		return;
	}

	/* ----------------------------------------------------------------------------------------- */

	public String nom () { return this.nom; }
	public String description () { return this.description; }
	public int poids () { return this.poids; }
	public boolean transportable () { return this.transportable; }

	/* ----------------------------------------------------------------------------------------- */

	public boolean equals (ObjetZork objet)
	{
		if ( this.nom.equals ( objet.nom ) ) return false;
		
		if ( this.description.equals ( objet.description ) ) return false;

		if ( this.poids != objet.poids ) return false;

		if ( this.transportable != objet.transportable ) return false;

		return true;
	}

	public int hashCode ()
	{
		int hash = 0;

		hash += this.nom.hashCode ();
		hash += this.description.hashCode ();

		hash += this.poids;
		hash += ( this.transportable ) ? 1 : 0;

		return hash;
	}

	/* ----------------------------------------------------------------------------------------- */

	public String nom_poids (String espace)
	{
		return ( this.nom + espace + this.poids );
	}

}