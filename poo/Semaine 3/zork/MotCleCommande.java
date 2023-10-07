

public class MotCleCommande
{
	private final static String [] commandesValides =
		{
			"aide",
			"piece",
			"aller",
			"quitter"
		};
	
	/* ----------------------------------------------------------------------------------------- */

	public MotCleCommande () { return; }
	
	/* ----------------------------------------------------------------------------------------- */

	public String [] commandesValides () { return commandesValides; }

	/* ----------------------------------------------------------------------------------------- */

	public boolean estCommande (String string)
	{
		for ( String commandeValide : commandesValides )
			if ( commandeValide.equals (string) ) return true;
		
		return false;
	}


	public void afficherToutesLesCommandes ()
	{
		for ( String commandeValide : commandesValides )
			System.out.print ( commandeValide + " " );
		
		System.out.println ();

		return;
	}

}