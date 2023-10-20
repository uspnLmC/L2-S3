#ifndef __LISTE_H__
#define __LISTE_H__

#include "item.h"

struct maillon
{
	item valeur;
	struct maillon * suivant;
};

struct liste
{
	struct maillon * premier;
	int longueur;
};

struct maillon * nouveau_maillon (item v);

void detruire_maillon (struct maillon *m);

struct liste * nouvelle_liste ();

void afficher_liste (const struct liste * l);

struct maillon * acceder_pos_liste (const struct liste * l, unsigned pos);

void ajouter_maillon_debut_liste (struct liste * l, struct maillon * m);

void ajouter_maillon_fin_liste (struct liste * l, struct maillon * m);

struct maillon * extraire_maillon_debut_liste (struct liste * l);

void detruire_liste (struct liste ** l);

#endif // __LISTE_H__
