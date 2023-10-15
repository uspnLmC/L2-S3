//	Semaine_5


/**										<p> MODIFICATION !!! </p>
 * 	Classe principale du jeu "Zork".
 * 
 * 	<p>
 * 		Cette classe cree et initialise des instances de toutes les autres classes :
 * 		<ul>
 * 			<li> Cree l'analyseur syntaxique. </li>
 * 			<li> Cree toutes les pieces et leurs objets. </li>
 * 			<li> Demarre le jeu. </li>
 * 		</ul>
 * 		Elle se charge aussi d'executer les commandes que lui renvoie l'analyseur syntaxique.
 * 	</p>
 * 
 * 	@author		LemilCa
 * 	@version	Semaine_5 from Semaine_4
 */
public class Jeu
{
    /* -------------------------------- Arguments non constants -------------------------------- */

    private AnalyseurSyntaxique analyseur;
    private Piece               pieceActuelle;
	private Joueur              joueur;


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

	/**	
	 * 	Cree le jeu avec un joueur specifie.
	 * 
	 * 	@param joueur	Joueur
	 */
	public Jeu (Joueur joueur)
    {
        this.analyseur     = new AnalyseurSyntaxique ();
        this.pieceActuelle = this.creerPieces ();
        
		joueur.deplacer ( this.pieceActuelle );
		this.joueur        = joueur;

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

	/**									<p> AJOUT !!! </p>
	 * 	Renvoie l'argument 'joueur'.
	 * 
	 * 	@return			Joueur
	 */
	public Joueur joueur () { return this.joueur; };


    /* ---------------------------------------- Méthodes --------------------------------------- */

	/**									<p> MODIFICATION !!! </p>
	 * 	Cree toutes les pieces et tous les objets, et relie leurs sorties les unes aux autres.
	 * 
	 * 	@return			Piece : piece de depart
	 */
    public Piece creerPieces ()
    {
        ObjetZork pain    = new ObjetZork ( "Pain"      , "Tu peux le manger"            , 2  , true  );
		ObjetZork table   = new ObjetZork ( "Table"     , "Tu peux y mettre des choses"  , 5  , true  );
		ObjetZork arbre   = new ObjetZork ( "Arbre"     , "Il est tres beau"             , 100, false );
		ObjetZork ordi    = new ObjetZork ( "Ordinateur", "Tu peux y jouer a Zork"       , 4  , true  );
		ObjetZork tableau = new ObjetZork ( "Tableau"   , "Tu peux y dessiner des choses", 7  , false );

		Piece dehors    = new Piece ( "devant le batiment C"                   , 6 );
		Piece salleTD   = new Piece ( "dans une salle de TD dans le batiment G", 8 );
		Piece taverne   = new Piece ( "dans la taverne"                        , 6 );
		Piece batimentC = new Piece ( "dans le batiment C"                     , 4 );
		Piece bureau    = new Piece ( "dans le secretariat"                    , 6 );

		dehors.initialiserObjets    ( arbre, arbre, pain                         );
		salleTD.initialiserObjets   ( table, table, ordi   , tableau, pain       );
		taverne.initialiserObjets   ( pain , table, pain   , table  , pain, ordi );
		batimentC.initialiserObjets ( table, table, ordi   , pain                );
		bureau.initialiserObjets    ( table, ordi , tableau, arbre               );

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
		System.out.println ("\t- 'objets'      : donne la description des objets de la piece." );
		System.out.println ("\t- 'capacite'    : donne la description des informations liees a la capacite de la piece.");
		System.out.println ("\t- 'sorties'     : donne les directions vers lesquelles vous pouvez aller depuis la piece.");
		System.out.println ("\t- 'global'      : donne l'ensemble des informations de la piece.");

		return;
	}

	/**	
	 * 	Afficher l'aide du joueur
	 */
	public void afficherAideJoueur ()
    {
        System.out.println ("La commande 'joueur' permet d'acceder a vos informations.");
		System.out.println ();
		
		System.out.println ("Elle peut prendre plusieurs parametres :");
		System.out.println ("\t- 'pseudo'     : donne votre pseudo.");
		System.out.println ("\t- 'inventaire' : donne la description de votre inventaire.");
		System.out.println ("\t- 'capacite'   : donne les informations liees a la capacites de votre inventaire.");
		System.out.println ("\t- 'global'     : donne l'ensemble de vos informations.");

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
	 * 	Affiche l'aide pour prendre un objet.
	 */
	public void afficherAidePrendre ()
	{
		System.out.println ("La commande 'prendre' vous permet de recuperer un objet de la piece actuelle.");
		System.out.println ();

		System.out.println ("Elle peut prendre n'importe quel nom d'objet en parametre,");
		System.out.println ("\tmais vous ne pourrez le prendre que s'il vous reste assez de place et assez de force et qu'il soit transportable.");
		System.out.println ();
		
		
		System.out.println ("Pour connaitre les objets que vous pouvez recuperer,");
		System.out.println ("\tutilisez la commande 'piece objets'.");
		System.out.println ("Pour connaitre la capacite qu'il vous reste,");
		System.out.println ("\tutilisez la commande 'joueur capacite'.");

		return;
	}

	/**	
	 * 	Affiche l'aide pour poser un objet.
	 */
    public void afficherAidePoser ()
	{
		System.out.println ("La commande 'poser' vous permet de laisser un objet dans la piece actuelle.");
		System.out.println ();

		System.out.println ("Elle peut prendre n'importe quel nom d'objet en parametre,");
		System.out.println ("\tmais vous ne pourrez le laisser que si vous le possedez et qu'il y a la place dans la piece.");
		System.out.println ();
		
		
		System.out.println ("Pour connaitre les objets que vous pouvez laisser,");
		System.out.println ("\tutilisez la commande 'joueur objets'.");

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
		
		if ( motCommande.equals ("joueur") )
			{ this.commandeJoueur (commande); return false; }


		if ( motCommande.equals ("aller") )
			{ this.commandeAller (commande); return false; }
		
		if ( motCommande.equals ("prendre") )
			{ this.commandePrendre (commande); return false; }

		if ( motCommande.equals ("poser") )
			{ this.commandePoser (commande); return false; }


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
	 * 		<li> 'joueur'  : affiche l'aide du joueur </li>
	 * 		<li> 'aller'   : affiche l'aide pour se deplacer </li>
	 * 		<li> 'prendre' : affiche l'aide pour prendre un objet </li>
	 * 		<li> 'poser'   : affiche l'aide pour poser un objet </li>
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

        if ( motParametre.equals ("joueur") )
            { this.afficherAideJoueur (); return; }


		if ( motParametre.equals ("aller") )
			{ this.afficherAideAller (); return; }
        
        if ( motParametre.equals ("prendre") )
            { this.afficherAidePrendre (); return; }
        
        if ( motParametre.equals ("poser") )
            { this.afficherAidePoser (); return; }


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
	 * 		<li> 'objets'      : affiche la description des objets de la piece actuelle. </li>
	 * 		<li> 'capacite'    : affiche les informations liees a la capacite de la piece actuelle </li>
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

		if ( motParametre.equals ("objets") )
			{ System.out.println ( this.pieceActuelle.descriptionObjets () ); return; }

        if ( motParametre.equals ("capacite") )
            { System.out.println ( this.pieceActuelle.descriptionCapacite () ); return; }

		if ( motParametre.equals ("sorties") )
			{ System.out.println ( this.pieceActuelle.descriptionSorties () ); return; }

		if ( motParametre.equals ("global") )
			{ System.out.println ( this.pieceActuelle.descriptionGlobale () ); return; }


		this.afficherAidePiece ();

		return;
	}

	/**	
	 * 	Execute la commande 'joueur' en fonction du parametre.
	 * 	<ul>
	 * 		<li> 'pseudo'     : affiche le pseudo du joueur. </li>
	 * 		<li> 'inventaire' : affiche la description de l'inventaire du joueur. </li>
	 * 		<li> 'capacite'   : affiche les informations liees a la capacite du joueur. </li>
	 * 		<li> 'global'     : affiche l'ensemble des informations du joueur. </li>
	 * 	</ul>
	 * 	Si aucun parametre ou un parametre non reconnu, affiche l'aide du joueur.
	 * 	
	 * 	@param commande		(Commande) la commande a executer (utile pour le parametre)
	 */
	public void commandeJoueur (Commande commande)
	{
		if ( ! commande.aParametre () )
			{ this.afficherAideJoueur (); return; }

		String motParametre = commande.motParametre ();

		if ( motParametre.equals ("pseudo") )
			{ System.out.println ( this.joueur.descriptionPseudo () ); return; }

		if ( motParametre.equals ("inventaire") )
			{ System.out.println ( this.joueur.descriptionInventaire () ); return; }

		if ( motParametre.equals ("capacite") )
			{ System.out.println ( this.joueur.descriptionCapacite () ); return; }

		if ( motParametre.equals ("global") )
			{ System.out.println ( this.joueur.descriptionGlobale () ); return; }


		this.afficherAideJoueur ();

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

	/**									<p> MODIFICATION !!! </p>
	 * 	Execute la commande 'prendre' en fonction du parametre.
	 * 	<p>
	 * 		Si le parametre est le nom d'un objet present dans la piece, que l'objet est transportable et si le joueur a la capacite de le prendre : joueur recupere l'objet
	 * 	</p>
	 * 	Si aucun parametre ou un parametre non reconnu, affiche l'aide pour prendre.
	 * 	
	 * 	@param commande	(Commande) la commande a executer (utile pour le parametre)
	 */
	public void commandePrendre (Commande commande)
	{
		if ( ! commande.aParametre () )
			{ this.afficherAidePrendre (); return; }

		String parametreObjet = commande.motParametre ();
		ObjetZork objet       = null;
		int indice            = 0;

		boolean pasTrouve = true;
		while ( indice < this.pieceActuelle.objetsCapaciteActuelle () && pasTrouve )
		{
			objet = this.pieceActuelle.objet (indice);

			if ( parametreObjet.equals ( objet.nom () ) && objet.estTransportable () )
				if ( this.joueur.capaciteTransport () - objet.poids () >= 0 ) pasTrouve = false;
			
			if ( pasTrouve ) indice += 1;
		}

		if ( pasTrouve )
			{ System.out.println ( "Vous ne pouvez pas prendre l'objet : " + parametreObjet ); return; }

		this.joueur.ajouterObjet        (      objet);
		this.pieceActuelle.retirerObjet (indice     );

		System.out.println ( "Vous avez pris l'objet : " + parametreObjet );

		return;
	}

	/**									<p> AJOUT !!! </p>
	 * 	Execute la commande 'poser' en fonction du parametre.
	 * 	<p>
	 * 		Si le parametre est le nom d'un objet que le joueur possede, et s'il peut le deposer dans la piece : joueur depose l'objet
	 * 	</p>
	 * 	Si aucun parametre ou un parametre non reconnu, affiche l'aide pour poser.
	 * 	
	 * 	@param commande	(Commande) la commande a executer (utile pour le parametre)
	 */
	public void commandePoser (Commande commande)
	{
		if ( ! commande.aParametre () )
			{ this.afficherAidePrendre (); return; }

		String parametreObjet = commande.motParametre ();
		ObjetZork objet       = null;
		int indice            = 0;

		boolean pasTrouve = true;
		while ( indice < this.joueur.inventaireCapaciteActuelle () && pasTrouve )
		{
			objet = this.joueur.objet (indice);

			if ( parametreObjet.equals ( objet.nom () ) ) pasTrouve = false;
			else indice += 1;
		}

		if ( pasTrouve )
			{ System.out.println ( "Vous ne pouvez pas poser l'objet : " + parametreObjet ); return; }

		this.pieceActuelle.ajouterObjet (        objet);
		this.joueur.retirerObjet        (indice, objet);

		System.out.println ( "Vous avez pose l'objet : " + parametreObjet );

		return;
	}

    
}


