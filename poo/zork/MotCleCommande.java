

public class MotCleCommande
{

	private final static String [] commandesValides =
		{
			"aide",
			"piece",
			"aller",
			"quitter"
		};


	public MotCleCommande ()
	{
		return;
	}


	public String [] commandesValides () { return commandesValides; }


	public boolean estCommande (String string)
	{
		for ( int i = 0; i < commandesValides.length; i ++ )
			if ( commandesValides [i].equals (string) )
				return true;
		
		return false;
	}

	public void afficherToutesLesCommandes ()
	{
		for ( int i = 0; i < commandesValides.length; i ++ )
			System.out.print ( commandesValides [i] + " " );
		
		System.out.println ();
	}


}

