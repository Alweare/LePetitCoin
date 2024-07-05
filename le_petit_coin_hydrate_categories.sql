use ENIEncheres;
go

INSERT INTO categories (libelle) VALUES ('Informatique');
INSERT INTO categories (libelle) VALUES ('Ameublement');
INSERT INTO categories (libelle) VALUES ('Vêtement');
INSERT INTO categories (libelle) VALUES ('Sport&Loisirs');

INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe, credit, administrateur) VALUES ('moi','toi','toi','toicampus','063514','des oiseau','03500','nantes','moi',200,1);