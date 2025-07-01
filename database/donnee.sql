-- Insertion des statuts par défaut
INSERT INTO Status_Demande (status) VALUES 
('en_attente'), ('valide'), ('refuse'), ('annule');

INSERT INTO Status_Exemplaire (status) VALUES 
('disponible'), ('emprunte'), ('reserve'), ('hors_service');