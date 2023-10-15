//	Semaine_4 ; Semaine_5


/**	
 *  Classe contenant le main du projet Zork.
 * 
 * 	<p> Elle initialise un joueur puis lance le jeu "Zork". </p>
 * 
 * 	@author		LemilCa
 * 	@version	Semaine_4 from Base
 */
public class ZorkFr
{
	public static void main (String [] args)
	{
		Joueur joueur = new Joueur ("LemilCa", 8, 15);

		Jeu jeu = new Jeu (joueur);
		jeu.jouer ();

		return;
	}


}


