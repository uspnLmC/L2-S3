//	Base


/**
 * 	Classe principale du jeu "Zork".
 * 
 * 	<p>
 * 		Cette classe cree et initialise des instances de toutes les autres classes :
 * 		<ul>
 * 			<li> Cree l'analyseur syntaxique. </li>
 * 			<li> Cree toutes les pieces. </li>
 * 			<li> Demarre le jeu. </li>
 * 		</ul>
 * 		Elle se charge aussi d'executer les commandes que lui renvoie l'analyseur syntaxique.
 * 	</p>
 * 
 * 	@author		LemilCa
 * 	@version	Base
 */
public class Jeu
{
    /* -------------------------------- Arguments non constants -------------------------------- */

    private AnalyseurSyntaxique analyseur;
    private Piece               pieceActuelle;


    /* ------------------------------------- Constructeurs ------------------------------------- */

	/**
	 * 	Cree le jeu et initialise la carte du jeu.
	 */
    public Jeu ()
    {
        this.analyseur     = new AnalyseurSyntaxique ();
        this.pieceActuelle = this.creerPieces ();

        return;
    }


    /* ------------------------------------------ Get ------------------------------------------ */

	/**
	 * 	Renvoie l'argument 'analyseur'.
	 * 	
	 * 	@return 		AnalyseurSyntaxique
	 */
    public AnalyseurSyntaxique analyseur () { return this.analyseur; }

	/**
	 * 	Renvoie l'argument 'pieceActuelle'.
	 * 	
	 * 	@return 		Piece
	 */
    public Piece pieceActuelle () { return this.pieceActuelle; }


    /* ---------------------------------------- Méthodes --------------------------------------- */

	/**
	 * 	Cree toutes les pieces et relie leurs sorties les unes aux autres.
	 * 
	 * 	@return			Piece : piece de depart
	 */
    public Piece creerPieces ()
    {
        Piece dehors    = new Piece ( "devant le batiment C"                    );
		Piece salleTD   = new Piece ( "dans une salle de TD dans le batiment G" );
		Piece taverne   = new Piece ( "dans la taverne"                         );
		Piece batimentC = new Piece ( "dans le batiment C"                      );
		Piece bureau    = new Piece ( "dans le secretariat"                     );

        dehors.initialiserSorties    ( null  , salleTD, batimentC, taverne   );
		salleTD.initialiserSorties   ( null  , null   , null     , dehors    );
		taverne.initialiserSorties   ( null  , dehors , null     , null      );
		batimentC.initialiserSorties ( dehors, bureau , null     , null      );
		bureau.initialiserSorties    ( null  , null   , null     , batimentC );

        return dehors;
    }

						/* -------------------------------------------- */

	/**
	 * 	Affiche le message de bienvenue pour le joueur.
	 */
	public void afficherMessageBienvenue ()
    {
        System.out.println ();
		
		System.out.println ("Bienvenue dans le monde de Zork !");
		System.out.println ("Zork est un nouveau jeu d'aventure, terriblement enuyeux.");
		System.out.println ("Tapez 'aide' si vous avez besoin d'aide.");
		System.out.println ();
		
		System.out.println ( this.pieceActuelle.descriptionLongue () );

		return;
    }


	/**
	 * 	Affiche l'aide generale.
	 */
    public void afficherAide ()
	{
		System.out.println ("Vous vous trouvez sur le campus de l'USPN.");
		System.out.println ();
		
		System.out.println ("Les commandes reconnues sont :");
		this.analyseur.afficherCommandes ();
		System.out.println ();

		System.out.println ("Toute commande est de la forme : commande parametre (parametre optionnel).");
		System.out.println ("Si vous voulez en savoir plus sur une commande, tapez 'aide' puis la commande en parametre.");

		return;
	}

	/**
	 * 	Affiche l'aide des pieces.
	 */
    public void afficherAidePiece ()
	{
		System.out.println ("La commande 'piece' permet d'acceder aux informations de votre piece actuelle.");
		System.out.println ();
		
		System.out.println ("Elle peut prendre plusieurs arguments :");
		System.out.println ("\t- 'description' : donne la description de la piece.");
		System.out.println ("\t- 'sorties'     : donne les directions vers lesquelles vous pouvez aller depuis la piece.");
		System.out.println ("\t- 'global'      : donne l'ensemble des informations de la piece.");

		return;
	}


	/**
	 * 	Affiche l'aide pour se deplacer.
	 */
    public void afficherAideAller ()
	{
		System.out.println ("La commande 'aller' permet de se deplacer en dehors de votre piece actuelle.");
		System.out.println ();

		System.out.println ("Elle peut prendre l'ensemble des directions en argument,");
		System.out.println ("\tmais vous pourrez vous déplacer que s'il existe une porte dans cette direction.");
		System.out.println ();
		
		
		System.out.println ("Pour connaitre les directions ou vous pouvez vous deplacer,");
		System.out.println ("\tutilisez la commande 'piece sorties'.");

		return;
	}


	/**
	 * 	Affiche l'aide pour quitter le jeu.
	 */
    public void afficherAideQuitter ()
	{
		System.out.println ("La commande 'quitter' permet de quitter le jeu.");
		System.out.println ();

		System.out.println ("Il ne faut pas ajouter d'arguments pour la faire fonctionner.");

		return;
	}

						/* -------------------------------------------- */
	
	/**
	 * 	Pour lancer ce jeu.
	 * 	Boucle jusqu'a ce que l'utilisateur quitte ce jeu.
	 */
	public void jouer ()
	{
		this.afficherMessageBienvenue ();

		boolean aQuitteLeJeu = false;
		while ( ! aQuitteLeJeu )
		{
			System.out.println ();

			Commande commande = this.analyseur.recupererCommande ();
			aQuitteLeJeu      = this.traiterCommande (commande);
		}

		System.out.println ();
		
		System.out.println ("Merci d'avoir jouer. Au revoir.");
		System.out.println ();

		return;
	}


	/**
	 * 	Execute la commande specifiee.
	 * 
	 * 	@param 	commande 	(String) la commande a executer
	 * 	
	 * 	@return				boolean : 'true' si cette commande termine le jeu ; 'false' sinon
	 */
    public boolean traiterCommande (Commande commande)
	{
		if ( commande.estInconnue () )
			{ System.out.println ("Je ne comprends pas ce que vous voulez..."); return false; }
		
		String motCommande = commande.motCommande ();

		if ( motCommande.equals ("aide") )
			{ this.commandeAide (commande); return false; }


		if ( motCommande.equals ("piece") )
			{ this.commandePiece (commande); return false; }


		if ( motCommande.equals ("aller") )
			{ this.commandeAller (commande); return false; }


		if ( ! motCommande.equals ("quitter") )
			{ System.out.println ("Ceci n'est pas une commande."); return false; }

		if ( ! commande.aParametre () ) return true;


		System.out.println ( "Quitter quoi ?" );

		return false;
	}


	/**
	 * 	Execute la commande 'aide' en fonction du parametre.
	 * 	<ul>
	 * 		<li> 'aide'    : affiche un texte de base </li>
	 * 		<li> 'piece'   : affiche l'aide de la piece </li>
	 * 		<li> 'aller'   : affiche l'aide pour se deplacer </li>
	 * 		<li> 'quitter' : affiche l'aide pour quitter le jeu </li>
	 * 	</ul>
	 * 	Si aucun parametre ou un parametre non reconnu, affiche l'aide generale.
	 * 	
	 * 	@param 			commande	(Commande) la commande a executer (utile pour le parametre)
	 */
    public void commandeAide (Commande commande)
	{
		if ( ! commande.aParametre () )
			{ this.afficherAide (); return; }

		String motParametre = commande.motParametre ();

		if ( motParametre.equals ("aide") )
			{ System.out.println ("La commande 'aide' est assez simple pour que vous la comprenniez par vous-meme."); return; }

		
		if ( motParametre.equals ("piece") )
			{ this.afficherAidePiece (); return; }


		if ( motParametre.equals ("aller") )
			{ this.afficherAideAller (); return; }


		if ( motParametre.equals ("quitter") )
			{ this.afficherAideQuitter (); return; }


		System.out.println ("Je ne sais pas quelle aide vous demandez, donc voici l'aide general :");
		this.afficherAide ();

		return;
	}


	/**
	 * 	Execute la commande 'piece' en fonction du parametre.
	 * 	<ul>
	 * 		<li> 'description' : affiche la description de la piece actuelle. </li>
	 * 		<li> 'sorties'     : affiche les sorties de la piece actuelle. </li>
	 * 		<li> 'global'      : affiche l'ensemble des informations de la piece. </li>
	 * 	</ul>
	 * 	Si aucun parametre ou un parametre non reconnu, affiche l'aide des pieces.
	 * 	
	 * 	@param 			commande	(Commande) la commande a executer (utile pour le parametre)
	 */
    public void commandePiece (Commande commande)
	{
		if ( ! commande.aParametre () )
			{ this.afficherAidePiece (); return; }

		String motParametre = commande.motParametre ();

		if ( motParametre.equals ("description") )
			{ System.out.println ( this.pieceActuelle.descriptionLongue () ); return; }

		if ( motParametre.equals ("sorties") )
			{ System.out.println ( this.pieceActuelle.descriptionSorties () ); return; }

		if ( motParametre.equals ("global") )
			{ System.out.println ( this.pieceActuelle.descriptionGlobale () ); return; }


		this.afficherAidePiece ();

		return;
	}


	/**
	 * 	Execute la commande 'aller' en fonction du parametre.
	 * 	<p>
	 * 		Si le parametre est une direction reconnue par ce jeu : deplace dans cette direction
	 * 	</p>
	 * 	Si aucun parametre ou un parametre non reconnu, affiche l'aide pour se deplacer.
	 * 	
	 * 	@param 			commande	(Commande) la commande a executer (utile pour le parametre)
	 */
    public void commandeAller (Commande commande)
	{
		if ( ! commande.aParametre () )
			{ this.afficherAideAller (); return; }

		String parametreDirection = commande.motParametre ();
		Direction direction       = null;

		try { direction = Direction.valueOf (parametreDirection); }
		catch ( IllegalArgumentException exception )
		{
			System.out.println ("Pour indiquer une direction, entrez une des chaines de caracteres suivantes :");

			for ( Direction directionPossible : Direction.values () )
				System.out.println ( "-> \"" + directionPossible + "\"" );

			return;
		}

		Piece pieceSuivante = this.pieceActuelle.pieceSuivante (direction);

		if ( pieceSuivante == null )
			{ System.out.println ("Il n'y a pas de piece dans cette direction !"); return; }

		this.pieceActuelle = pieceSuivante;
		System.out.println ( this.pieceActuelle.descriptionLongue () );

		return;
	}

    
}


