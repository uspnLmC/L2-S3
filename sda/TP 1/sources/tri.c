#include "tri.h"


int triInsertionFloat (Element * elements, int gauche, int droite)
{
	int i, j, complexite = 0;
	Element v;

	for ( i = gauche + 1; i <= droite; i ++ )
	{
		if ( Inferieur ( elements [i], elements [gauche] ) ) 
			{ Echange ( elements [i], elements [gauche] ); complexite += 3; }
		
		complexite += 1;
	}

	for ( i = gauche + 2; i <= droite; i ++ )
	{
		j = i; v = elements [i];

		while ( Inferieur ( v, elements [j - 1] ) )
			{ elements [j] = elements [j - 1]; j -= 1; complexite += 2; }

		elements [j] = v;
		complexite += 1;
	}

	return complexite;
}

int triInsertion (Element * elements, int gauche, int droite)
{
	int i, j, complexite = 0;

	for ( i = gauche + 1; i <= droite; i ++ )
	{
		j = i;
		
		while ( j > gauche && Inferieur ( elements [j], elements [j - 1] ) )
			{ Echange ( elements [j], elements [j - 1] ); j -= 1; complexite += 4; }
	}

	return complexite;
}

int rechercherPosMin (Element * elements, int taille, int * ptrComplexite)
{
	int i, posMin = 0;

	for ( i = 1; i < taille; i ++ )
	{
		if ( Inferieur ( elements [i], elements [posMin] ) ) { posMin = i; }

		* ptrComplexite += 1;
	}

	return posMin;
}
int triSelection (Element * elements, int gauche, int droite)
{
	int i, posMin, taille = droite + 1, complexite = 0;

	for ( i = 0; i < droite; i ++ )
	{
		posMin = rechercherPosMin (elements + i, taille - i, & complexite);
		
		Echange ( elements [i], elements [i + posMin] );
		complexite += 3;
	}

	return complexite;
}


int triShell (Element * elements, int gauche, int droite)
{
	int i, j, h = 1, complexite = 0;

	while ( 3 * h <= droite - gauche - 1 )
		{ h = 3 * h + 1; }
	
	while ( h > 0 )
	{
		for ( i = gauche + h; i <= droite; i ++ )
		{
			j = i;

			while ( j >= gauche + h && Inferieur ( elements [j], elements [j - h] ) )
				{ Echange ( elements [j - h], elements [j] ); j -= h; complexite += 4; }
			complexite += 1;
		}

		h /= 3;
	}

	return complexite;
}


Element elementsFusion [NB_MAX_ELEMENTS];
int fusion (Element * elements, int gauche, int milieu, int droite, int complexite)
{
	int i, j, k;

	for ( i = milieu + 1; i > gauche; i -- )
		{ elementsFusion [i - 1] = elements [i - 1]; complexite += 1; }

	for ( j = milieu; j < droite; j ++ )
		{ elementsFusion [droite + milieu - j] = elements [j + 1]; complexite += 1; }

	for ( k = gauche; k <= droite; k ++ )
	{
		if ( Inferieur ( elementsFusion [i], elementsFusion [j] ) ) { elements [k] = elementsFusion [i]; i += 1; }
		else                                                        { elements [k] = elementsFusion [j]; j -= 1; }

		complexite += 1;
	}

	return complexite;
}
int triFusion (Element * elements, int gauche, int droite, int complexite)
{
	if ( droite <= gauche )
		return complexite;
	
	int milieu = (gauche + droite) / 2;

	complexite = triFusion (elements, gauche, milieu, complexite);
	complexite = triFusion (elements, milieu + 1, droite, complexite);

	complexite = fusion (elements, gauche, milieu, droite, complexite);

	return complexite;
}

int partition(Element * elements, int gauche, int droite, int * ptrComlexite)
{
	int i = gauche-1, j = droite, complexite = 0;
	Element pivot = elements[droite];
	bool onContinue = true;

	while ( onContinue )
	{
		do { i += 1; complexite += 1; } while ( Inferieur ( elements [i], pivot ) );

		do { j -= 1; complexite += 1; } while ( j != gauche && Inferieur ( pivot, elements [j] ) );

		if ( i >= j ) onContinue = false;
		else { Echange ( elements [i], elements [j] ); complexite += 3; };
	}
	
	Echange ( elements [i], elements [droite] );
	complexite += 3;

	* ptrComlexite += complexite;
	return i;
}
int triRapide (Element * elements, int gauche, int droite, int complexite)
{
	if ( droite <= gauche ) { return complexite; }

	int i = partition (elements, gauche, droite, & complexite);
	complexite = triRapide (elements, gauche, i - 1, complexite);
	complexite = triRapide (elements, i + 1, droite, complexite);

	return complexite;
}



