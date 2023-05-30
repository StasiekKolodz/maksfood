DROP DATABASE maksfood;
CREATE DATABASE maksfood;
CREATE TABLE maksfood.fridge(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(2048) NOT NULL,
    amount VARCHAR(2048) DEFAULT "1",
    exp_date VARCHAR(2048) DEFAULT ""
    );
INSERT INTO maksfood.fridge VALUES(DEFAULT, "MILK", "2", "2012-01-12");
INSERT INTO maksfood.fridge VALUES(DEFAULT, "CHEESE", "3", "2022-01-22");
INSERT INTO maksfood.fridge VALUES(DEFAULT, "CORN", "1", "2010-01-12");
INSERT INTO maksfood.fridge VALUES(DEFAULT, "TOMATO", "3", "2001-03-27");
INSERT INTO maksfood.fridge VALUES(DEFAULT, "YOGURT", "3", "2003-05-17");
INSERT INTO maksfood.fridge VALUES(DEFAULT, "KETCHUP", "3", "2006-11-02");
    
CREATE TABLE maksfood.shoppingList(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(2048) NOT NULL,
    amount VARCHAR(2048) DEFAULT "1",
    parent_list VARCHAR(2048) NOT NULL
    );
    
INSERT INTO maksfood.shoppingList VALUES(DEFAULT, "MILK", "2", "Harcerz");
INSERT INTO maksfood.shoppingList VALUES(DEFAULT, "CHEESE", "3", "Harcerz");
INSERT INTO maksfood.shoppingList VALUES(DEFAULT, "CORN", "1", "Harcerz");
INSERT INTO maksfood.shoppingList VALUES(DEFAULT, "TOMATO", "3", "StudentPack");
INSERT INTO maksfood.shoppingList VALUES(DEFAULT, "YOGURT", "3", "StudentPack");
INSERT INTO maksfood.shoppingList VALUES(DEFAULT, "KETCHUP", "3", "StudentPack");

CREATE TABLE maksfood.listsList(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(2048) NOT NULL
    );
    
INSERT INTO maksfood.listsList VALUES(DEFAULT, "StudentPack");
INSERT INTO maksfood.listsList VALUES(DEFAULT, "Tortilla");
INSERT INTO maksfood.listsList VALUES(DEFAULT, "Harcerz");

CREATE TABLE maksfood.expiredProducts(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    daysToExpire INT NOT NULL,
    isExpired BOOL DEFAULT FALSE
    );

INSERT INTO maksfood.expiredProducts VALUES(DEFAULT, "MILK", "0", "1");
INSERT INTO maksfood.expiredProducts VALUES(DEFAULT, "CHEESE", "0", "1");
INSERT INTO maksfood.expiredProducts VALUES(DEFAULT, "CORN", "0", "1");
INSERT INTO maksfood.expiredProducts VALUES(DEFAULT, "TOMATO", "0", "1");
INSERT INTO maksfood.expiredProducts VALUES(DEFAULT, "YOGURT", "0", "1");
INSERT INTO maksfood.expiredProducts VALUES(DEFAULT, "KETCHUP", "0", "1");

CREATE TABLE maksfood.favourite_recipe(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(2048) NOT NULL,
    ingredients VARCHAR(2048) NOT NULL,
    photo_link VARCHAR(2048) NOT NULL,
    recipe_link VARCHAR(2048) NOT NULL
);