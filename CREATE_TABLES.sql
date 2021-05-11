DROP TABLE DLC.dbo.POSTEOS;
DROP TABLE DLC.dbo.DOCUMENTOS;
DROP TABLE DLC.dbo.VOCABULARIO;

CREATE TABLE DLC.dbo.VOCABULARIO (
	id int IDENTITY(1,1),
	palabra varchar(50),
	maximo_ocurrencias numeric(18,0) NULL,
	cantidad_documentos numeric(18,0) NULL,
	CONSTRAINT PK_VOCABULARIO PRIMARY KEY (id)
);

CREATE TABLE DLC.dbo.DOCUMENTOS (
	id int IDENTITY(1,1),
	titulo varchar(500),
	[path] varchar(500),
	CONSTRAINT PK_DOCUMENTOS PRIMARY KEY (id)
);

CREATE TABLE DLC.dbo.POSTEOS (
	id int IDENTITY(1,1),
	documento_id int NOT NULL,
	vocabulario_id int NOT NULL,
	cantidad_ocurrencias numeric(18,0) NULL,
	CONSTRAINT POSTEOS_FK_1 FOREIGN KEY (vocabulario_id) REFERENCES DLC.dbo.VOCABULARIO(id),
	CONSTRAINT POSTEOS_FK FOREIGN KEY (documento_id) REFERENCES DLC.dbo.DOCUMENTOS(id)
);
