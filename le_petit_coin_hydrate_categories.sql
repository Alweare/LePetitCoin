use ENIEncheres;
go

INSERT INTO categories (libelle) VALUES ('Informatique');
INSERT INTO categories (libelle) VALUES ('Ameublement');
INSERT INTO categories (libelle) VALUES ('Vêtement');
INSERT INTO categories (libelle) VALUES ('Sport&Loisirs');

INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe, credit, administrateur) VALUES ('moi','toi','toi','toicampus','063514','des oiseau','03500','nantes','moi',200,1);

INSERT INTO ARTICLES_VENDUS (nomArticle, description, cheminImage, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente, idUtilisateur, idCategorie)
	VALUES ('une pelle', 'c''est une pelle', DEFAULT, CURRENT_TIMESTAMP, DATEADD(year, 1, CURRENT_TIMESTAMP), 20, 30, 1, 1);

INSERT INTO RETRAITS (idArticle, rue, code_postal,ville) VALUES (1, 'MenilMontant Mais oui mesdames', '46544', 'Saint-Jean-du-doigts');

INSERT INTO ENCHERES (idUtilisateur, idArticle, dateEnchere,montantEnchere) VALUES (1, 1, CURRENT_TIMESTAMP, 125);