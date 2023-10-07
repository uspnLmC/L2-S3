

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

	public String nom_poids (String espace)
	{
		return ( this.nom + espace + this.poids );
	}

}