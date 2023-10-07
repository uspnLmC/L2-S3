# TD de la semaine 4

## Exercice 1 :
1. Le code affiche "objets différents".
2. Il faut utiliser la méthode < equals >.
3. Il faut utiliser "==" si on veut comparer l'endroit où sont stocker les objets (pas int, float, ..) et "equals" sinon.
4. **Voir TP**

## Exercice 2 :
1. La version 1 respecte le contrat mais pas parfaitement. La version 2 ne le respecte pas ( oz1 != oz2 ). La version 3 respecte le contrat de manière optimale, tout comme la version 4.
2. Lorsqu'on redéfinit la fonctions equals, il faut également redéfinir la méthode hashCode.
3. **Voir TP**

## Exercice 3 :
1. Les caractéristiques sont :
- String nom;
- int capaciteRestante;
- ObjetZork [] objetsPorte;
2. **Voir TP**
3. **Voir TP**

---

# TP de la semaine 4

## Exercice 1 :

Il faut juste comparer tous les attributs, avec la méthode equals pour "nom" et "description", et le double égal pour "poids" et pour "transportable".

## Exercice 2 :

Pour faire simple, mon hashCode se calcule en sommant le hashCode de "nom", le hashCode de "description", le poids et 1 si "transportable" est vrai, 0 sinon.

## Exercice 3

Dans cet exercice, on doit modifier le code du projet Zork pour implementer le joueur :
- En ajoutant des commandes
- En modifiant les classes 'Jeu' et 'Piece'
- En ajoutant la classe 'Joueur'

### Ajout / Modification des commandes :

On veut ajouter les commandes suivantes :
- 'joueur' : toutes les commandes liées au joueur
- 'prendre' et 'poser'

### Modifier la classe 'Jeu' et la classe 'Piece' :

- Classe 'Jeu' : On veut ajouter le joueur présent dans le jeu et savoir si on peut prendre / poser un objet.
- Classe 'Piece' : On ajoute la possibilité d'ajouter ou enlever un objet de la liste d'objets

### Ajout de la classe 'Joueur' :

La classe joueur possède 4 attributs :
- String nom
- Piece pieceActuelle
- int capaciteRestante
- ObjetZork [] objets

Autre que les méthodes de base et d'affichage, on implémente aussi une méthode pour se deplacer et ajouter et enlever un objet.
