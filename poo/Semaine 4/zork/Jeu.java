

public class Jeu
{

	private AnalyseurSyntaxique analyseur;
	private Piece pieceActuelle;
	private Joueur joueur;

	/* ----------------------------------------------------------------------------------------- */

	public Jeu ()
	{
		this.analyseur = new AnalyseurSyntaxique ();
		this.pieceActuelle = creerPieces ();
		this.joueur = new Joueur ( "LemilCa", 30 );

		return;
	}

	/* ----------------------------------------------------------------------------------------- */

	public AnalyseurSyntaxique analyseur () { return this.analyseur; }
	public Piece pieceActuelle () { return this.pieceActuelle; }
	public Joueur joueur () { return this.joueur; }

	/* ----------------------------------------------------------------------------------------- */

	public Piece creerPieces ()
	{	
		ObjetZork pain = new ObjetZork ( "Pain", "Ceci est du pain", 2, true );
		ObjetZork table = new ObjetZork ( "Table", "Ceci est une table", 5, true );
		ObjetZork arme = new ObjetZork ( "Arme", "Ceci est une arme", 3, true );
		ObjetZork frigo = new ObjetZork ( "Frido", "Ceci est un frigo", 20, false );
		ObjetZork ordi = new ObjetZork ( "Ordi", "Ceci est un ordi", 12, true );


		ObjetZork [] objetsDehors = new ObjetZork [] { pain, pain, arme, pain };
		ObjetZork [] objetsSalleTD = new ObjetZork [] { ordi, ordi, table, ordi, table, table };
		ObjetZork [] objetsTaverne = new ObjetZork [] { pain, frigo, table, table, pain };
		ObjetZork [] objetsBatimentC = new ObjetZork [] { pain, arme, arme };
		ObjetZork [] objetsBureau = new ObjetZork [] { ordi, table, ordi };


		Piece dehors = new Piece ( "devant le batiment C", objetsDehors );
		Piece salleTD = new Piece ( "dans une salle de TD dans le batiment G", objetsSalleTD );
		Piece taverne = new Piece ( "dans la taverne", objetsTaverne );
		Piece batimentC = new Piece ( "dans le batiment C", objetsBatimentC );
		Piece bureau = new Piece ( "dans le secretariat", objetsBureau );

		dehors.initialiseSorties (null, salleTD, batimentC, taverne);
		salleTD.initialiseSorties(null, null, null, dehors);
		taverne.initialiseSorties(null, dehors, null, null);
		batimentC.initialiseSorties(dehors, bureau, null, null);
		bureau.initialiseSorties(null, null, null, batimentC);


		return dehors;
	}


	public void afficherMessageDeBienvenue ()
	{
		System.out.println ();
		System.out.println ( "Bienvenue dans le monde de Zork, " + this.joueur.nom () + " !" );
		System.out.println ( "Zork est un nouveau jeu d'aventure, terriblement enuyeux." );
		System.out.println ( "Tapez 'aide' si vous avez besoin d'aide." );
		System.out.println ();
		System.out.println ( this.pieceActuelle.desciptionLongue () );
		
		return;
	}

	public void afficherAideGeneral ()
	{
		System.out.println ( "Vous vous trouvez sur le campus de l'USPN." );
		System.out.println ();
		
		System.out.println ( "Les commandes reconnues sont :" );
		this.analyseur.afficherToutesLesCommandes ();
		System.out.println ();

		System.out.println ( "Si vous voulez en savoir plus sur une commande, tapez 'aide' puis la commande" );

		return;
	}

	public void afficherAidePiece ()
	{
		System.out.println ( "La commande 'piece' permet d'acceder aux informations de votre piece actuelle." );
		
		System.out.println ( "Elle peut prendre plusieurs arguments :" );
		System.out.println ( "\t- 'description' : donne la description de la piece." );
		System.out.println ( "\t- 'objets' : donne le nom et le poids de tous les objets presents dans la piece." );
		System.out.println ( "\t- 'capacite' : donne le nombre d'objets que la piece peut encore accueillir" );
		System.out.println ( "\t- 'sorties' : donne les directions vers lesquelles vous pouvez aller." );
		System.out.println ( "\t- 'tout' : donne l'ensemble des informations de la piece." );

		return;
	}
	public void afficherAideJoueur ()
	{
		System.out.println ( "La commande 'joueur' permet d'acceder a vos informations" );
		
		System.out.println ( "Elle peut prendre plusieurs arguments :" );
		System.out.println ( "\t- 'nom' : donne votre nom." );
		System.out.println ( "\t- 'objets' : donne le nom et le poids de tous les objets que votre joueur porte." );
		System.out.println ( "\t- 'capacite' : donne votre capacite restante." );
		System.out.println ( "\t- 'tout' : donne l'ensemble de vos informations." );

		return;
	}

	public void afficherAideAller ()
	{
		System.out.println ( "La commande 'aller' permet de se deplacer en dehors de votre piece actuelle." );
		System.out.println ();

		System.out.println ( "Elle peut prendre l'ensemble des directions en argument," );
		System.out.println ( "\tmais vous pourrez vous déplacer que s'il existe une porte dans cette direction." );
		System.out.println ();
		
		
		System.out.println ( "Pour connaitre les directions ou vous pouvez vous deplacer," );
		System.out.println ( "\tutilisez la commande 'piece sorties'." );

		return;
	}
	public void afficherAidePrendre ()
	{
		System.out.println ( "La commande 'prendre' permet de recuperer un objet de votre piece actuelle." );
		System.out.println ();

		System.out.println ( "Elle prend en argument le nom de l'objet que vous voulez prendre," );
		System.out.println ( "\tmais vous ne pourrez le récupérer que s'il est transportable et que vous avez la force de le prendre." );
		System.out.println ();

		System.out.println ( "Pour connaitre les objets que vous pouvez prendre," );
		System.out.println ( "\tutilisez la commande 'piece objets'." );

		return;
	}
	public void afficherAidePoser ()
	{
		System.out.println ( "La commande 'poser' permet de laisser un de vos objets dans votre piece actuelle." );
		System.out.println ();

		System.out.println ( "Elle prend en argument le nom de l'objet que vous voulez laisser," );
		System.out.println ( "\tmais vous ne pourrez le laisser que si vous le posseder et qu'il y a la place de le laisser" );
		System.out.println ();

		System.out.println ( "Pour connaitre les objets que vous avez," );
		System.out.println ( "\tutilisez la commande 'joueur objets'." );
	}

	public void afficherAideQuitter ()
	{
		System.out.println ( "La commande 'quitter' permet de quitter le jeu" );
		System.out.println ( "\tne le faites pas et restez vous amusez avec moi)." );
		System.out.println ();

		System.out.println ( "Il ne faut pas ajouter d'arguments pour la faire fonctionner." );

		return;
	}


	public void jouer ()
	{
		this.afficherMessageDeBienvenue ();

		boolean aQuitteLeJeu = false;
		while ( ! aQuitteLeJeu )
		{
			System.out.println ();
			Commande commande = this.analyseur.recupereCommande ();

			aQuitteLeJeu = this.traiterCommande (commande);
		}

		System.out.println ();
		System.out.println ( "Merci d'avoir jouer. Au revoir." );
		System.out.println ();

		return;
	}

	public boolean traiterCommande (Commande commande)
	{
		if ( commande.estInconnue () )
		{
			System.out.println ( "Je ne comprends pas ce que vous voulez..." );

			return false;
		}

		String premierMot = commande.premierMot ();

		if ( premierMot.equals ( "aide" ) )
			{ this.afficherAide (commande); return false; }
		
		
		if ( premierMot.equals ( "piece" ) )
			{ this.commandesPieces (commande); return false; }

		if ( premierMot.equals ( "joueur" ) )
			{ this.commandesJoueurs (commande); return false; }
		
		
		if ( premierMot.equals ( "aller" ) )
			{ this.deplacerVersAutrePiece (commande); return false; }

		if ( premierMot.equals ( "prendre" ) )
			{ this.prendreObjet (commande); return false; }

		if ( premierMot.equals ( "poser" ) )
			{ this.poserObjet (commande); return false; }
		

		if ( ! premierMot.equals ( "quitter" ) )
			{ System.out.println ( "Ceci n'est pas une commande" ); return false; }
		
		
		if ( ! commande.aDeuxiemeMot () ) return true;


		System.out.println ( "Quitter quoi ?" );

		return false;
	}


	public void afficherAide (Commande commande)
	{
		if ( ! commande.aDeuxiemeMot () )
			{ this.afficherAideGeneral (); return; }

		String deuxiemeMot = commande.deuxiemeMot ();

		if ( deuxiemeMot.equals ( "aide" ) )
			{ System.out.println ( "La commande 'aide' est assez simple pour que tu la comprennes par toi-meme" ); return; }

		
		if ( deuxiemeMot.equals ( "piece" ) )
			{ this.afficherAidePiece (); return; }

		if ( deuxiemeMot.equals ( "joueur" ) )
			{ this.afficherAideJoueur (); return; }
		
		
		if ( deuxiemeMot.equals ( "aller" ) )
			{ this.afficherAideAller (); return; }

		if ( deuxiemeMot.equals ( "prendre" ) )
			{ this.afficherAidePrendre (); return; }
		
		if ( deuxiemeMot.equals ( "poser" ) )
			{ this.afficherAidePoser (); return; }
		
		
		if ( deuxiemeMot.equals ( "quitter" ) )
			{ this.afficherAideQuitter (); return; }


		System.out.println ( "Je ne sais pas quelle aide tu demandes, donc voici l'aide general :" );
		this.afficherAideGeneral ();

		return;
	}


	public void commandesPieces (Commande commande)
	{
		if ( ! commande.aDeuxiemeMot () )
			{ this.afficherAidePiece (); return; }
		
		String deuxiemeMot = commande.deuxiemeMot ();

		if ( deuxiemeMot.equals ( "description" ) )
			{ System.out.println ( this.pieceActuelle.desciptionLongue () ); return; }

		if ( deuxiemeMot.equals ( "objets" ) )
			{ System.out.println ( this.pieceActuelle.descriptionObjets () ); return; }

		if ( deuxiemeMot.equals ( "capacite" ) )
			{ System.out.println ( this.pieceActuelle.descriptionCapacite () ); return; }
		
		if ( deuxiemeMot.equals ( "sorties" ) )
			{ System.out.println ( this.pieceActuelle.descriptionSorties () ); return; }
		
		if ( deuxiemeMot.equals ( "tout" ) )
			{ System.out.println ( this.pieceActuelle.descriptionTotale () ); return; }
		

		this.afficherAidePiece ();

		return;
	}

	public void commandesJoueurs (Commande commande)
	{
		if ( ! commande.aDeuxiemeMot () )
			{ this.afficherAideJoueur (); return; }

		String deuxiemeMot = commande.deuxiemeMot ();

		if ( deuxiemeMot.equals ( "nom" ) )
			{ System.out.println ( this.joueur.descriptionNom () ); return; }

		if ( deuxiemeMot.equals ( "objets" ) )
			{ System.out.println ( this.joueur.descriptionObjets () ); return; }

		if ( deuxiemeMot.equals ( "capacite" ) )
			{ System.out.println ( this.joueur.descriptionCapacite () ); return; }

		if ( deuxiemeMot.equals ( "tout" ) )
			{ System.out.println ( this.joueur.descriptionTotale () ); return; }
		

		this.afficherAideJoueur ();

		return;
	}


	public void deplacerVersAutrePiece (Commande commande)
	{
		if ( ! commande.aDeuxiemeMot () )
			{ this.afficherAideAller (); return; }

		String stringDirection = commande.deuxiemeMot ();
		Direction direction = null;

		try { direction = Direction.valueOf (stringDirection); }
		catch ( IllegalArgumentException exception )
		{
			System.out.println ( "Pour indiquer une direction, entrez une des chaines de caracteres suivantes :" );

			for ( Direction directionPossible : Direction.values () )
				System.out.println ( "-> \"" + directionPossible + "\"" );

			return;
		}

		Piece pieceSuivante = this.pieceActuelle.pieceSuivante (direction);

		if ( pieceSuivante == null )
			{ System.out.println ( "Il n'y a pas de piece dans cette direction !" ); return; }
		
		this.pieceActuelle = pieceSuivante;
		this.joueur.seDeplace (pieceSuivante);

		System.out.println ( this.pieceActuelle.desciptionLongue () );

		return;
	}


	public void prendreObjet (Commande commande)
	{
		if ( ! commande.aDeuxiemeMot () )
			{ this.afficherAidePrendre (); return; }

		String stringObjet = commande.deuxiemeMot ();
		int indiceObjet = this.peutPrendreObjet (stringObjet);

		if ( indiceObjet < 0 )
			{ System.out.println ( "Vous ne pouvez pas prendre l'objet : " + stringObjet ); return; }

		ObjetZork objet = this.pieceActuelle.objet (indiceObjet);

		this.pieceActuelle.enleveObjet (indiceObjet);
		this.joueur.ajouteObjet (objet);

		System.out.println ( "Vous avez pris l'objet : " + stringObjet );

		return;
	}

	public int peutPrendreObjet (String stringObjet)
	{
		if ( this.pieceActuelle.objets () == null ) return -1;

		int indiceObjet = 0;
		for ( ObjetZork objet : this.pieceActuelle.objets () )
		{
			if ( objet != null && objet.transportable () && stringObjet.equals ( objet.nom () ) )
				if ( this.joueur.capaciteRestante () - objet.poids () >= 0 )
					return indiceObjet;

			indiceObjet += 1;
		}

		return -1;
	}


	public void poserObjet (Commande commande)
	{
		if ( ! commande.aDeuxiemeMot () )
			{ this.afficherAidePoser (); return; }

		String stringObjet = commande.deuxiemeMot ();
		int indiceObjet = this.peutPoserObjet (stringObjet);

		if ( indiceObjet < 0 )
			{ System.out.println ( "Vous ne pouvez pas poser l'objet : " + stringObjet ); return; }

		ObjetZork objet = this.joueur.objet (indiceObjet);

		this.joueur.enleveObjet (indiceObjet);
		this.pieceActuelle.ajouteObjet (objet);

		System.out.println ( "Vous avez pose l'objet : " + stringObjet );
	}

	public int peutPoserObjet (String stringObjet)
	{
		if ( this.joueur.objets () == null ) return -1;

		int indiceObjet = 0;
		for ( ObjetZork objet : this.joueur.objets () )
		{
			if ( objet != null && stringObjet.equals ( objet.nom () ) )
				if ( this.pieceActuelle.nbObjets () != this.pieceActuelle.objets ().length )
					return indiceObjet;

			indiceObjet += 1;
		}

		return -1;
	}

}