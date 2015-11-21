CREATE DATABASE PortalFatecNews;
USE PortalFatecNews;

CREATE TABLE Usuario(
idUsuario INT IDENTITY PRIMARY KEY,
email VARCHAR(200) UNIQUE NOT NULL,
usuario VARCHAR(50) UNIQUE NOT NULL,
senha VARCHAR(50) NOT NULL,
tipo CHAR(1) CHECK(tipo = 'L' OR
tipo = 'E' OR tipo = 'A') NOT NULL 
)

CREATE TABLE Perfil(
idPerfil INT IDENTITY,
idUsuario INT NOT NULL,
avatar VARCHAR(MAX),
nome VARCHAR(50) NOT NULL,
sobrenome VARCHAR(100) NOT NULL,
sexo VARCHAR(9) NOT NULL,
dataNascimento DATE NOT NULL,
celular VARCHAR(11) NOT NULL,
telefone VARCHAR(10) NOT NULL,
redeSocial VARCHAR(500)
FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
PRIMARY KEY (idPerfil)
)



CREATE TABLE Materia(
idMateria INT IDENTITY,
idPerfil INT NOT NULL,
titulo VARCHAR(200) NOT NULL,
subTitulo VARCHAR(200) NOT NULL,
data DATETIME NOT NULL,
imagem VARCHAR(200),
fonteImagem VARCHAR(100),
corpo VARCHAR(MAX) NOT NULL,
fonteMateria VARCHAR(100)
FOREIGN KEY (idPerfil) REFERENCES Perfil (idPerfil),
PRIMARY KEY (idMateria)
)
 
CREATE TABLE Comentario(
idComentario INT IDENTITY,
idMateria INT NOT NULL,
idPerfil INT NOT NULL,
data DATETIME NOT NULL,
corpo VARCHAR(1000) NOT NULL
FOREIGN KEY(idMateria) REFERENCES Materia (idMateria),
FOREIGN KEY(idPerfil) REFERENCES Perfil(idPerfil),
PRIMARY KEY(idComentario)
)

SELECT idPerfil AS id, nome + + ' ' + sobrenome AS  'Nome completo' FROM Perfil


SELECT u.idUsuario, u.email, u.senha, u.tipo,
p.avatar, p.nome, p.sobrenome, p.sexo,
p.dataNascimento, p.celular, p.telefone,
p.redeSocial FROM Usuario u
INNER JOIN Perfil p
ON p.idUsuario = u.idUsuario

SELECT u.idUsuario, u.email, u.senha, u.tipo, p.idPerfil, p.avatar, p.nome,
 p.sobrenome, p.sexo, p.dataNascimento, p.celular, p.telefone,
				p.redeSocial FROM Usuario u INNER JOIN Perfil p ON p.idUsuario = u.idUsuario
				WHERE p.nome LIKE '%Gustavo%'

DELETE FROM Perfil WHERE idUsuario = 1
DELETE FROM Usuario WHERE idUsuario = 1


SELECT * FROM Usuario
SELECT * FROM Perfil
SELECT * FROM Materia
SELECT * FROM Comentario

DROP TABLE Comentario
DROP TABLE Materia
DROP TABLE Perfil
DROP TABLE Usuario

