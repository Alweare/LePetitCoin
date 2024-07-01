USE ENIEncheres;
go

--------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------UTILISATEURS--------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES ('Alwear','Rougail','Saucisse','yanis.renard@campus','0633134552','kervegan','44000','Nantes','Pa$$W0rd',0,0);

SELECT * FROM UTILISATEURS
--------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------CATEGORIES--------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------
SELECT id, libelle FROM categories;

--------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------ARTICLES_vendus-----------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO ARTICLES_VENDUS (nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente, idUtilisateur, idCategorie)
	VALUES ('une pelle', 'c''est une pelle', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 20, 30, 1, 1);

UPDATE ARTICLES_VENDUS SET 
	nomArticle = 'un artichaud', 
	description = 'c''est un artichaud', 
	dateDebutEncheres = CURRENT_TIMESTAMP, 
	dateFinEncheres = CURRENT_TIMESTAMP, 
	prixInitial = 12, 
	prixVente = 200000, 
	idUtilisateur = 1, 
	idCategorie = 1
	WHERE id=1
	;
--DELETE FROM ARTICLES_VENDUS WHERE id=1;

SELECT id, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente, idUtilisateur, idCategorie FROM ARTICLES_VENDUS
--SELECT

