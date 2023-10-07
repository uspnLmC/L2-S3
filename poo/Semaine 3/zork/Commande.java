

public class Commande
{

	private String premierMot;
	private String deuxiemeMot;

	/* ----------------------------------------------------------------------------------------- */

	public Commande (String premierMot, String deuxiemeMot)
	{
		this.premierMot = premierMot;
		this.deuxiemeMot = deuxiemeMot;

		return;
	}

	/* ----------------------------------------------------------------------------------------- */

	public String premierMot () { return this.premierMot; }
	public String deuxiemeMot () { return this.deuxiemeMot; }

	/* ----------------------------------------------------------------------------------------- */

	public boolean estInconnue ()
	{
		return ( this.premierMot == null );
	}
	public boolean aDeuxiemeMot ()
	{
		return ( this.deuxiemeMot != null );
	}

}