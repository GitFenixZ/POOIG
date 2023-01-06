# Introduction

Ce projet a été réalisé par ARROUS Thomas (22114626) et SOAN Tony Ly (22107803)

Ce fichier est un fichier d'informations destiné à des utilisateurs souhaitant lancer notre projet.

## Configuration requise

Avant tout, assurez vous d'avoir la version 11 de Java.

## Instructions

Pour démarrer le jeu, depuis le dossier POOIG, utilisez la commande `$ bash launch.sh`.

Après avoir exécuté la commande ci-dessus, une fenêtre appraitra sur votre écran.

## Le jeu

### Choix du jeu

Vous trouverez un menu où vous pourrez choisir le nombre de joueurs.

Veuillez choisir entre 2 et 8 joueurs inclus.

Ensuite sélectionnez le jeu auquel vous voulez jouer, vous avez 3 choix:

- `Domino Terminal`, c'est la version terminale du jeu `Domino`.
- `Domino`, c'est la version avec interface graphique du jeu `Domino`.
- `Carcassonne`, c'est la version avec interface graphique du jeu `Carcassonne`.

### Paramétrage du jeu

- Si vous avez sélectionné Domino Terminal`, vous pourrez paramétrer votre partie dans le terminal.

  - Pour chaque joueur, vous pourrez choisir son nom et s'il s'agit d'un bot en suivant les questions qui apparaîtront au fur ét à mesure auxquelles vous devrez répondre.

- Si vous avez sélectionné un des jeux ayant une interface graphique, vous pourrez paramétrez votre partie sur la fenêtre de paramètres qui s'affiche.

  - Pour chaque joueur, vous pourrez choisir son nom et s'il s'agit d'un bot et puis vous validerez quand un joueur est prêt grâce aux zones de texte et aux boutons.
  - La différence entre la version graphique de `Domino` et de `Carcassonne` est que vous pouvez choisir la couleur des partisans dans `Carcassonne` grâce à un menu déroulant.

Une fois que tous les joueurs sont paramétrés et ont validé, la partie se lance.

### Comment jouer ?

Le jeu se termine lorsque le sac de pièces a été vidé.

### `Domino Terminal`

Pour jouer à `Domino Terminal` vous devez entrer au clavier un choix valide parmi les propositions qui s'afficheront au fur et à mesure de la partie que ce soit pour naviguer, tourner une pièce, ou poser une pièce.

Des informations sur le déroulement du jeu sont disponibles tout au long de la partie.
Par exemple vous verrez:

- l'endroit où vous vous situez sur le plateau grâce à une grille 5x5 centrée sur votre position affichant aussi les cases autour.
- les pièces déjà posées et les espaces libres autour de vous
- le nom, le score, et la pièce dans la main du joueur.

### `Domino` (version interface graphique)

Vous jouerez avec des `Dominos` carrés, ayant des valeurs sur chaque coté du `Domino` sauf dans les coins de la pièce.

Pour jouer à `Domino` vous avez plusieurs informations disponibles et une télécommande.

Le centre de la grille sera votre position actuelle, un carré noir s'affichera pour fournir une aide visuelle si vous n'êtes pas sur une pièce.

- Sur la gauche de votre fenêtre vous trouverez votre barre d'information, qui affiche des informations concernant le joueur, son score, et le nombre de pièces restantes dans la partie.

- Vous trouverez au bas de la barre d'information une télécommande pour tourner une pièce vers la gauche ou vers la droite et aussi pour vous déplacer dans la direction des flèches indiquées sur chaque touche.

- Au centre de cette télécommande se trouve le bouton pour placer la pièce sur l'emplacement où vous trouvez.

### `Carcassonne` (version interface graphique)

`Carcassonne` dispose de la même interface utilisateur que Domino à l'exception de quelques détails.

Le score affichera toujours `0` car on ne calcule pas les scores des joueurs.

Les pièces sont des images des pièces tirées du jeu de société pour une meilleure expérience visuelle.

Quand un joueur place une pièce, il est possible de placer un partisan sur la fenêtre qui s'affiche en cliquant sur l'enplacement voulu de la pièce.
Il est possible de ne pas placer de partisan en cliquant sur le bouton `Passer`.


