# Projet Transversal 4IRC

Membres : 
* Antoine Gamain (https://github.com/Fyndir)
* Tom Blanchet (https://github.com/frontBOI)
* léo Meynet (https://github.com/Neexos)
* Lucas Philippe (https://github.com/Tenebry)

## Contexte

Ce projet consiste à simuler des incendies sur une ville, et leur prise en charge par les flottes d’urgence qui vont intervenir.

le projet se découpe en plusieur brique : 

* Le centre de simulation (https://github.com/Fyndir/ClientJavaSimulation) : 

Son rôle est de générer des feux dont les coordonnées, l’intensité et la fréquence sont à définir dans le programme. Ces données sont par la site transmit au serveur Flash de simulation à l'aide d'une API.
Par la site la gestion des déplacements des camions sera également gérer par ce programme et seront envoyer sur le serveur de l'emergencyManager à l'aide d'une API.

* Le serveur de simulation (https://github.com/Fyndir/FireSimulation) :

Son role est d'afficher la simulation en temps réel pour voir l'état des feu sur une map.

* La brique IOT :

Son role est de transmettre les information du serveur de simulation au serveur d'emergency manager à l'aide de deux microcontrolleur et d'API devellopées sur les deux serveurs.

* Le serveur Emergency Manager (https://github.com/Fyndir/EmergencyManager):

Son role est d'inserer les données qu'il recoit dans la base de données à l'aide d'API. Il permet d'afficher en temps réel le contenu de la base (feu / déplacement des camions)

* La base de données de l'emergency Manager : 

Son role est de stocker les données des feu et d'affecté les camions au dit feu à l'aide d'un ensemble de trigger SQL

## Le centre de simulation

### Fonctionnement
![Image of sequence diag](https://github.com/Fyndir/ClientJavaSimulation/blob/master/Simulation%20java.png)
