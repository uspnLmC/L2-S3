

public class Jeu
{
	private final static int POIDS_MAX = 20;

	private AnalyseurSyntaxique analyseurSyntaxique;
	private Piece pieceActuelle;
	private int poidsActuel;


	public Jeu ()
	{
		this.analyseurSyntaxique = new AnalyseurSyntaxique ();
		creerPieces ();
		this.poidsActuel = 0;

		return;
	}
	public void creerPieces ()
	{
		ObjetZork pain, table, arme, frigo, ordi;
		
		pain = new ObjetZork ( "Pain", "Ceci est du pain", 2, true );
		table = new ObjetZork ( "Table", "Ceci est une table", 5, true );
		arme = new ObjetZork ( "Arme", "Ceci est une arme", 3, true );
		frigo = new ObjetZork ( "Frido", "Ceci est un frigo", 20, false );
		ordi = new ObjetZork ( "Ordi", "Ceci est un ordi", 12, true );
		
		ObjetZork [] objetsDehors = new ObjetZork [] { pain, pain, arme, pain };
		ObjetZork [] objetsSalleTD = new ObjetZork [] { ordi, ordi, table, ordi, table, table };
		ObjetZork [] objetsTaverne = new ObjetZork [] { pain, frigo, table, table, pain };
		ObjetZork [] objetsBatimentC = new ObjetZork [] { pain, arme, arme };
		ObjetZork [] objetsBureau = new ObjetZork [] { ordi, table, ordi };

		Piece dehors, salleTD, taverne, batimentC, bureau;

		dehors = new Piece ( "devant le batiment C", objetsDehors );
		salleTD = new Piece ( "dans une salle de TD dans le batiment G", objetsSalleTD );
		taverne = new Piece ( "dans la taverne", objetsTaverne );
		batimentC = new Piece ( "dans le batiment C", objetsBatimentC );
		bureau = new Piece ( "dans le secrétariat", objetsBureau );

		// Piece dehors, salleTD, taverne, batimentC, bureau;

		// dehors = new Piece ( "devant le batiment C", 8 );
		// salleTD = new Piece ( "dans une salle de TD dans le batiment G", 5 );
		// taverne = new Piece ( "dans la taverne", 5 );
		// batimentC = new Piece ( "dans le batiment C", 5 );
		// bureau = new Piece ( "dans le secrétariat", 6 );


		// dehors.setObjets (pain, pain, arme, pain);
		// salleTD.setObjets (ordi, ordi, table, ordi, table, table);
		// taverne.setObjets (pain, frigo, table, table, pain);
		// batimentC.setObjets(pain, arme, arme);
		// bureau.setObjets(ordi, table, ordi);

		dehors.setSorties (null, salleTD, batimentC, taverne);
		salleTD.setSorties (null, null, null, dehors);
		taverne.setSorties (null, dehors, null, null);
		batimentC.setSorties (dehors, bureau, null, null);
		bureau.setSorties (null, null, null, batimentC);

		
		this.pieceActuelle = dehors;

		return;
	}


	public AnalyseurSyntaxique analyseurSyntaxique () { return this.analyseurSyntaxique; }
	public Piece pieceActuelle () { return this.pieceActuelle; }


	public void jouer ()
	{
		this.afficherMessageDeBienvenue ();

		boolean aQuitte = false;
		while ( ! aQuitte )
		{
			System.out.println ();
			Commande commande = this.analyseurSyntaxique.recupereCommande ();

			aQuitte = this.traiterCommande (commande);
		}

		System.out.println ();
		System.out.println ( "Merci d'avoir jouer. Au revoir." );
		System.out.println ();

		return;
	}

	public void afficherMessageDeBienvenue ()
	{
		System.out.println ();
		System.out.println ( "Bienvennue dans le monde de Zork !" );
		System.out.println ( "Zork est un nouveau jeu d'aventure, terriblement enuyeux." );
		System.out.println ( "Tapez 'aide' si vous avez besoin d'aide." );
		System.out.println ();
		System.out.println ( this.pieceActuelle.descriptionLongue () );
		
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
			{ this.commandesPiece (commande); return false; }
		
		if ( premierMot.equals ( "poids" ) )
			{ this.commandesPoids (commande); return false; }
		

		if ( premierMot.equals ( "aller" ) )
			{ this.deplacerVersAutrePiece (commande); return false; }
		
		if ( premierMot.equals ( "prendre" ) )
			{ this.prendreObjet (commande); return false; }
		

		if ( ! premierMot.equals ( "quitter" ))
			{ System.out.println ( "Ceci n'est pas une commande." ); return false; }


		if ( ! commande.aDeuxiemeMot () )
			return true;
		
		
		System.out.println ( "Quitter quoi ?" );

		return false;
	}


	public void afficherAide ( Commande commande )
	{
		if ( ! commande.aDeuxiemeMot () )
			{ this.afficherAideGeneral (); return; }


		String deuxiemeMot = commande.deuxiemeMot ();

		if ( deuxiemeMot.equals ( "aide" ) )
			{ System.out.println ( "La commande aide est assez simple pour que tu la comprennes sans avoir besoin d'aide je pense." ); return; }

		if ( deuxiemeMot.equals ( "piece" ) )
			{ this.afficherAidePiece (); return; }
		
		if ( deuxiemeMot.equals ( "poids" ) )
			{ this.afficherAidePoids (); return; }
		
		
		if ( deuxiemeMot.equals ( "aller" ) )
			{ this.afficherAideAller (); return; }

		if ( deuxiemeMot.equals ( "prendre" ) )
			{ this.afficherAidePrendre (); return; }
		

		if ( deuxiemeMot.equals ( "quitter" ) )
			{ this.afficherAideQuitter (); return; }
		
		
		System.out.println ( "Je sais pas quelle aide tu veux voir donc je te montre les commandes que tu peux faire." );
		this.afficherAideGeneral ();

		return;
	}
	
	public void afficherAideGeneral ()
	{
		System.out.println ( "Vous vous trouvez sur le campus de l'USPN." );
		System.out.println ();
		
		System.out.println ( "Les commandes reconnues sont :" );
		this.analyseurSyntaxique.afficherToutesLesCommandes ();
		System.out.println ();

		System.out.println ( "Si vous voulez en savoir plus sur une des commandes, tapez 'aide' puis la commande" );

		return;
	}


	public void afficherAidePiece ()
	{
		System.out.println ( "La commande 'piece' permet d'acceder aux informations de votre piece actuelle." );
		
		System.out.println ( "Elle peut prendre plusieurs arguments :" );
		System.out.println ( "\t- 'description' : donne la description de la piece." );
		System.out.println ( "\t- 'objets' : donne les objets présents dans la pièce (sera sous la forme <nomObjet, poidsObjet>)" );
		System.out.println ( "\t- 'sorties' : donne les directions vers lesquelles vous pouvez aller." );
		System.out.println ( "\t- 'tout' : donne l'ensemble des informations de la piece." );

		return;
	}

	public void commandesPiece (Commande commande)
	{
		if ( ! commande.aDeuxiemeMot () )
			{ this.afficherAidePiece (); return; }
		
		String deuxiemeMot = commande.deuxiemeMot ();

		if ( deuxiemeMot.equals ( "description" ) )
			{ System.out.println ( this.pieceActuelle.description () ); return; }
		
		if ( deuxiemeMot.equals ( "objets" ) )
			{ System.out.println ( this.pieceActuelle.descriptionObjets () ); return; }

		if ( deuxiemeMot.equals ( "sorties" ) )
			{ System.out.println ( this.pieceActuelle.descriptionSorties () ); return; }
		
		if ( deuxiemeMot.equals ( "tout" ) )
		{
			System.out.println ( this.pieceActuelle.descriptionLongue () );

			return;
		}
		
		this.afficherAidePiece ();
		
		return;
	}


	public void afficherAidePoids ()
	{
		System.out.println ( "La commande 'poids' permet de voir les informations à propos du poids." );

		System.out.println ( "Elle peut prendre plusieurs arguments :" );
		System.out.println ( "\t- 'actuel' : donne le poids que vous portez actuellement" );
		System.out.println ( "\t- 'max' : donne le poids que vous pouvez porter au maximum" );
		System.out.println ( "\t- 'tout' : donne l'ensemble des informations sur le poids" );
		
		return;
	}

	public void commandesPoids (Commande commande)
	{
		if ( ! commande.aDeuxiemeMot () )
			{ this.afficherAidePoids (); return; }
		
		String deuxiemeMot = commande.deuxiemeMot ();

		if ( deuxiemeMot.equals ( "actuel" ) )
			{ System.out.println ( "Vous portez pour un poids de " +  this.poidsActuel ); return; }

		if ( deuxiemeMot.equals ( "max" ) )
			{ System.out.println ( "Vous pouvez porter un poids maximal de " + POIDS_MAX ); return; }
		
		if ( deuxiemeMot.equals ( "tout" ) )
			{ System.out.println ( "Vous portez pour un poids de " + this.poidsActuel + " sur " + POIDS_MAX + " possible" ); return; }
		
		this.afficherAidePoids ();

		return;
	}


	public void afficherAideAller ()
	{
		System.out.println ( "La commande 'aller' permet de se déplacer en dehors de votre piece actuelle." );
		System.out.println ();

		System.out.println ( "Elle peut prendre l'ensemble des directions en argument," );
		System.out.println ( "\tmais vous pourrez vous déplacer que s'il existe une porte dans cette direction." );
		System.out.println ();
		
		
		System.out.println ( "Pour connaître les directions où vous pouvez vous déplacer, utilisez la commande 'piece sorties'" );

		return;
	}

	public void deplacerVersAutrePiece (Commande commande)
	{
		if ( ! commande.aDeuxiemeMot () )
			{ this.afficherAideAller (); return; }
		

		String stringDirection = commande.deuxiemeMot ();
		Direction direction = null;

		try { direction = Direction.valueOf (stringDirection); }
		catch (IllegalArgumentException exception)
		{
			System.out.println ( "Pour indiquer une direction entrez une des chaines de caractères suivantes :" );

			for ( Direction valeurDirection : Direction.values () )
				System.out.println ( "-> \"" + valeurDirection + "\"" );
			
			return;
		}

		Piece pieceSuivante = this.pieceActuelle.pieceSuivante (direction);

		if ( pieceSuivante == null )
			System.out.println ( "Il n'y a pas de porte dans cette direction !" );
		else
		{
			this.pieceActuelle = pieceSuivante;
			System.out.println ( this.pieceActuelle.descriptionLongue () );
		}

		return;
	}


	public void afficherAidePrendre ()
	{
		System.out.println ( "La commande 'prendre' permet de récuperer un objet présent dans la pièce." );
		System.out.println ();

		System.out.println ( "Elle peut prendre l'ensemble des objets présents en argument," );
		System.out.println ( "\tmais vous pourrez le prendre que s'il vous n'êtes pas trop charger." );
		System.out.println ();
		
		System.out.println ( "Pour connaître les objets que vous pouvez prendre, utilisez la commande 'piece objets'" );

		return;
	}
	public void prendreObjet (Commande commande)
	{
		if ( ! commande.aDeuxiemeMot () )
			{ this.afficherAidePrendre (); return; }
		
		if ( this.pieceActuelle.objetsEstNull () )
			{ System.out.println ( "Il n'y a pas d'objets." ); return; }
		
		String stringObjet = commande.deuxiemeMot ();
		ObjetZork objetLu = null;
		
		for ( int i = 0;  i < this.pieceActuelle.objetsTaille (); i ++ )
		{
			objetLu = this.pieceActuelle.objet (i);
			
			if ( objetLu != null && stringObjet.equals ( objetLu.nom () ) && objetLu.peutEtrePris ( this.poidsActuel, POIDS_MAX ) )
			{
				this.prendObjet (objetLu);
				this.pieceActuelle.enleverObjet (i);
				
				return;
			}
		}

		System.out.println ( "Vous ne pouvez pas prendre : " + stringObjet );

		return;
	}
	public void prendObjet (ObjetZork objet)
	{
		this.poidsActuel += objet.poids ();

		System.out.println ( "Vous avez pris : " + objet.nom () );

		return;
	}


	public void afficherAideQuitter ()
	{
		System.out.println ( "La commande 'quitter' permet de quitter le jeu (ne le faites pas et restez vous amusez avec moi)." );
		System.out.println ();

		System.out.println ( "Il ne faut pas ajouter d'arguments pour la faire fonctionner." );

		return;
	}



}

