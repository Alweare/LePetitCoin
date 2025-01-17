--DROP TABLE RETRAITS;
--DROP TABLE ENCHERES;
--DROP TABLE ARTICLES_VENDUS;
--DROP TABLE UTILISATEURS;
--DROP TABLE CATEGORIES;

--USE master;
--go
DROP DATABASE ENIEncheres;

CREATE DATABASE ENIEncheres;
go
USE ENIEncheres;
go
 --Script de cr�ation de la base de donn�es ENCHERES
 --  type :      SQL Server 2012





CREATE TABLE CATEGORIES (
    id   INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(30) NOT NULL
);

ALTER TABLE CATEGORIES ADD constraint categorie_pk PRIMARY KEY (id)

CREATE TABLE ENCHERES (
	id				INTEGER NOT NULL IDENTITY,
    idUtilisateur   INTEGER NOT NULL,
    idArticle      INTEGER NOT NULL,
    dateEnchere     datetime NOT NULL,
	montantEnchere  INTEGER NOT NULL
);

ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY (id);

CREATE TABLE RETRAITS (
	idArticle       INTEGER NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
);

ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (idArticle)

CREATE TABLE UTILISATEURS (
    id				 INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(100) NOT NULL,
    telephone        VARCHAR(15),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(100) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   bit NOT NULL
);

ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (id);
ALTER TABLE UTILISATEURS ADD CONSTRAINT UK_utilisateur_pseudo UNIQUE(pseudo);
ALTER TABLE UTILISATEURS ADD CONSTRAINT UK_utilisateur_email UNIQUE(email);


CREATE TABLE ARTICLES_VENDUS (
    id                INTEGER IDENTITY(1,1) NOT NULL,
    nomArticle                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	cheminImage					  VARCHAR(3000) NOT NULL,
	dateDebutEncheres           DATETIME2 NOT NULL,
    dateFinEncheres             DATETIME2 NOT NULL,
    prixInitial                  INTEGER,
    prixVente                    INTEGER,
    idUtilisateur                INTEGER NOT NULL,
    idCategorie                  INTEGER NOT NULL
);

ALTER TABLE ARTICLES_VENDUS ADD constraint articles_vendus_pk PRIMARY KEY (id);
ALTER TABLE ARTICLES_VENDUS 
	ADD 
	CONSTRAINT df_articles_imgPath 
	DEFAULT 'https://st2.depositphotos.com/9998432/48297/v/1600/depositphotos_482974586-stock-illustration-default-avatar-photo-placeholder-grey.jpg'
	FOR cheminImage;


ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( idUtilisateur ) REFERENCES UTILISATEURS ( id )
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_articles_vendus_fk FOREIGN KEY ( idArticle )
        REFERENCES ARTICLES_VENDUS ( id )
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE RETRAITS
    ADD CONSTRAINT retraits_articles_vendus_fk FOREIGN KEY ( idArticle )
        REFERENCES ARTICLES_VENDUS ( id )
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( idCategorie )
        REFERENCES categories ( id )
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY ( idUtilisateur )
        REFERENCES utilisateurs ( id )
ON DELETE NO ACTION 
    ON UPDATE no action ;
GO



CREATE FUNCTION dbo.GetHighestBid(@idArticle INT)
RETURNS INT
AS
BEGIN
    DECLARE @highestBid INT;

    SELECT @highestBid = MAX(montantEnchere)
    FROM Encheres
    WHERE idArticle = @idArticle;

    RETURN @highestBid;
END;
GO

 --�tape 2 : Cr�er un trigger sur la table des ench�res
CREATE TRIGGER trg_UpdateCurrentPrice
ON Encheres
AFTER INSERT, UPDATE
AS
BEGIN
    DECLARE @idArticle INT;

    -- R�cup�rer l'ID de l'article concern�
    SELECT @idArticle = inserted.idArticle
    FROM inserted;

    -- Mettre � jour le prix actuel dans la table des ventes
    UPDATE ARTICLES_VENDUS
    SET prixVente = dbo.GetHighestBid(@idArticle)
    WHERE id = @idArticle;
END;
GO