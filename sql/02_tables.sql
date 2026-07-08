-- ==============================
-- SECUENCIAS
-- ==============================
CREATE SEQUENCE seq_usuario START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_rol START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_categoria START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_periodo START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_curso START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_curso_discente START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_evaluacion START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_nota START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_resultado START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_semana_academica START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_aula START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_bloque_horario START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_actividad START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_horario START WITH 1 INCREMENT BY 1;

-- CAMBIO 01: Nueva tabla de periodos académicos
CREATE TABLE periodo_academico
(
    id_periodo   NUMBER PRIMARY KEY,
    nombre       VARCHAR2(20) NOT NULL UNIQUE,
    fecha_inicio DATE NOT NULL,
    fecha_fin    DATE NOT NULL,
    estado       VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT ck_periodo_estado CHECK (estado IN ('Y', 'N'))
);

-- CAMBIO 02: UNIQUE en DNI, EMAIL y USERNAME
CREATE TABLE usuario
(
    id_usuario NUMBER PRIMARY KEY,
    dni        VARCHAR2(8) NOT NULL,
    nombres    VARCHAR2(100),
    apellidos  VARCHAR2(100),
    email      VARCHAR2(100),
    username   VARCHAR2(50),
    password   VARCHAR2(255),
    estado     VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT uk_usuario_dni UNIQUE (dni),
    CONSTRAINT uk_usuario_email UNIQUE (email),
    CONSTRAINT uk_usuario_username UNIQUE (username),
    CONSTRAINT ck_usuario_estado CHECK (estado IN ('Y', 'N'))
);

CREATE TABLE rol
(
    id_rol     NUMBER PRIMARY KEY,
    nombre_rol VARCHAR2(30) NOT NULL,
    estado     VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT ck_rol_estado CHECK (estado IN ('Y', 'N'))
);

CREATE TABLE usuario_rol
(
    id_usuario NUMBER,
    id_rol     NUMBER,
    CONSTRAINT pk_usuario_rol PRIMARY KEY (id_usuario, id_rol),
    CONSTRAINT fk_ur_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
    CONSTRAINT fk_ur_rol FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
);

CREATE TABLE categoria
(
    id_categoria NUMBER PRIMARY KEY,
    nombre       VARCHAR2(50) NOT NULL,
    descripcion  VARCHAR2(200),
    estado       VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT ck_categoria_estado CHECK (estado IN ('Y', 'N'))
);

-- CAMBIO 03: id_docente -> id_usuario_docente
-- CAMBIO 04: periodo normalizado
CREATE TABLE curso
(
    id_curso           NUMBER PRIMARY KEY,
    nombre             VARCHAR2(100) NOT NULL,
    descripcion        VARCHAR2(200),
    id_categoria       NUMBER NOT NULL,
    id_usuario_docente NUMBER NOT NULL,
    id_periodo         NUMBER NOT NULL,
    estado             VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT fk_curso_categoria FOREIGN KEY (id_categoria) REFERENCES categoria (id_categoria),
    CONSTRAINT fk_curso_docente FOREIGN KEY (id_usuario_docente) REFERENCES usuario (id_usuario),
    CONSTRAINT fk_curso_periodo FOREIGN KEY (id_periodo) REFERENCES periodo_academico (id_periodo),
    CONSTRAINT ck_curso_estado CHECK (estado IN ('Y', 'N'))
);

-- CAMBIO 05: UNIQUE matrícula
CREATE TABLE curso_discente
(
    id_curso_discente     NUMBER PRIMARY KEY,
    id_curso              NUMBER NOT NULL,
    id_usuario_estudiante NUMBER NOT NULL,
    fecha_matricula       DATE DEFAULT SYSDATE,
    estado                VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT uk_cd UNIQUE (id_curso, id_usuario_estudiante),
    CONSTRAINT fk_cd_curso FOREIGN KEY (id_curso) REFERENCES curso (id_curso),
    CONSTRAINT fk_cd_usuario FOREIGN KEY (id_usuario_estudiante) REFERENCES usuario (id_usuario),
    CONSTRAINT ck_cd_estado CHECK (estado IN ('Y', 'N'))
);

CREATE TABLE evaluacion
(
    id_evaluacion NUMBER PRIMARY KEY,
    id_curso      NUMBER NOT NULL,
    nombre        VARCHAR2(100) NOT NULL,
    tipo          VARCHAR2(30),
    peso          NUMBER(5,2),
    fecha         DATE,
    estado        VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT ck_peso CHECK (peso BETWEEN 0 AND 100),
    CONSTRAINT fk_eval_curso FOREIGN KEY (id_curso) REFERENCES curso (id_curso),
    CONSTRAINT ck_eval_estado CHECK (estado IN ('Y', 'N'))
);

CREATE TABLE nota
(
    id_nota               NUMBER PRIMARY KEY,
    id_evaluacion         NUMBER NOT NULL,
    id_usuario_estudiante NUMBER NOT NULL,
    calificacion          NUMBER(5,2),
    observacion           VARCHAR2(200),
    estado                VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT uk_nota UNIQUE (id_evaluacion, id_usuario_estudiante),
    CONSTRAINT ck_calificacion CHECK (calificacion BETWEEN 0 AND 20),
    CONSTRAINT fk_nota_eval FOREIGN KEY (id_evaluacion) REFERENCES evaluacion (id_evaluacion),
    CONSTRAINT fk_nota_usuario FOREIGN KEY (id_usuario_estudiante) REFERENCES usuario (id_usuario),
    CONSTRAINT ck_nota_estado CHECK (estado IN ('Y', 'N'))
);

CREATE TABLE resultado_curso
(
    id_resultado          NUMBER PRIMARY KEY,
    id_curso              NUMBER NOT NULL,
    id_usuario_estudiante NUMBER NOT NULL,
    promedio_final        NUMBER(5,2),
    estado_aprobacion     VARCHAR2(20),
    fecha_cierre          DATE,
    estado                VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT uk_resultado UNIQUE (id_curso, id_usuario_estudiante),
    CONSTRAINT fk_res_curso FOREIGN KEY (id_curso) REFERENCES curso (id_curso),
    CONSTRAINT fk_res_usuario FOREIGN KEY (id_usuario_estudiante) REFERENCES usuario (id_usuario),
    CONSTRAINT ck_resultado_estado CHECK (estado IN ('Y', 'N'))
);

CREATE TABLE semana_academica
(
    id_semana     NUMBER PRIMARY KEY,
    numero_semana NUMBER(2) NOT NULL,
    descripcion   VARCHAR2(100),
    fecha_inicio  DATE NOT NULL,
    fecha_fin     DATE NOT NULL,
    estado        VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT ck_semana_estado CHECK (estado IN ('Y', 'N'))
);

CREATE TABLE aula
(
    id_aula   NUMBER PRIMARY KEY,
    nombre    VARCHAR2(50) NOT NULL,
    capacidad NUMBER CHECK (capacidad > 0),
    ubicacion VARCHAR2(100),
    estado    VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT ck_aula_estado CHECK (estado IN ('Y', 'N'))
);

CREATE TABLE bloque_horario
(
    id_bloque   NUMBER PRIMARY KEY,
    hora_inicio VARCHAR2(5) NOT NULL,
    hora_fin    VARCHAR2(5) NOT NULL,
    estado      VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT ck_bloque_estado CHECK (estado IN ('Y', 'N'))
);

CREATE TABLE actividad
(
    id_actividad NUMBER PRIMARY KEY,
    nombre       VARCHAR2(100) NOT NULL,
    tipo         VARCHAR2(30),
    id_curso     NUMBER NOT NULL,
    estado       VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT fk_act_curso FOREIGN KEY (id_curso) REFERENCES curso (id_curso),
    CONSTRAINT ck_actividad_estado CHECK (estado IN ('Y', 'N'))
);

CREATE TABLE horario
(
    id_horario   NUMBER PRIMARY KEY,
    dia_semana   VARCHAR2(15) NOT NULL,
    fecha        DATE   NOT NULL,
    id_semana    NUMBER NOT NULL,
    id_aula      NUMBER NOT NULL,
    id_bloque    NUMBER NOT NULL,
    id_actividad NUMBER NOT NULL,
    id_usuario   NUMBER NOT NULL,
    estado       VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINT uk_horario_aula UNIQUE (fecha, id_bloque, id_aula),
    CONSTRAINT uk_horario_docente UNIQUE (fecha, id_bloque, id_usuario),
    CONSTRAINT fk_hor_semana FOREIGN KEY (id_semana) REFERENCES semana_academica (id_semana),
    CONSTRAINT fk_hor_aula FOREIGN KEY (id_aula) REFERENCES aula (id_aula),
    CONSTRAINT fk_hor_bloque FOREIGN KEY (id_bloque) REFERENCES bloque_horario (id_bloque),
    CONSTRAINT fk_hor_actividad FOREIGN KEY (id_actividad) REFERENCES actividad (id_actividad),
    CONSTRAINT fk_hor_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
    CONSTRAINT ck_hor_estado CHECK (estado IN ('Y', 'N'))
);
