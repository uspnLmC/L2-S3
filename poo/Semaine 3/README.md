# TD de la semaine 3

Le TD et le TP sont confondus donc je mets tout dans le TP.

---

# TP de la semaine 3

De ce que j'ai compris de par le TD et ceux d'après, il faut implémenter la classe 'ObjetZork' de manière nulle. \
Par contre pour l'instant, le code que j'ai fait n'est pas très propre, il faut que je le revois (mais plus tard). Tout comme la documentation javadoc qui n'est pas faite.

## Exercice 1 :

Dans cet exercice, on doit créer la classe 'ObjetZork', qui possédera comme caractéristiques :
- String nom;
- String description;
- int poids;
- boolean transportable : sera vraie ou faux suivant si on le joueur peut porter l'objet ou non;

Autres que les méthodes de base pour créer une nouvelle instance ou récupérer les caractéristiques, on doit implémenter :
- int hashCode () : sert à rien pour l'instant mais c'est juste pour un prochain TP;
- boolean peutEtrePris (int poidsActu, int poidsMax) : retourne vrai si l'objet est prenable par la personne, donc qu'il puisse le supporter et que l'objet soit portable;
- String nom_poids (String espace) : retourne le nom et le poids de l'objet séparé de 'espace'

Normalement il faut faire la documentation javadoc mais flemme, je la ferai plus tard


## Exercice 2 :

Dans cet exercice, on doit modifier le code du projet Zork pour implementer les objets :
- En ajoutant des commandes
- En modifiant la classe 'Piece' 

### Ajout / Modification des commandes :

On veut ajouter les commandes suivantes :
- 'piece objets' : affiche les objets

Il faut aussi ajouter et modifier les commandes d'aides, mais on s'en fout un peu (puisque de base ce n'est pas aussi développer).

### Modifier la classe 'Piece'

Il faut ajouter la variable ObjetZork [] objets qui stockera les objets de la piece.

Il faut également créer différentes méthodes :
- boolean objetsVide () : retourne vrai si 'objets' est null ou si tous les objets de 'objets' sont nuls, faux sinon





