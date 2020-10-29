CREATE SEQUENCE profesor_id_seq
NO MINVALUE
NO MAXVALUE;

CREATE SEQUENCE alumno_id_seq
NO MINVALUE
NO MAXVALUE;

CREATE SEQUENCE curso_id_seq
NO MINVALUE
NO MAXVALUE;

CREATE SEQUENCE asignacion_id_seq
NO MINVALUE
NO MAXVALUE;

CREATE TABLE profesor (
	id int8 NOT NULL DEFAULT nextval('profesor_id_seq'::regclass),
	activo bool NOT NULL,
	nombres varchar(255) NULL,
	apellidos varchar(255) NULL,
	fechaNacimiento date NULL,
	cui varchar(255) NULL,
	genero varchar(255) NULL,
	profesion varchar(255) NULL,
	CONSTRAINT profesor_pkey PRIMARY KEY (id)
);

CREATE TABLE alumno (
	id int8 NOT NULL DEFAULT nextval('alumno_id_seq'::regclass),
	activo bool NOT NULL,
	nombres varchar(255) NULL,
	apellidos varchar(255) NULL,
	fechaNacimiento date NULL,
	cui varchar(255) NULL,
	genero varchar(255) NULL,
	grado varchar(255) NULL,
	CONSTRAINT alumno_pkey PRIMARY KEY (id)
);

CREATE TABLE curso (
	id int8 NOT NULL DEFAULT nextval('curso_id_seq'::regclass),
	activo bool NOT NULL,
	nombreCurso varchar(255) NULL,
	duracionCurso int4 NULL,
	profesor_id int8 NULL,
	CONSTRAINT curso_pkey PRIMARY KEY (id),
	CONSTRAINT fk_curso_profesor_id FOREIGN KEY (profesor_id) REFERENCES profesor(id)
);

CREATE TABLE asignacion (
	id int8 NOT NULL DEFAULT nextval('asignacion_id_seq'::regclass),
	activo bool NOT NULL,
	horaInicio time NULL,
	horafinal time NULL,
	alumno_id int8 NULL,
	curso_id int8 NULL,
	CONSTRAINT asignacion_pkey PRIMARY KEY (id),
	CONSTRAINT fk_asignacion_alumno_id FOREIGN KEY (alumno_id) REFERENCES alumno(id),
	CONSTRAINT fk_asignacion_curso_id FOREIGN KEY (curso_id) REFERENCES curso(id)
);