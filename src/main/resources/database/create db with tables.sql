DROP DATABASE IF EXISTS EXPICO;

CREATE DATABASE EXPICO;
USE EXPICO;

CREATE TABLE b100(
	bovinecode int primary key,
    breed varchar(50),
    color varchar(50),
    gender varchar(20) not null,
    birthdate date not null,
    bovinename varchar(100),
    bovinestatus varchar(20) not null,
    dateofdeath date,
    causeofdeath varchar(150),
    motherscode int,
    fatherscode int
);

CREATE TABLE l101(
	id int auto_increment,
    parcelariocode int,
    pathcadernetapredial varchar(200),
    pathdescricaopredial varchar(200),
    pathescritura varchar(200),
    gpslocation varchar(150),
    totalha double,
    primary key(id, parcelariocode)
);

CREATE TABLE v102(
	id int auto_increment primary key,
    vtype varchar(100) not null,
    brand varchar(100),
    lastoilchangedate date,
    lastoilchangekm int,
    nextoilchangedate date,
    nextoilchangekm int
);