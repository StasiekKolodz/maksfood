DROP DATABASE maksfood;
CREATE DATABASE maksfood;
CREATE TABLE maksfood.fridge(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    amount VARCHAR(45) DEFAULT "1",
    exp_date VARCHAR(45) DEFAULT ""
    );
INSERT INTO maksfood.fridge VALUES(DEFAULT, "MILK", "2", "2012-01-12");
INSERT INTO maksfood.fridge VALUES(DEFAULT, "CHEESE", "3", "2022-01-22");
INSERT INTO maksfood.fridge VALUES(DEFAULT, "CORN", "1", "2010-01-12");
INSERT INTO maksfood.fridge VALUES(DEFAULT, "TOMATO", "3", "2001-03-27");
INSERT INTO maksfood.fridge VALUES(DEFAULT, "YOGURT", "3", "2003-05-17");
INSERT INTO maksfood.fridge VALUES(DEFAULT, "KETCHUP", "3", "2006-11-02");
    
CREATE TABLE maksfood.favourite_recipe(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(2048) NOT NULL,
    ingredients VARCHAR(2048) NOT NULL,
    photo_link VARCHAR(2048) NOT NULL,
    recipe_link VARCHAR(2048) NOT NULL,
);
