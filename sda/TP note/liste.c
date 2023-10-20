#include "liste.h"
#include <stdio.h>
#include <stdlib.h>

struct maillon * nouveau_maillon (item v)
{
	struct maillon * m = malloc ( sizeof (struct maillon) );

	m -> valeur = v;
	m -> suivant = NULL;

	return m;
}

void detruire_maillon (struct maillon * m)
{
	if ( m == NULL ) return;

	m -> suivant = NULL;
	free (m);

	return;
}

struct liste * nouvelle_liste ()
{
	struct liste * l = malloc ( sizeof (struct liste) );
	
	l -> premier = NULL;
	l -> longueur = 0;

	return l;
}

void afficher_liste (const struct liste * l)
{
	struct maillon * m;
	for ( m = l -> premier; m != NULL; m = m -> suivant)
		{ display (m -> valeur); }
	printf ("\n");

	return;
}

struct maillon * acceder_pos_liste (const struct liste * l, unsigned pos)
{
	if ( l == NULL ) return NULL;
	if ( pos >= l -> longueur ) return NULL;

	struct maillon * m;
	for ( m = l -> premier; m != NULL && pos != 0; pos -- )
		{ m = m -> suivant; }
	
	return m;
}

void ajouter_maillon_debut_liste (struct liste * l, struct maillon * m)
{
	if ( l == NULL ) return;
	if ( m == NULL ) return;

	m -> suivant = l -> premier;
	l -> premier = m;

	l -> longueur += 1;

	return;
}

void ajouter_maillon_fin_liste (struct liste * l, struct maillon * m)
{
	if ( l == NULL ) return;
	if ( m == NULL ) return;

	if ( l -> longueur == 0 )
		{ ajouter_maillon_debut_liste (l, m); return; }

	struct maillon * p;

	p = acceder_pos_liste (l, l -> longueur - 1);
	p -> suivant = m;

	m -> suivant = NULL;
	l -> longueur += 1;

	return;
}

struct maillon * extraire_maillon_debut_liste (struct liste * l)
{
	if ( l == NULL ) return NULL;

	struct maillon * m = l -> premier;

	if ( m != NULL )
	{
		l -> premier = m -> suivant;
		l -> longueur -= 1;

		m -> suivant = NULL;
	}

	return m;
}

void detruire_liste (struct liste ** l)
{
	if ( l == NULL ) return;

	while ( (* l) -> longueur > 0 )
		{ detruire_maillon ( extraire_maillon_debut_liste (* l) ); }
	
	free (* l);
	* l = NULL;

	return;
}
