#include "libs.h"
#include "tri.h"
#include <time.h>

#define NB_TABLEAUX (6)

typedef enum { INSERTION_FLOAT, INSERTION, SELECTION, SHELL, FUSION, RAPIDE } TriEnum;

Element ** creerTableaux (int * ptrNbElements);
Element ** initialiserTableaux (Element ** tableaux, int nbElements);
Element ** supprimerTableaux (Element ** tableaux, int nbElements);

int * trierTableaux (Element ** tableaux, int nbElements);
int * supprimerComplexites (int * complexites);

void afficherTableaux (Element ** tableaux, int nbElements);
void afficherComplexites (Element ** tableaux, int nbElements, int * complexites);

int main ()
{
	srand ( time (NULL) );
	int nbElements = 0; Element ** tableaux = NULL; int * complexites = NULL;

	tableaux = creerTableaux (& nbElements);
	printf ( "%d\n", nbElements );
	tableaux = initialiserTableaux (tableaux, nbElements);

	printf ("\n");
	afficherTableaux (tableaux, nbElements);

	complexites = trierTableaux (tableaux, nbElements);
	afficherComplexites (tableaux, nbElements, complexites);

	tableaux = supprimerTableaux (tableaux, nbElements);
	complexites = supprimerComplexites (complexites);

	printf ("\n");
	return EXIT_SUCCESS;
}


int recupereEntier (int * ptrEntier)
{
	int encoreEntier = 1, entier = 0;
	char c;

	do
	{
		scanf ( "%c", &c );

		if ( '0' <= c && c <= '9' ) { entier *= 10; entier += ( c - '0' ); }
		else if ( c != '\n' ) { encoreEntier = 0; }
	} while ( encoreEntier == 1 && c != '\n' );

	if ( encoreEntier == 1 ) { * ptrEntier = entier; return 1; }
	while ( c != '\n' ) { scanf ( "%c", &c ); }

	return 0;
}
Element ** creerTableaux (int * ptrNbElements)
{
	int nbElements, nombreLu, estEntier;
	Element ** tableaux = NULL;

	do { printf ("Nombre d'elements a trier : "); estEntier = recupereEntier ( & nbElements ); }
	while ( ! estEntier && nbElements >= 0 && nbElements < NB_MAX_ELEMENTS );

	tableaux = malloc ( NB_TABLEAUX * sizeof (Element *) );
	if ( tableaux == NULL ) { exit (EXIT_FAILURE); }

	for ( int iTableau = 0; iTableau < NB_TABLEAUX; iTableau ++ )
	{
		tableaux [iTableau] = malloc ( nbElements * sizeof (Element) );
		
		if ( tableaux [iTableau] == NULL ) { exit (EXIT_FAILURE); }
	}

	* ptrNbElements = nbElements;
	return tableaux;
}

Element ** initialiserTableauxAleatoire (Element ** tableaux, int nbElements)
{
	if ( tableaux == NULL ) { return NULL; }

	Element element;

	for ( int iElement = 0; iElement < nbElements; iElement ++ )
	{
		element = rand () % 1000;
		element = ( element == 0 ) ? (element) : ( ( rand () % 1000 ) / element * 100 );

		for ( int iTableau = 0; iTableau < NB_TABLEAUX; iTableau ++ )
			{ tableaux [iTableau][iElement] = element; }
	}

	return tableaux;
}
Element ** initialiserTableauxSaisie (Element ** tableaux, int nbElements)
{
	if ( tableaux == NULL ) { return NULL; }

	Element element;

	for ( int iElement = 0; iElement < nbElements; iElement ++ )
	{
		printf ( "Valeur %d : ", iElement );
		scanf ( "%f", & element );

		for ( int iTableau = 0; iTableau < NB_TABLEAUX; iTableau ++ )
			{ tableaux [iTableau][iElement] = element; }
	}

	return tableaux;
}
Element ** initialiserTableaux (Element ** tableaux, int nbElements)
{
	if ( tableaux == NULL ) { return NULL; }

	char modeInitialisation;

	do
	{
		printf ("Mode d'initialisation ('a' pour aleatoire, 's' pour saisie au clavier) : ");
		scanf ( "\n%c", & modeInitialisation );
	} while ( modeInitialisation != 'a' && modeInitialisation != 's' );

	if ( modeInitialisation == 'a' ) { tableaux = initialiserTableauxAleatoire (tableaux, nbElements); }
	if ( modeInitialisation == 's' ) { tableaux = initialiserTableauxSaisie    (tableaux, nbElements); }

	return tableaux;
}

Element ** supprimerTableaux (Element ** tableaux, int nbElements)
{
	if ( tableaux == NULL ) { return NULL; }

	for ( int iTableau = 0; iTableau < NB_TABLEAUX; iTableau ++ )
	{
		if ( tableaux [iTableau] != NULL ) { free (tableaux [iTableau]); } 
	}

	free (tableaux);

	return NULL;
}

int * trierTableaux (Element ** tableaux, int nbElements)
{
	int gauche = 0, droite = nbElements - 1;
	int * complexites = NULL;

	complexites = calloc ( NB_TABLEAUX, sizeof (int) );
	if ( complexites == NULL ) { return NULL; }

	complexites [INSERTION_FLOAT] = triInsertionFloat ( tableaux [INSERTION_FLOAT], gauche, droite );
	complexites [INSERTION] = triInsertion ( tableaux [INSERTION], gauche, droite );
	complexites [SELECTION] = triSelection ( tableaux [SELECTION], gauche, droite );
	complexites [SHELL] = triShell ( tableaux [SHELL], gauche, droite );
	complexites [FUSION] = triFusion ( tableaux [FUSION], gauche, droite, 0 );
	complexites [RAPIDE] = triRapide ( tableaux [RAPIDE], gauche, droite, 0 );

	return complexites;
}

int * supprimerComplexites (int * complexites)
{
	if ( complexites == NULL ) { return NULL; }

	free (complexites);

	return NULL;
}

void afficherElements (Element * elements, int nbElements)
{
	if ( elements == NULL ) { return; }

	for ( int iElement = 0; iElement < nbElements; iElement ++ )
		{ printf ( "[%.3f]", elements [iElement] ); }
	printf ("\n");

	return;
}
void afficherTableaux (Element ** tableaux, int nbElements)
{
	if ( tableaux == NULL ) { return; }

	for ( int iTableau = 0; iTableau < NB_TABLEAUX; iTableau ++ )
		{ afficherElements ( tableaux [iTableau], nbElements ); }
	printf ("\n");

	return;
}

void afficherComplexites (Element ** tableaux, int nbElements, int * complexites)
{
	if ( tableaux == NULL )    { return; }
	if ( complexites == NULL ) { return; }

	for ( int iTableau = 0; iTableau < NB_TABLEAUX; iTableau ++ )
		{ printf ( "%d :\t", complexites [iTableau] ); afficherElements (tableaux [iTableau], nbElements); }

	return;
}



