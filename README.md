# Système de Gestion de Location de Véhicules (POO Java)

Ce projet est une application console robuste développée en Java, illustrant les concepts fondamentaux de la Programmation Orientée Objet (POO). Il permet de gérer un parc de véhicules (Voitures, Motos, Camions), d'enregistrer des clients et de gérer les contrats de location avec calcul automatique des tarifs et des primes d'assurance.

## 🚀 Fonctionnalités Clés

- **Gestion Multi-Véhicules** : Support pour Voitures, Motos et Camions avec des logiques de tarification distinctes.
- **Système d'Assurance** : Interface `Assurable` implémentant des règles métier spécifiques (ex: assurance Moto basée sur la cylindrée).
- **Gestion des Locations** : Suivi des dates, calcul automatique des durées et génération de factures détaillées.
- **Sécurité et Exceptions** : Gestion rigoureuse des erreurs via des exceptions personnalisées (Permis manquant, véhicule déjà loué, etc.).
- **Persistance des Données** : Sauvegarde et chargement automatique de l'état du système via la sérialisation Java (`agence.dat`).
- **Généricité** : Utilisation d'une classe `Parc<T>` générique pour une gestion flexible des collections.

## 📂 Structure du Projet

```text
src/
├── exceptions/          # Exceptions métier personnalisées
├── model/               # Classes de données et logique métier (POO)
│   ├── Assurable.java   # Interface pour les véhicules assurables
│   ├── Vehicule.java    # Classe de base abstraite
│   └── ...
├── service/             # Services de gestion (Parc générique)
└── Main.java            # Point d'entrée et interface utilisateur (CLI)
```

## 🛠️ Concepts POO Utilisés

- **Abstraction** : Utilisation de classes et méthodes abstraites pour définir un contrat commun aux véhicules.
- **Héritage** : Spécialisation des véhicules (Voiture, Moto, Camion).
- **Polymorphisme** : Calcul dynamique des prix et affichage des informations selon le type d'objet.
- **Interfaces** : Découplage de la logique d'assurance via l'interface `Assurable`.
- **Encapsulation** : Protection des données via des attributs privés/protégés et des getters/setters.
- **Généricité** : Manipulation de collections typées de manière flexible.

## 💻 Installation et Exécution

### Prérequis
- Java JDK 11 ou supérieur.

### Compilation
Depuis la racine du projet :
```powershell
javac -d bin -cp src src/Main.java src/model/*.java src/service/*.java src/exceptions/*.java
```

### Lancement
```powershell
java -cp bin Main
```

## 📝 Auteur
Développé dans le cadre d'un mini-projet pédagogique sur la Programmation Orientée Objet en Java.
