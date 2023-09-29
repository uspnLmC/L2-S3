

public class ObjetZork
{
	private String nom;
	private String description;
	private int poids;
	private boolean transportable;


	public ObjetZork (String nom, String description, int poids, boolean transportable)
	{
		this.nom = nom;
		this.description = description;
		this.poids = ( poids < 0 ) ? Integer.MAX_VALUE : poids;
		this.transportable = transportable;

		return;
	}


	public String nom () { return this.nom; }
	public String description () { return this.description; }
	public int poids () { return this.poids; }
	public boolean transportable () { return this.transportable; }

	public String nomDescription ()
	{
		return ( this.nom + " : " + this.description );
	}
	public String nomPoids ()
	{
		return ( "<" + this.nom + ", " + this.poids + ">" );
	}
	public String nomTransportable ()
	{
		String returnString = ( this.transportable ) ?
			this.nom + "est transportable" :
			this.nom + "n'est pas transportable";
		
		return returnString;
	}


	public int hashCode ()
	{
		return ( this.description.hashCode () + this.poids );
	}


	public boolean peutEtrePris (int poidsActuel, int poidsMax)
	{
		if ( ! this.transportable )
			return false;
		
		if ( poidsActuel + this.poids > poidsMax )
			return false;
		
		return true;
	}
}