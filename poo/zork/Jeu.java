

public class Jeu
{

	private AnalyseurSyntaxique analyseurSyntaxique;
	private Piece pieceActuelle;


	public Jeu ()
	{
		this.analyseurSyntaxique = new AnalyseurSyntaxique ();
		creerPieces ();

		return;
	}
	public void creerPieces ()
	{
		Piece dehors, salleTD, taverne, batimentC, bureau;

		dehors = new Piece ( "devant le batiment C" );
		salleTD = new Piece ( "dans une salle de TD dans le batiment G" );
		taverne = new Piece ( "dans la taverne" );
		batimentC = new Piece ( "dans le batiment C" );
		bureau = new Piece ( "dans le secrétariat" );


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
			{ this.afficherAide ( commande ); return false; }
		

		if ( premierMot.equals ( "piece" ) )
			{ this.commandesPiece ( commande ); return false; }
		

		if ( premierMot.equals ( "aller" ) )
			{ this.deplacerVersAutrePiece ( commande ); return false; }
		

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
		
		
		if ( deuxiemeMot.equals ( "aller" ) )
			{ this.afficherAideAller (); return; }
		

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


	public void afficherAideQuitter ()
	{
		System.out.println ( "La commande 'quitter' permet de quitter le jeu (ne le faites pas et restez vous amusez avec moi)." );
		System.out.println ();

		System.out.println ( "Il ne faut pas ajouter d'arguments pour la faire fonctionner." );

		return;
	}



}

