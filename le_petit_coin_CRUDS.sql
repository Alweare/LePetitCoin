USE ENIEncheres;
go

--------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------UTILISATEURS--------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------

--INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES ('Alwear','Rougail','Saucisse','yanis.renard@campus','0633134552','kervegan','44000','Nantes','Pa$$W0rd',0,0);

--SELECT * FROM UTILISATEURS
--------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------CATEGORIES--------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------
--SELECT id, libelle FROM categories;

--------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------ARTICLES_vendus-----------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------
--INSERT INTO ARTICLES_VENDUS (nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente, idUtilisateur, idCategorie)
--	VALUES ('une pelle', 'c''est une pelle', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 20, 30, 1, 1);

--UPDATE ARTICLES_VENDUS SET 
--	nomArticle = 'un artichaud', 
--	description = 'c''est un artichaud', 
--	dateDebutEncheres = CURRENT_TIMESTAMP, 
--	dateFinEncheres = CURRENT_TIMESTAMP, 
--	prixInitial = 12, 
--	prixVente = 200000, 
--	idUtilisateur = 1, 
--	idCategorie = 1
--	WHERE id=1
--	;
--DELETE FROM ARTICLES_VENDUS WHERE id=1;

--SELECT id, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente, idUtilisateur, idCategorie FROM ARTICLES_VENDUS;

--SELECT

--------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------ENCHERE--------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------
--INSERT INTO ENCHERES (idUtilisateur, idArticle, dateEnchere,montantEnchere) VALUES (1, 1, CURRENT_TIMESTAMP, 125);
UPDATE ENCHERES SET dateEnchere = CURRENT_TIMESTAMP, montantEnchere= 150 WHERE idUtilisateur = 1 AND montantEnchere = 1;
SELECT idUtilisateur, idArticle, dateEnchere, montantEnchere FROM ENCHERES;
SELECT 
	AV.id, AV.nomArticle, 
	AV.description, 
	AV.prixInitial, 
	AV.prixVente,
	AV.dateDebutEncheres, 
	AV.dateDebutEncheres, 
	C.id, C.libelle, 
	E.dateEnchere, 
	E.montantEnchere, 
	U.id, U.pseudo
	FROM ENCHERES as E 
		INNER JOIN UTILISATEURS as U ON (e.idUtilisateur = u.id) 
		INNER JOIN ARTICLES_VENDUS as AV ON (AV.idUtilisateur = u.id) 
		INNER JOIN CATEGORIES AS C ON (AV.idCategorie = C.id) 
	WHERE e.idUtilisateur = 1 AND e.idArticle = 1;

	SELECT 
	AV.id, AV.nomArticle, 
	AV.description, 
	AV.prixInitial, 
	AV.prixVente,
	AV.dateDebutEncheres, 
	AV.dateDebutEncheres, 
	C.id, C.libelle, 
	E.dateEnchere, 
	E.montantEnchere, 
	U.id, U.pseudo
	FROM ENCHERES as E 
		INNER JOIN UTILISATEURS as U ON (e.idUtilisateur = u.id) 
		INNER JOIN ARTICLES_VENDUS as AV ON (AV.idUtilisateur = u.id) 
		INNER JOIN CATEGORIES AS C ON (AV.idCategorie = C.id) 
	WHERE U.id=1;
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
	SELECT 
	AV.id, AV.nomArticle, 
	AV.description, 
	AV.prixInitial, 
	AV.prixVente,
	AV.dateDebutEncheres, 
	AV.dateDebutEncheres, 
	C.id, C.libelle, 
	E.dateEnchere, 
	E.montantEnchere, 
	U.id, U.pseudo
	FROM ENCHERES as E 
		INNER JOIN UTILISATEURS as U ON (e.idUtilisateur = u.id) 
		INNER JOIN ARTICLES_VENDUS as AV ON (AV.idUtilisateur = u.id) 
		INNER JOIN CATEGORIES AS C ON (AV.idCategorie = C.id) 
<<<<<<< Updated upstream
	WHERE e.idUtilisateur = 1;

	SELECT 
	AV.id, AV.nomArticle, 
	AV.description, 
	AV.prixInitial, 
	AV.prixVente,
	AV.dateDebutEncheres, 
	AV.dateDebutEncheres, 
	C.id, C.libelle, 
	E.dateEnchere, 
	E.montantEnchere, 
	U.id, U.pseudo
	FROM ENCHERES as E 
		INNER JOIN UTILISATEURS as U ON (e.idUtilisateur = u.id) 
		INNER JOIN ARTICLES_VENDUS as AV ON (AV.idUtilisateur = u.id) 
		INNER JOIN CATEGORIES AS C ON (AV.idCategorie = C.id) 
	WHERE C.id = 1;

	SELECT 
	AV.id, AV.nomArticle, 
	AV.description, 
	AV.prixInitial, 
	AV.prixVente,
	AV.dateDebutEncheres, 
	AV.dateDebutEncheres, 
	C.id, C.libelle, 
	E.dateEnchere, 
	E.montantEnchere, 
	U.id, U.pseudo
	FROM ENCHERES as E 
		INNER JOIN UTILISATEURS as U ON (e.idUtilisateur = u.id) 
		INNER JOIN ARTICLES_VENDUS as AV ON (AV.idUtilisateur = u.id) 
		INNER JOIN CATEGORIES AS C ON (AV.idCategorie = C.id) 
	WHERE E.idUtilisateur = 1 AND AV.dateDebutEncheres < CURRENT_TIMESTAMP AND E.montantEnchere = MAX(e.montantEnchere) ;
=======
	WHERE e.idUtilisateur = 1;
>>>>>>> Stashed changes
