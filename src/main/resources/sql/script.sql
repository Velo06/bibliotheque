CREATE DATABASE bibliotheque;
USE bibliotheque;

CREATE TABLE typeadherent(
    id INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(100),   -- etudiant, professionnel, professeur --
    dureePret INT,
    dureePenalite INT,
    quotaLivre INT,
    quotaReservation INT,
    quotaProlongement INT,
    montantCotisation DECIMAL(10, 2)
);

CREATE TABLE adherent(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100),
    prenom VARCHAR(200),
    dateNaissance DATE,
    idTypeAdherent INT,
    mdp VARCHAR(100),
    FOREIGN KEY (idTypeAdherent) REFERENCES typeadherent(id)
);

CREATE TABLE bibliothecaire(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100),
    prenom VARCHAR(200),
    dateNaissance DATE,
    mdp VARCHAR(100)
);

CREATE TABLE theme(
    id INT PRIMARY KEY AUTO_INCREMENT,
    theme VARCHAR(100)
);

CREATE TABLE auteur(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nomAuteur VARCHAR(100)
);

CREATE TABLE livre(
    id INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(100),
    idAuteur INT,
    idTheme INT,
    age INT, -- restriction pret ex.: livre interdit aux moins de 18 ans --
    nbrExemplaire INT,
    FOREIGN KEY (idTheme) REFERENCES theme(id)
);

CREATE TABLE typepret(
    id INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(100)   -- sur place, a la maison --
);

CREATE TABLE pret(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idTypePret INT,
    idAdherent INT,
    idLivre INT,
    dateRetourPrevu DATE,
    FOREIGN KEY (idTypePret) REFERENCES typepret(id),
    FOREIGN KEY (idAdherent) REFERENCES adherent(id),
    FOREIGN KEY (idLivre) REFERENCES livre(id)
);
ALTER TABLE pret ADD dateEmprunt DATE;
ALTER TABLE pret ADD dateRetourReel DATE;

CREATE TABLE etat(
    id INT PRIMARY KEY AUTO_INCREMENT,
    etat VARCHAR(50) -- accepte, refuse, en attente
);

CREATE TABLE reservation(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idAdherent INT,
    idLivre INT,
    idEtat INT,
    dateDemande DATE,
    dateReservation DATE,
    FOREIGN KEY (idAdherent) REFERENCES adherent(id),
    FOREIGN KEY (idLivre) REFERENCES livre(id),
    FOREIGN KEY (idEtat) REFERENCES etat(id)
);

CREATE TABLE penalisation(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idAdherent INT,
    FOREIGN KEY (idAdherent) REFERENCES adherent(id)
);
ALTER TABLE penalisation ADD dateDebut DATE;
ALTER TABLE penalisation ADD dateFin DATE;

CREATE TABLE prolongement(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idPret INT,
    dateFin DATE,   -- dateRetourPrevu + nbr de jour prolongement
    idEtat INT,
    dateDemande DATE,
    FOREIGN KEY (idPret) REFERENCES pret(id),
    FOREIGN KEY (idEtat) REFERENCES etat(id)
);

CREATE TABLE abonnement(    -- ?? --
    id INT PRIMARY KEY AUTO_INCREMENT,
    idAdherent INT,
    montant DECIMAL(10, 2),
    dateDebut DATE,
    dateFin DATE,
    FOREIGN KEY (idAdherent) REFERENCES adherent(id)
);

CREATE TABLE jourferie(
    id INT PRIMARY KEY AUTO_INCREMENT,
    dateFerie DATE
);

CREATE TABLE etatlivre(
    id INT PRIMARY KEY AUTO_INCREMENT,
    etat VARCHAR(100)   -- disponible, reserve, en pret --
);  

CREATE TABLE exemplaire (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idLivre INT NOT NULL,
    idEtat INT NOT NULL,
    FOREIGN KEY (idLivre) REFERENCES livre(id),
    FOREIGN KEY (idEtat) REFERENCES etatlivre(id)
);

INSERT INTO bibliothecaire(nom,mdp) VALUES('admin','admin');
INSERT INTO bibliothecaire(nom,mdp) VALUES('velo','velo');