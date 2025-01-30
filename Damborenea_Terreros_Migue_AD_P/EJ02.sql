-- Modela un objeto llamado película para almacenar sus características (título, director, duración en minutos). 
-- Incluye una función member llamada calcular_duracion_horas que devolverá la duración de la película en horas con un decimal.
CREATE TYPE Pelicula AS OBJECT (
	titulo VARCHAR2(100),
	director VARCHAR2(100),
	duracion_minutos NUMBER,
	
	MEMBER FUNCTION calcular_duracion_horas RETURN NUMBER
);

-- Define la función calcular_duracion_horas para convertir la duración de minutos a horas.

CREATE OR REPLACE TYPE BODY Pelicula AS 
	MEMBER FUNCTION total RETURN NUMBER IS
	BEGIN 
		RETURN ROUND(duracion_minutos/60, 1);
	END calcular_duracion_horas;
END;

-- Crea una tabla para almacenar películas con un id de tipo NUMBER y un campo pelicula del tipo tipo_pelicula.

CREATE TABLE peliculas (
	id NUMBER PRIMARY KEY,
	pelicula Pelicula
);

-- Inserta una película con título "El arte del cine", director "Ana López" y duración de 150 minutos.

INSERT INTO Peliculas (id, pelicula) VALUES (1, Pelicula('El arte del cine', 'Ana López', 150));

-- Crea un programa en PL/SQL que invoque a la función calcular_duracion_horas para obtener la duración de la película en horas.
--   a. Recupera a una variable tipo_pelicula una película.
--   b. Invoca la función.
--   c. Muestra la duración con DBMS_OUTPUT.PUT_LINE.

DECLARE 
	p Pelicula;
	duracion_en_horas NUMBER;
BEGIN
	SELECT pelicula INTO p FROM Peliculas WHERE id = 1;
	
	duracion_en_horas := p.calcular_duracion_horas;
	
	DBMS_OUTPUT.PUT_LINE('La duración de la película "' ||p.titulo|| '" es de: ' ||duracion_en_horas|| ' horas.');
END;