CREATE DATABASE PortalFatecNews;
USE PortalFatecNews;


CREATE TABLE Usuario(
id INT IDENTITY PRIMARY KEY,
avatar VARCHAR(MAX),
nome VARCHAR(50) NOT NULL,
sobrenome VARCHAR(100) NOT NULL,
email VARCHAR(200) UNIQUE NOT NULL,
datanascimento DATETIME NOT NULL,
sexo VARCHAR(9) NOT NULL,
login VARCHAR(50) UNIQUE NOT NULL,
senha VARCHAR(50) NOT NULL,
telefone VARCHAR(10) NOT NULL,
celular VARCHAR(11) NOT NULL
)

--Testando inserção
INSERT INTO Usuario VALUES
('c://algo.png','vitor','pinheiro','vsp@gmail.com','12/12/2012','Masculino','vsp123','vsp123','22222222','22222222')


SELECT * FROM Usuario WHERE email like 'vsp@gmail.com'
SELECT * FROM Usuario WHERE login like 'vsp@gmail.com'

--Eliminando dados da tabela
TRUNCATE TABLE Usuario

