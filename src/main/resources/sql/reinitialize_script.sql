-- Script de réinitialisation complète de la base gestion_bibliotheque
SET FOREIGN_KEY_CHECKS = 0; -- Désactive les contraintes de clés étrangères temporairement

-- Suppression de toutes les tables (dans l'ordre inverse de création pour éviter les erreurs de référence)
DROP TABLE IF EXISTS Penalite;
DROP TABLE IF EXISTS TypePenalite;
DROP TABLE IF EXISTS PaiementCotisation;
DROP TABLE IF EXISTS Prolongement;
DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS Pret;
DROP TABLE IF EXISTS Adherent;
DROP TABLE IF EXISTS TypeAdherent;
DROP TABLE IF EXISTS Exemplaire;
DROP TABLE IF EXISTS Livre;
DROP TABLE IF EXISTS TypePret;
DROP TABLE IF EXISTS Status_Exemplaire;
DROP TABLE IF EXISTS Status_Demande;
DROP TABLE IF EXISTS JourFerie;
DROP TABLE IF EXISTS Bibliothecaire;

SET FOREIGN_KEY_CHECKS = 1; -- Réactive les contraintes de clés étrangères

-- Recréation de la base de données (la supprime si elle existe déjà)
DROP DATABASE IF EXISTS gestion_bibliotheque;
CREATE DATABASE gestion_bibliotheque;
USE gestion_bibliotheque;

-- Recréation de toutes les tables avec leur structure complète
-- Table Bibliothecaire
CREATE TABLE Bibliothecaire (
    id_bibliothecaire INT AUTO_INCREMENT PRIMARY KEY,
    pseudo VARCHAR(50) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(255) NOT NULL
);

-- Table JourFerie
CREATE TABLE JourFerie (
    id_jour_ferie INT AUTO_INCREMENT PRIMARY KEY,
    nom_fete VARCHAR(50) NOT NULL,
    date_jour_ferie DATE NOT NULL,
    est_exceptionnel BOOLEAN NOT NULL DEFAULT FALSE,
    UNIQUE KEY (date_jour_ferie)
);

-- Table Status_Demande
CREATE TABLE Status_Demande (
    id_status_demande INT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(50) NOT NULL UNIQUE
);

-- Table Status_Exemplaire
CREATE TABLE Status_Exemplaire (
    id_status_exemplaire INT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(50) NOT NULL UNIQUE
);

-- Table Livre
CREATE TABLE Livre (
    id_livre INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    auteur VARCHAR(100),
    editeur VARCHAR(100),
    annee_publication INT,
    nombre_pages INT,
    categorie VARCHAR(50),
    description TEXT,
    langue VARCHAR(30)
);

-- Table Exemplaire
CREATE TABLE Exemplaire (
    id_exemplaire INT AUTO_INCREMENT PRIMARY KEY,
    id_livre INT NOT NULL,
    id_status_exemplaire INT NOT NULL,
    date_ajout DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (id_livre) REFERENCES Livre(id_livre) ON DELETE CASCADE,
    FOREIGN KEY (id_status_exemplaire) REFERENCES Status_Exemplaire(id_status_exemplaire)
);

-- Table TypePret
CREATE TABLE TypePret (
    id_type_pret INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL UNIQUE
);

-- Table TypeAdherent
CREATE TABLE TypeAdherent (
    id_type_adherent INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL UNIQUE,
    quota_livre INT NOT NULL CHECK (quota_livre > 0),
    duree_emprunt_max INT NOT NULL CHECK (duree_emprunt_max > 0),
    montant_cotisation DECIMAL(10,2) NOT NULL CHECK (montant_cotisation >= 0)
);

-- Table Adherent
CREATE TABLE Adherent (
    id_adherent INT AUTO_INCREMENT PRIMARY KEY,
    id_type_adherent INT NOT NULL,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    date_naissance DATE,
    pseudo VARCHAR(50) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(255) NOT NULL,
    date_inscription DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (id_type_adherent) REFERENCES TypeAdherent(id_type_adherent)
);

-- Table Pret
CREATE TABLE Pret (
    id_pret INT AUTO_INCREMENT PRIMARY KEY,
    id_type_pret INT NOT NULL,
    id_adherent INT NOT NULL,
    id_exemplaire INT NOT NULL,
    date_emprunt DATE NOT NULL,
    date_retour_prevu DATE NOT NULL,
    date_retour_effective DATE,
    est_en_retard BOOLEAN GENERATED ALWAYS AS ((date_retour_effective IS NULL AND CURDATE() > date_retour_prevu)) STORED,
    FOREIGN KEY (id_type_pret) REFERENCES TypePret(id_type_pret),
    FOREIGN KEY (id_adherent) REFERENCES Adherent(id_adherent),
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire(id_exemplaire),
    CHECK (date_retour_prevu > date_emprunt)
);

-- Table Prolongement
CREATE TABLE Prolongement (
    id_prolongement INT AUTO_INCREMENT PRIMARY KEY,
    id_status_demande INT NOT NULL,
    id_pret INT NOT NULL,
    date_fin_prolongement DATE NOT NULL,
    date_de_demande TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_status_demande) REFERENCES Status_Demande(id_status_demande),
    FOREIGN KEY (id_pret) REFERENCES Pret(id_pret) ON DELETE CASCADE,
    CHECK (date_fin_prolongement > date_de_demande)
);

-- Table Reservation
CREATE TABLE Reservation (
    id_reservation INT AUTO_INCREMENT PRIMARY KEY,
    id_exemplaire INT NOT NULL,
    id_adherent INT NOT NULL,
    id_status_demande INT NOT NULL,
    date_de_demande TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_de_reservation DATE,
    date_expiration TIMESTAMP GENERATED ALWAYS AS (date_de_demande + INTERVAL 48 HOUR) STORED,
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire(id_exemplaire),
    FOREIGN KEY (id_adherent) REFERENCES Adherent(id_adherent),
    FOREIGN KEY (id_status_demande) REFERENCES Status_Demande(id_status_demande)
);

-- Table PaiementCotisation
CREATE TABLE PaiementCotisation (
    id_paiement_cotisation INT AUTO_INCREMENT PRIMARY KEY,
    id_adherent INT NOT NULL,
    montant DECIMAL(10,2) NOT NULL CHECK (montant > 0),
    date_de_paiement DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (id_adherent) REFERENCES Adherent(id_adherent) ON DELETE CASCADE
);

-- Table TypePenalite
CREATE TABLE TypePenalite (
    id_type_penalite INT AUTO_INCREMENT PRIMARY KEY,
    motif VARCHAR(100) NOT NULL UNIQUE
);

-- Table Penalite
CREATE TABLE Penalite (
    id_penalite INT AUTO_INCREMENT PRIMARY KEY,
    id_type_penalite INT NOT NULL,
    id_adherent INT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    FOREIGN KEY (id_type_penalite) REFERENCES TypePenalite(id_type_penalite),
    FOREIGN KEY (id_adherent) REFERENCES Adherent(id_adherent) ON DELETE CASCADE,
    CHECK (date_fin > date_debut)
);

-- Insertion des données de base
INSERT INTO Status_Demande (status) VALUES 
('en_attente'), ('valide'), ('refuse'), ('annule');

INSERT INTO Status_Exemplaire (status) VALUES 
('disponible'), ('emprunte'), ('reserve'), ('hors_service');

-- Recréation des index
CREATE INDEX idx_exemplaire_status ON Exemplaire(id_status_exemplaire);
CREATE INDEX idx_pret_retard ON Pret(est_en_retard);
CREATE INDEX idx_reservation_status ON Reservation(id_status_demande);
CREATE INDEX idx_jour_ferie_date ON JourFerie(date_jour_ferie);
CREATE INDEX idx_pret_adherent ON Pret(id_adherent);
CREATE INDEX idx_pret_exemplaire ON Pret(id_exemplaire);
CREATE INDEX idx_prolongement_status ON Prolongement(id_status_demande);

-- Message de confirmation
SELECT 'La base de données gestion_bibliotheque a été réinitialisée avec succès.' AS Message;