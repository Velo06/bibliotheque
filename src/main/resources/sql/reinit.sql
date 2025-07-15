-- Désactiver les contraintes de clé étrangère temporairement
SET FOREIGN_KEY_CHECKS = 0;

-- Supprimer les données dans un ordre respectant les dépendances
DELETE FROM abonnement;
DELETE FROM penalisation;
DELETE FROM prolongement;
DELETE FROM reservation;
DELETE FROM pret;
DELETE FROM exemplaire;
DELETE FROM etatlivre;
DELETE FROM jourferie;
DELETE FROM etat;
DELETE FROM typepret;
DELETE FROM livre;
DELETE FROM auteur;
DELETE FROM theme;
DELETE FROM adherent;
DELETE FROM typeadherent;
DELETE FROM bibliothecaire;

-- Réactiver les contraintes
SET FOREIGN_KEY_CHECKS = 1;

-- Réinitialiser les auto-incréments (optionnel)
ALTER TABLE abonnement AUTO_INCREMENT = 1;
ALTER TABLE penalisation AUTO_INCREMENT = 1;
ALTER TABLE prolongement AUTO_INCREMENT = 1;
ALTER TABLE reservation AUTO_INCREMENT = 1;
ALTER TABLE pret AUTO_INCREMENT = 1;
ALTER TABLE exemplaire AUTO_INCREMENT = 1;
ALTER TABLE etatlivre AUTO_INCREMENT = 1;
ALTER TABLE jourferie AUTO_INCREMENT = 1;
ALTER TABLE etat AUTO_INCREMENT = 1;
ALTER TABLE typepret AUTO_INCREMENT = 1;
ALTER TABLE livre AUTO_INCREMENT = 1;
ALTER TABLE auteur AUTO_INCREMENT = 1;
ALTER TABLE theme AUTO_INCREMENT = 1;
ALTER TABLE adherent AUTO_INCREMENT = 1;
ALTER TABLE typeadherent AUTO_INCREMENT = 1;
ALTER TABLE bibliothecaire AUTO_INCREMENT = 1;

