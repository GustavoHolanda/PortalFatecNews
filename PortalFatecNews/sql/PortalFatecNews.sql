CREATE DATABASE PortalFatecNews;
USE PortalFatecNews;

CREATE TABLE Usuario(
id INT IDENTITY PRIMARY KEY,
email VARCHAR(200) UNIQUE NOT NULL,
login VARCHAR(50) UNIQUE NOT NULL,
senha VARCHAR(50) NOT NULL

)

CREATE TABLE Leitor(
id INT IDENTITY,
idUsuario INT NOT NULL,
avatar VARCHAR(MAX),
nome VARCHAR(50) NOT NULL,
sobrenome VARCHAR(100) NOT NULL,
sexo VARCHAR(9) NOT NULL,
dataNascimento DATE NOT NULL,
celular VARCHAR(11) NOT NULL,
telefone VARCHAR(10) NOT NULL,
categoriaPreferida VARCHAR(50),
redeSocial VARCHAR(500)
FOREIGN KEY (idUsuario) REFERENCES Usuario(id),
PRIMARY KEY (id, idUsuario)
)


CREATE TABLE Escritor(
id INT IDENTITY,
idUsuario INT NOT NULL,
avatar VARCHAR(MAX),
nome VARCHAR(50) NOT NULL,
sobrenome VARCHAR(100) NOT NULL,
sexo VARCHAR(9) NOT NULL,
dataNascimento DATE NOT NULL,
celular VARCHAR(11) NOT NULL,
telefone VARCHAR(10) NOT NULL,
especialidade VARCHAR(50) NOT NULL,
blog VARCHAR(500),
biografiaPremios VARCHAR(MAX),
codigoUBE INT
FOREIGN KEY (idUsuario) REFERENCES Usuario(id),
PRIMARY KEY (id, idUsuario)
)



SELECT * FROM Usuario
SELECT * FROM Escritor
SELECT * FROM Leitor

DROP TABLE Leitor
DROP TABLE Escritor
DROP TABLE Usuario

TRUNCATE TABLE Leitor 
TRUNCATE TABLE Escritor
TRUNCATE TABLE Usuario

DELETE FROM Usuario

