import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AnalyseurSyntaxique
{
	
	private MotCleCommande commandes;

	/* ----------------------------------------------------------------------------------------- */

	public AnalyseurSyntaxique ()
	{
		this.commandes = new MotCleCommande ();

		return;
	}

	/* ----------------------------------------------------------------------------------------- */

	public MotCleCommande commandes () { return this.commandes; }

	/* ----------------------------------------------------------------------------------------- */

	public String [] commandesValides () { return this.commandes.commandesValides (); }
	

	public Commande recupereCommande ()
	{
		String commande = "";
		String premierMot, deuxiemeMot;

		System.out.print ( "> " );
		BufferedReader reader = new BufferedReader ( new InputStreamReader ( System.in ) );

		try { commande = reader.readLine (); }
		catch ( java.io.IOException exception )
		{
			System.out.print ( "Une erreur est survenue pendant la lecture de votre commande" );
			System.out.println ( exception.getMessage () );
		}

		StringTokenizer tokenizer = new StringTokenizer (commande);

		premierMot = ( tokenizer.hasMoreTokens () ) ? tokenizer.nextToken () : null;
		deuxiemeMot = ( tokenizer.hasMoreTokens () ) ? tokenizer.nextToken () : null;
		
		return ( this.commandes.estCommande (premierMot) ) ?
			new Commande (premierMot, deuxiemeMot) :
			new Commande (null, deuxiemeMot);
	}


	public void afficherToutesLesCommandes ()
	{
		this.commandes.afficherToutesLesCommandes ();

		return;
	}

}