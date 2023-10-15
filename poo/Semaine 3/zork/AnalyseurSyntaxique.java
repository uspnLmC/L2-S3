//	Base ; Semaine_3 ; Semaine_4 ; Semaine_5

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 	Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode texte.
 * 
 * 	<p>
 * 		Cet analyseur syntaxique lit les entrees dans le terminal utilisateur et tente de les interpreter comme des commandes du jeu "Zork".
 * 		Chaque appel a la methode 'recupereCommande' lit une ligne du terminal et tente de l'interpreter comme constituant d'une commande composee de deux mots.
 * 		La commande est alors renvoyee sous forme d'une instance de la classe Commande.
 * 	</p>
 * 
 * 	@author		LemilCa
 * 	@version	Base
 */
public class AnalyseurSyntaxique
{
    /* -------------------------------- Arguments non constants -------------------------------- */

    private MotCleCommande commandes;


    /* ------------------------------------- Constructeurs ------------------------------------- */

	/**
	 * 	Initialise un nouvel analyseur syntaxique.
	 */
    public AnalyseurSyntaxique ()
    {
        this.commandes = new MotCleCommande ();

        return;
    }


    /* ------------------------------------------ Get ------------------------------------------ */

	/**
	 * 	Renvoie l'argument 'commandes'.
	 * 
	 * 	@return			MotCleCommande
	 */
    public MotCleCommande commandes () { return this.commandes; }


    /* ---------------------------------------- Méthodes --------------------------------------- */

	/**
	 * 	Affiche la liste de toutes les commandes reconnues par le jeu.
	 */
    public void afficherCommandes ()
    {
        this.commandes.afficherCommandes ();

        return;
    }

    					/* -------------------------------------------- */

	/**
	 * 	Lit une ligne du terminal et tente de l'interpreter comme une commande de 2 mots.
	 * 	
	 * 	@return			Commande : commande utilisateur
	 */
    public Commande recupererCommande ()
    {
        String commande;
        String motCommande, motParametre;

        System.out.print ("> ");
        BufferedReader reader = new BufferedReader ( new InputStreamReader ( System.in ) );

        try { commande = reader.readLine (); }
        catch ( java.io.IOException exception )
        {
            System.out.print   ("Une erreur est survenue pendant la lecture de votre commande");
            System.out.println ( exception.getMessage () );

            return new Commande (null, null);
        }

        StringTokenizer tokenizer = new StringTokenizer (commande);

        motCommande  = ( tokenizer.hasMoreTokens () ) ? ( tokenizer.nextToken () ) : (null);
        motParametre = ( tokenizer.hasMoreTokens () ) ? ( tokenizer.nextToken () ) : (null);

        return ( this.commandes.estCommande (motCommande) ) ?
            ( new Commande (motCommande, motParametre) ) :
            ( new Commande (null       , motParametre) );
    }


}


