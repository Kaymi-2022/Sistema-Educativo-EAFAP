-- ==============================
-- SECUENCIAS
-- ==============================

CREATE SEQUENCE seq_usuario START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_rol START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_categoria START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_curso START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_curso_discente START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_evaluacion START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_nota START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_resultado START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_semana START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_bloque_horario START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_aula START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_actividad START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_horario START WITH 1 INCREMENT BY 1;

-- ==============================
-- TABLA USUARIO
-- ==============================

CREATE TABLE usuario
(
    id_usuario NUMBER PRIMARY KEY,
    dni        VARCHAR2(8) NOT NULL,
    nombres    VARCHAR2(100) NOT NULL,
    apellidos  VARCHAR2(100) NOT NULL,
    email      VARCHAR2(100),
    username   VARCHAR2(50) NOT NULL,
    password   VARCHAR2(255) NOT NULL,
    estado     VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT ck_usuario_estado CHECK (estado IN ('Y', 'N'))
);
-- ==============================
-- TABLA ROL
-- ==============================

CREATE TABLE rol
(
    id_rol     NUMBER PRIMARY KEY,
    nombre_rol VARCHAR2(30) NOT NULL,
    estado     VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT ck_rol_estado CHECK (estado IN ('Y', 'N'))
);

-- ==============================
-- TABLA USUARIO_ROL
-- ==============================

CREATE TABLE usuario_rol
(
    id_usuario NUMBER NOT NULL,
    id_rol     NUMBER NOT NULL,
    CONSTRAINT pk_usuario_rol PRIMARY KEY (id_usuario, id_rol),
    CONSTRAINT fk_ur_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
    CONSTRAINT fk_ur_rol FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
);

-- ==============================
-- TABLA CATEGORIA
-- ==============================

CREATE TABLE categoria
(
    id_categoria NUMBER PRIMARY KEY,
    nombre       VARCHAR2(50) NOT NULL,
    descripcion  VARCHAR2(200),
    estado       VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT ck_categoria_estado CHECK (estado IN ('Y', 'N'))
);

-- ==============================
-- TABLA CURSO
-- ==============================

CREATE TABLE curso
(
    id_curso     NUMBER PRIMARY KEY,
    nombre       VARCHAR2(100) NOT NULL,
    descripcion  VARCHAR2(200),
    id_categoria NUMBER NOT NULL,
    id_docente   NUMBER NOT NULL,
    periodo      VARCHAR2(20) NOT NULL,
    estado       VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT fk_curso_categoria FOREIGN KEY (id_categoria) REFERENCES categoria (id_categoria),
    CONSTRAINT fk_curso_docente FOREIGN KEY (id_docente) REFERENCES usuario (id_usuario),
    CONSTRAINT ck_curso_estado CHECK (estado IN ('Y', 'N'))
);

-- ==============================
-- TABLA CURSO_DISCENTE
-- ==============================

CREATE TABLE curso_discente
(
    id_curso_discente NUMBER PRIMARY KEY,
    id_curso          NUMBER NOT NULL,
    id_discente       NUMBER NOT NULL,
    fecha_matricula   DATE DEFAULT SYSDATE,
    estado            VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT fk_cd_curso FOREIGN KEY (id_curso) REFERENCES curso (id_curso),
    CONSTRAINT fk_cd_discente FOREIGN KEY (id_discente) REFERENCES usuario (id_usuario),
    CONSTRAINT ck_cd_estado CHECK (estado IN ('Y', 'N'))
);

-- ==============================
-- TABLA EVALUACION
-- ==============================

CREATE TABLE evaluacion
(
    id_evaluacion NUMBER PRIMARY KEY,
    id_curso      NUMBER NOT NULL,
    nombre        VARCHAR2(100) NOT NULL,
    tipo          VARCHAR2(30) NOT NULL,
    peso          NUMBER(5,2) NOT NULL,
    fecha         DATE   NOT NULL,
    estado        VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT fk_eval_curso FOREIGN KEY (id_curso) REFERENCES curso (id_curso),
    CONSTRAINT ck_eval_estado CHECK (estado IN ('Y', 'N'))
);

-- ==============================
-- TABLA NOTA
-- ==============================

CREATE TABLE nota
(
    id_nota       NUMBER PRIMARY KEY,
    id_evaluacion NUMBER NOT NULL,
    id_discente   NUMBER NOT NULL,
    calificacion  NUMBER(5,2) NOT NULL,
    observacion   VARCHAR2(200),
    estado        VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT fk_nota_eval FOREIGN KEY (id_evaluacion) REFERENCES evaluacion (id_evaluacion),
    CONSTRAINT fk_nota_discente FOREIGN KEY (id_discente) REFERENCES usuario (id_usuario),
    CONSTRAINT ck_nota_estado CHECK (estado IN ('Y', 'N'))
);

-- ==============================
-- TABLA RESULTADO_CURSO
-- ==============================

CREATE TABLE resultado_curso
(
    id_resultado      NUMBER PRIMARY KEY,
    id_curso          NUMBER NOT NULL,
    id_discente       NUMBER NOT NULL,
    promedio_final    NUMBER(5,2) NOT NULL,
    estado_aprobacion VARCHAR2(20) NOT NULL,
    fecha_cierre      DATE,
    estado            VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT fk_res_curso FOREIGN KEY (id_curso) REFERENCES curso (id_curso),
    CONSTRAINT fk_res_discente FOREIGN KEY (id_discente) REFERENCES usuario (id_usuario),
    CONSTRAINT ck_res_estado CHECK (estado IN ('Y', 'N'))
);

-- ==============================
-- TABLA SEMANA_ACADEMICA
-- ==============================

CREATE TABLE semana_academica
(
    id_semana     NUMBER PRIMARY KEY,
    numero_semana NUMBER NOT NULL,
    descripcion   VARCHAR2(100),
    fecha_inicio  DATE   NOT NULL,
    fecha_fin     DATE   NOT NULL,
    estado        VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT ck_semana_estado CHECK (estado IN ('Y', 'N'))
);

-- ==============================
-- TABLA AULA

CREATE TABLE aula
(
    id_aula   NUMBER PRIMARY KEY,
    nombre    VARCHAR2(50) NOT NULL,
    capacidad NUMBER,
    ubicacion VARCHAR2(100),
    estado    VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT ck_aula_estado CHECK (estado IN ('Y', 'N'))
);

-- ==============================
-- TABLA BLOQUE_HORARIO
-- ==============================
CREATE TABLE bloque_horario
(
    id_bloque   NUMBER PRIMARY KEY,
    hora_inicio VARCHAR2(5) NOT NULL,
    hora_fin    VARCHAR2(5) NOT NULL,
    estado      VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT ck_bloque_estado CHECK (estado IN ('Y', 'N'))
);


-- ==============================
-- TABLA ACTIVIDAD

CREATE TABLE actividad
(
    id_actividad NUMBER PRIMARY KEY,
    nombre       VARCHAR2(100) NOT NULL,
    tipo         VARCHAR2(30), -- CLASE, PRACTICA, EVALUACION
    id_curso     NUMBER NOT NULL,
    estado       VARCHAR2(1) DEFAULT 'Y' NOT NULL,

    CONSTRAINT fk_act_curso FOREIGN KEY (id_curso)
        REFERENCES curso (id_curso),

    CONSTRAINT ck_actividad_estado CHECK (estado IN ('Y', 'N'))
);

CREATE TABLE horario
(
    id_horario    NUMBER PRIMARY KEY,
    dia_semana    VARCHAR2(15) NOT NULL,
    fecha         DATE   NOT NULL,
    id_semana     NUMBER NOT NULL,
    id_aula       NUMBER NOT NULL,
    id_bloque     NUMBER NOT NULL,
    id_actividad  NUMBER NOT NULL,
    id_instructor NUMBER NOT NULL,
    estado        VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT fk_hor_semana FOREIGN KEY (id_semana) REFERENCES semana_academica (id_semana),
    CONSTRAINT fk_hor_aula FOREIGN KEY (id_aula) REFERENCES aula (id_aula),
    CONSTRAINT fk_hor_bloque FOREIGN KEY (id_bloque) REFERENCES bloque_horario (id_bloque),
    CONSTRAINT fk_hor_actividad FOREIGN KEY (id_actividad) REFERENCES actividad (id_actividad),
    CONSTRAINT fk_hor_instructor FOREIGN KEY (id_instructor) REFERENCES usuario (id_usuario),
    CONSTRAINT ck_hor_estado CHECK (estado IN ('Y', 'N'))
);