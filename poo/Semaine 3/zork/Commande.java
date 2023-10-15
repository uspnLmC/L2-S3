//	Base ; Semaine_3 ; Semaine_4 ; Semaine_5


/**
 * 	Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode texte.
 * 
 * 	<p>
 * 		Cette classe repertorie les informations liees a une commande utilisateur.
 * 		Elle possede deux arguments :
 * 		<ul>
 * 			<li> String motCommande  : soit un mot cle soit null. </li>
 * 			<li> String motParametre : soit un parametre de 'motCommande' soit null. </li>
 * 		</ul>
 * 		Notez que 'motParametre' peut contenir n'importe quoi, sa validite n'est pas testee, contrairement a 'motCommande'.
 * 	</p>
 * 
 * 	@author		LemilCa
 * 	@version	Base
 */
public class Commande
{
    /* -------------------------------- Arguments non constants -------------------------------- */

    private String motCommande;
    private String motParametre;


    /* ------------------------------------- Constructeurs ------------------------------------- */

	/**
	 * 	Initialise une commande a partir de deux strings specifies.
	 * 
	 * 	@param motCommande		(String) la commande utilisateur
	 * 	@param motParametre		(String) le parametre de la commande utilisateur
	 */
    public Commande (String motCommande, String motParametre)
    {
        this.motCommande  = motCommande;
        this.motParametre = motParametre;

        return;
    }


    /* ------------------------------------------ Get ------------------------------------------ */

	/**
	 * 	Renvoie l'argument 'motCommande'.
	 * 
	 * 	@return			String
	 */
    public String motCommande () { return this.motCommande; }
    
	/**
	 * 	Renvoie l'argument 'motParametre'.
	 * 
	 * 	@return			String
	 */
    public String motParametre () { return this.motParametre; }


    /* ---------------------------------------- Méthodes --------------------------------------- */

	/**
	 * 	Teste si cette commande est reconnue par le jeu.
	 * 
	 * 	@return			boolean : 'true' si pas reconnue, 'false' sinon
	 */
    public boolean estInconnue ()
    {
        return ( this.motCommande == null );
    }

	/**
	 * 	Teste si cette commande a un parametre.
	 * 
	 * 	@return			boolean : 'true' si parametre, 'false' sinon
	 */
    public boolean aParametre ()
    {
        return ( this.motParametre != null );
    }


}


