#ifndef __TRI_H__
#define __TRI_H__

#include "libs.h"

#define Inferieur(elementA, elementB) 		\
	( (elementA) < (elementB) )

#define Echange(elementA, elementB)			\
	Element elementC = elementA; 			\
	elementA = elementB; 					\
	elementB = elementC;


int triInsertionFloat (Element * elements, int gauche, int droite);
int triInsertion      (Element * elements, int gauche, int droite);
int triSelection      (Element * elements, int gauche, int droite);

int triShell          (Element * elements, int gauche, int droite);

int triFusion         (Element * elements, int gauche, int droite, int complexite);
int triRapide         (Element * elements, int gauche, int droite, int complexite);

#endif // __TRI_H__