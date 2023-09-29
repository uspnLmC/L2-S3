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
- boolean estTransportable : sera vraie ou faux suivant si on le joueur peut porter l'objet ou non;

Autres que les méthodes de base pour créer une nouvelle instance ou récupérer les caractéristiques, on doit implémenter :
- int hashCode () : sert à rien pour l'instant mais c'est juste pour un prochain TP;
- boolean peutEtrePris (int poidsActu, int poidsMax) : retourne vrai si l'objet est prenable par la personne, donc qu'il puisse le supporter et que l'objet soit portable;

Normalement il faut faire la documentation javadoc mais flemme, je la ferai plus tard


## Exercice 2 :

Dans cet exercice, on doit modifier le code du projet Zork pour implementer les objets :
- En ajoutant des commandes
- En modifiant les classes 'Piece' et 'Jeu' 

### Ajout / Modification des commandes :

On veut ajouter les commandes suivantes :
- 'piece objets' :
- 'poids' :
- 'prendre' :

Il faut aussi ajouter et modifier les commandes d'aides, mais on s'en fout un peu (puisque de base ce n'est pas aussi développer).

### Modifier la classe 'Piece'

Il faut ajouter la variable ObjetZork [] objets qui stockera les objets de la piece.

Pour ajouter les objets dans chaque piece on aura deux choix :
- version TP : lors de la création de la pièce, il faut mettre en argument un tableau d'objets
- version Moi : lors de la création de la pièce, on renseigne juste le nombre d'objets au maximum de la pièce, puis lors de la création des pièces pour le jeu, on y mettra les objets.

Les deux versions sont différentes, je préfère la deuxième mais la première est celle qui est marqué dans le TD/TP.

Si vous utilisez la deuxième version, il vous faut créer une méthode setObjets que vous n'aurez pas besoin pour la première.

Il faut également créer différentes méthodes :
- boolean objetsEstNull () : renvoie si le tableau d'objets existe ou pas
- int objetsTaille () : renvoie la taille du tableau d'objets, -1 si le tableau est null
- ObjetZork objet (int i) : renvoie l'objet situé à l'index i dans le tableau, null si le tableau est null
- void enleverObjet (int i) : supprime l'objet situé à l'index i dans le tableau, ne fait rien si le tableau est null.


### Modifier la classe 'Jeu'

Il faut ajouter deux variables pour le poids :
- final static int POIDS_MAX : variable qui ne peut être modifié qui stocke le poids max que le joueur peut porté
- int poidsActuel;

La méthode void creerPieces () est modifié pour y ajouter les objets.

Plusieurs méthodes ont été créées pour connaître le poids porté actuellement et le poids max que l'on peut porté.

Il faut également créer différentes méthodes pour récupérer un objet :
- void prendreObjet (Commande commande) : suivant l'objet que vous mettez en argument, regardera si vous pouvez le prendre, le cas échéant vous le prendrez et il sera supprimé de la pièce;
- void prendObjet (ObjetZork objet) : permet de prendre l'objet de la pièce;





