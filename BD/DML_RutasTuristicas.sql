--0. Asegurar que el autonumérico de los campos ID esté sincronizado
SELECT setval(
    pg_get_serial_sequence('tipo', 'id'),
     COALESCE((SELECT MAX(id) FROM tipo) + 1, 1),
    false
);

SELECT setval(
    pg_get_serial_sequence('pais', 'id'),
     COALESCE((SELECT MAX(id) FROM pais) + 1, 1),
    false
);

SELECT setval(
    pg_get_serial_sequence('ciudad', 'id'),
     COALESCE((SELECT MAX(id) FROM ciudad) + 1, 1),
    false
);


--1. Crear los tipos
INSERT INTO tipo
	(nombre)
	VALUES ('Terrestre'), 
		('Fluvial')
	ON CONFLICT(nombre) DO NOTHING;

--2. Crear los paises destinos turísticos mundiales
INSERT INTO Pais (Nombre, CodigoAlfa2) VALUES
	('Países Bajos', 'NL'),
    ('Francia', 'FR'),
    ('España', 'ES'),
    ('Estados Unidos', 'US'),
    ('Italia', 'IT'),
    ('México', 'MX'),
    ('Reino Unido', 'GB'),
    ('Alemania', 'DE'),
    ('Grecia', 'GR'),
    ('Japón', 'JP'),
    ('Tailandia', 'TH'),
    ('Colombia', 'CO'),
    ('Perú', 'PE'),
    ('Brasil', 'BR'),
    ('Argentina', 'AR'),
    ('Egipto', 'EG')
ON CONFLICT (Nombre) DO NOTHING;

--3. Crear los ciudades turísticas mundiales
INSERT INTO Ciudad 
(Nombre, IdPais, Longitud, Latitud)
SELECT 
    v.CiudadNombre, 
    p.Id, 
    v.Longitud, 
    v.Latitud
FROM (
    VALUES 
        -- (Nombre de Ciudad, Nombre del País, Longitud, Latitud)
        ('París', 'Francia', 2.3522, 48.8566),
        ('Madrid', 'España', -3.7038, 40.4167),
        ('Barcelona', 'España', 2.1734, 41.3851),
        ('Nueva York', 'Estados Unidos', -74.0060, 40.7128),
        ('Roma', 'Italia', 12.4964, 41.9028),
        ('Venecia', 'Italia', 12.3155, 45.4408),
        ('Ciudad de México', 'México', -99.1332, 19.4326),
        ('Cancún', 'México', -86.8515, 21.1619),
        ('Tokio', 'Japón', 139.6503, 35.6762),
        ('Kioto', 'Japón', 135.7681, 35.0116),
        ('Bogotá', 'Colombia', -74.0721, 4.7110),
        ('Medellín', 'Colombia', -75.5646, 6.2442),
        ('Cusco', 'Perú', -71.9785, -13.5320),
        ('El Cairo', 'Egipto', 31.2357, 30.0444)
) AS v(CiudadNombre, PaisNombre, Longitud, Latitud)
JOIN Pais p ON p.Nombre = v.PaisNombre
ON CONFLICT (IdPais, Nombre) DO NOTHING;

-- 4. Insertar las Rutas mediante JOIN con Ciudad y Tipo
INSERT INTO ruta 
	(nombre, descripcion, idciudad, idtipo)
	SELECT 
	    v.RutaNombre, 
	    v.Descripcion, 
	    c.id, 
	    t.id
	FROM (
	    VALUES 
	        ('Crucero Histórico por el Sena', 'Ruta acuática que recorre los monumentos icónicos de París desde el río.', 'París', 'Fluvial'),
	        ('Madrid de los Austrias', 'Recorrido a pie por el centro histórico y palaciego de la capital española.', 'Madrid', 'Terrestre'),
	        ('Paseo Fluvial por el Támesis', 'Navegación desde Westminster hasta el histórico barrio de Greenwich.', 'Londres', 'Fluvial')
	) AS v(RutaNombre, Descripcion, CiudadNombre, TipoNombre)
	JOIN ciudad c ON c.nombre = v.CiudadNombre
	JOIN tipo t ON t.nombre = v.TipoNombre
	ON CONFLICT (idciudad, nombre) DO NOTHING;

-- 5. Insertar las Paradas
INSERT INTO parada 
	(nombre, descripcion, latitud, longitud, tiempo, orden, idruta)
	SELECT 
	    v.ParadaNombre, 
	    v.Descripcion, 
	    v.Latitud, 
	    v.Longitud, 
	    v.Tiempo, 
	    v.Orden, 
	    r.id
	FROM (
	    VALUES 
	        -- --- PARADAS: Crucero Histórico por el Sena (París - Fluvial) ---
	        ('Embarcadero Torre Eiffel', 'Punto de inicio a los pies de la imponente torre.', 48.8592, 2.2931, 15, 1, 'Crucero Histórico por el Sena'),
	        ('Muelle del Museo de Orsay', 'Avistamiento de la antigua estación de tren convertida en museo.', 48.8606, 2.3265, 20, 2, 'Crucero Histórico por el Sena'),
	        ('Isla de la Cité - Notre Dame', 'Parada junto a la emblemática catedral en el corazón de París.', 48.8530, 2.3499, 25, 3, 'Crucero Histórico por el Sena'),
	
	        -- --- PARADAS: Madrid de los Austrias (Madrid - Terrestre) ---
	        ('Plaza Mayor', 'Punto de encuentro central del Madrid antiguo.', 40.4154, -3.7074, 30, 1, 'Madrid de los Austrias'),
	        ('Mercado de San Miguel', 'Parada gastronómica para tapeo tradicional.', 40.4155, -3.7090, 45, 2, 'Madrid de los Austrias'),
	        ('Palacio Real y Sabatini', 'Finalización del recorrido contemplando la residencia oficial.', 40.4173, -3.7143, 45, 3, 'Madrid de los Austrias'),
	
	        -- --- PARADAS: Paseo Fluvial por el Támesis (Londres - Fluvial) ---
	        ('Muelle de Westminster', 'Abordaje junto al Big Ben y las Casas del Parlamento.', 51.5012, -0.1225, 15, 1, 'Paseo Fluvial por el Támesis'),
	        ('Muelle del Tower Bridge', 'Paso subterráneo navegable bajo el puente más famoso del mundo.', 51.5050, -0.0754, 35, 2, 'Paseo Fluvial por el Támesis'),
	        ('Muelle de Greenwich', 'Desembarque en el meridiano cero y visita al mercado histórico.', 51.4828, -0.0094, 40, 3, 'Paseo Fluvial por el Támesis')
	) AS v(ParadaNombre, Descripcion, Latitud, Longitud, Tiempo, Orden, RutaNombre)
	JOIN ruta r ON r.nombre = v.RutaNombre
	ON CONFLICT (idruta, nombre) DO NOTHING;;