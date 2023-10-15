//	Semaine_4 ; Semaine_5


/**										<p> MODIFICATION !!! </p>
 * 	Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode texte.
 * 
 * 	<p>
 * 		Cette classe repertorie l'ensemble des mots cle utilisables comme commandes dans le jeu.
 * 		Elle est utilisee pour verifier la validite des commandes de l'utilisateur.
 * 	</p>
 * 
 * 	@author		LemilCa
 * 	@version	Semaine_4 from Base
 */
public class MotCleCommande
{
    /* ---------------------------------- Arguments constants ---------------------------------- */

	/**									<p> MODIFICATION !!! </p>
	 * 	Un tableau non modifiable contenant les différentes commandes.
	 * 	<ul>
	 * 		<li> ligne 1 : aide </li>
	 * 		<li> ligne 2 : informations </li>
	 * 		<li> ligne 3 : actions </li>
	 * 		<li> ligne 4 : quitter </li>
	 * 	</ul>
	 */
    private final static String [] CommandesValides = {
        "aide",
        "piece"  , "joueur" ,
        "aller"  , "prendre", "poser",
        "quitter"
    };


    /* ------------------------------------- Constructeurs ------------------------------------- */

	/**
	 * 	Initialise la liste des mots cle utilisables comme commande.
	 */
    public MotCleCommande ()
    {
        return;
    }


    /* ------------------------------------------ Get ------------------------------------------ */

	/**
	 * 	Renvoie l'argument 'CommandesValides'.
	 * 
	 * 	@return			String []
	 */
    public String [] CommandesValides () { return CommandesValides; }


    /* ---------------------------------------- Méthodes --------------------------------------- */

	/**
	 * 	Teste si le string specifie est un mot cle valide.
	 * 
	 * 	@param commande		(String)
	 * 
	 * 	@return				boolean : 'true' si commande valide ; 'false' sinon
	 */
    public boolean estCommande (String commande)
    {
        for ( String commandeValide : CommandesValides )
            if ( commandeValide.equals (commande) ) return true;

        return false;
    }

                        /* -------------------------------------------- */

	/**
	 * 	Affiche la liste de toutes les commandes reconnues par le jeu.
	 */
    public void afficherCommandes ()
    {
        String aAfficher = "";

        for ( String commandeValide : CommandesValides )
            aAfficher += ( commandeValide + " " );

        System.out.println (aAfficher);

        return;
    }


}


