

public class Jeu
{

	private AnalyseurSyntaxique analyseur;
	private Piece pieceActuelle;

	/* ----------------------------------------------------------------------------------------- */

	public Jeu ()
	{
		this.analyseur = new AnalyseurSyntaxique ();
		this.pieceActuelle = creerPieces ();

		return;
	}

	/* ----------------------------------------------------------------------------------------- */

	public AnalyseurSyntaxique analyseur () { return this.analyseur; }
	public Piece pieceActuelle () { return this.pieceActuelle; }

	/* ----------------------------------------------------------------------------------------- */

	public Piece creerPieces ()
	{
		Piece dehors, salleTD, taverne, batimentC, bureau;

		dehors = new Piece ( "devant le batiment C" );
		salleTD = new Piece ( "dans une salle de TD dans le batiment G" );
		taverne = new Piece ( "dans la taverne" );
		batimentC = new Piece ( "dans le batiment C" );
		bureau = new Piece ( "dans le secretariat" );

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
		System.out.println ( "Bienvenue dans le monde de Zork !" );
		System.out.println ( "Zork est un nouveau jeu d'aventure, terriblement enuyeux." );
		System.out.println ( "Tapez 'aide' si vous avez besoin d'aide." );
		System.out.println ();
		System.out.println ( this.pieceActuelle.descriptionTotale () );
		
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
		System.out.println ( "\t- 'sorties' : donne les directions vers lesquelles vous pouvez aller." );
		System.out.println ( "\t- 'tout' : donne l'ensemble des informations de la piece." );

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
		
		
		if ( premierMot.equals ( "aller" ) )
			{ this.deplacerVersAutrePiece (commande); return false; }
		

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
		
		
		if ( deuxiemeMot.equals ( "aller" ) )
			{ this.afficherAideAller (); return; }
		
		
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
		
		if ( deuxiemeMot.equals ( "sorties" ) )
			{ System.out.println ( this.pieceActuelle.descriptionSorties () ); return; }
		
		if ( deuxiemeMot.equals ( "tout" ) )
			{ System.out.println ( this.pieceActuelle.descriptionTotale () ); return; }
		

		this.afficherAidePiece ();

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
		System.out.println ( this.pieceActuelle.descriptionTotale () );

		return;
	}

}