--Ejecutar primero
CREATE DATABASE RutasTuristicas; 

--Para las siguientes instrucciones, se debe cambiar la conexión

-- 1. Crear tabla TIPO (si no existe)
CREATE TABLE IF NOT EXISTS Tipo(
    Id SERIAL PRIMARY KEY,
    Nombre varchar(50) NOT NULL
);

-- Crear índice para TIPO DE RUTA (si no existe)
CREATE UNIQUE INDEX IF NOT EXISTS ixTipo
    ON Tipo(Nombre);


-- 2. Crear tabla PAIS (si no existe)
CREATE TABLE IF NOT EXISTS Pais(
    Id SERIAL PRIMARY KEY,
    Nombre varchar(50) NOT NULL,
    CodigoAlfa2 varchar(5) NOT NULL
);

-- Crear índice para PAIS (si no existe)
CREATE UNIQUE INDEX IF NOT EXISTS ixPais
    ON Pais(Nombre);


-- 3. Crear tabla CIUDAD (si no existe)
CREATE TABLE IF NOT EXISTS Ciudad(
    Id SERIAL PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    IdPais int NOT NULL,
    Longitud NUMERIC(10, 7) NOT NULL, 
    Latitud NUMERIC(10, 7) NOT NULL,  
    CONSTRAINT fkCiudad_IdPais FOREIGN KEY (IdPais) REFERENCES Pais(Id)
);

-- Definir índice único de CIUDAD (si no existe)
CREATE UNIQUE INDEX IF NOT EXISTS ixCiudad
    ON Ciudad(IdPais, Nombre);


-- 4. Crear tabla RUTA TURISTICA (si no existe)
CREATE TABLE IF NOT EXISTS Ruta(
    Id SERIAL PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
	Descripcion text NULL,
    IdCiudad int NOT NULL,
    IdTipo int NOT NULL,
    CONSTRAINT fkRuta_IdCiudad FOREIGN KEY (IdCiudad) REFERENCES Ciudad(Id),
    CONSTRAINT fkRuta_IdTipo FOREIGN KEY (IdTipo) REFERENCES Tipo(Id)
);

-- Definir índice único de RUTA TURISTICA (si no existe)
CREATE UNIQUE INDEX IF NOT EXISTS ixRuta
    ON Ruta(IdCiudad, Nombre);


-- 5. Crear tabla PARADA (si no existe)
CREATE TABLE IF NOT EXISTS Parada(
    Id SERIAL PRIMARY KEY,
	orden INT NOT NULL DEFAULT 1,
    Nombre VARCHAR(50) NOT NULL,
    IdRuta int NOT NULL,
    Longitud NUMERIC(10, 7) NOT NULL, 
    Latitud NUMERIC(10, 7) NOT NULL,  
    Tiempo int NULL,
    Descripcion text NULL,
    CONSTRAINT fkParada_IdRuta FOREIGN KEY (IdRuta) REFERENCES Ruta(Id) 
);

-- Definir índice único de PARADA (si no existe)
CREATE UNIQUE INDEX IF NOT EXISTS ixParada
    ON Parada(IdRuta, Nombre);

DO $$
DECLARE
    sigIdTipo INTEGER;
    sigIdPais INTEGER;
    sigIdCiudad INTEGER;
    sigIdRuta INTEGER;
    sigIdParada INTEGER;
BEGIN
    -- 1. Obtener el siguiente ID disponible para cada tabla
    SELECT COALESCE(MAX(Id), 0) + 1 INTO sigIdTipo FROM Tipo;
    SELECT COALESCE(MAX(Id), 0) + 1 INTO sigIdPais FROM Pais;
    SELECT COALESCE(MAX(Id), 0) + 1 INTO sigIdCiudad FROM Ciudad;
    SELECT COALESCE(MAX(Id), 0) + 1 INTO sigIdRuta FROM Ruta;
    SELECT COALESCE(MAX(Id), 0) + 1 INTO sigIdParada FROM Parada;

    -- 2. Sincronizar las secuencias nativas generadas por el tipo SERIAL
    -- Nota: PostgreSQL nombra estas secuencias por defecto como: nombretabla_id_seq
    
    EXECUTE format('ALTER SEQUENCE tipo_id_seq RESTART WITH %s', sigIdTipo);
    EXECUTE format('ALTER SEQUENCE pais_id_seq RESTART WITH %s', sigIdPais);
    EXECUTE format('ALTER SEQUENCE ciudad_id_seq RESTART WITH %s', sigIdCiudad);
    EXECUTE format('ALTER SEQUENCE ruta_id_seq RESTART WITH %s', sigIdRuta);
    EXECUTE format('ALTER SEQUENCE parada_id_seq RESTART WITH %s', sigIdParada);

END $$;