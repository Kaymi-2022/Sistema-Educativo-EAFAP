
-- PERIODOS
INSERT INTO periodo_academico (id_periodo,nombre,fecha_inicio,fecha_fin,estado)
VALUES (seq_periodo.NEXTVAL,'2026-I',DATE '2026-03-01',DATE '2026-07-31','Y');

INSERT INTO periodo_academico (id_periodo,nombre,fecha_inicio,fecha_fin,estado)
VALUES (seq_periodo.NEXTVAL,'2026-II',DATE '2026-08-01',DATE '2026-12-15','Y');

-- ROLES
INSERT INTO rol (id_rol,nombre_rol,estado) VALUES (seq_rol.NEXTVAL,'ADMIN','Y');
INSERT INTO rol (id_rol,nombre_rol,estado) VALUES (seq_rol.NEXTVAL,'DOCENTE','Y');
INSERT INTO rol (id_rol,nombre_rol,estado) VALUES (seq_rol.NEXTVAL,'DISCENTE','Y');

-- USUARIOS
INSERT INTO usuario (id_usuario,dni,nombres,apellidos,email,username,password,estado) VALUES
    (seq_usuario.NEXTVAL,'45678901','Juan','Pérez','admin@eafap.mil.pe','admin','$2a$demo','Y');
INSERT INTO usuario VALUES (seq_usuario.NEXTVAL,'46781234','Carlos','Rojas','crojas@eafap.mil.pe','crojas','$2a$demo','Y');
INSERT INTO usuario VALUES (seq_usuario.NEXTVAL,'47892345','María','Gómez','mgomez@eafap.mil.pe','mgomez','$2a$demo','Y');
INSERT INTO usuario VALUES (seq_usuario.NEXTVAL,'48903456','Luis','Fernández','lfernandez@eafap.mil.pe','lfernandez','$2a$demo','Y');
INSERT INTO usuario VALUES (seq_usuario.NEXTVAL,'49014567','Ana','Torres','atorres@eafap.mil.pe','atorres','$2a$demo','Y');
INSERT INTO usuario VALUES (seq_usuario.NEXTVAL,'50125678','Pedro','Salas','psalas@eafap.mil.pe','psalas','$2a$demo','Y');

-- USUARIO_ROL
INSERT INTO usuario_rol VALUES (SEQ_USUARIO_ROL.NEXTVAL,1,1);
INSERT INTO usuario_rol VALUES (SEQ_USUARIO_ROL.NEXTVAL,2,2);
INSERT INTO usuario_rol VALUES (SEQ_USUARIO_ROL.NEXTVAL,3,2);
INSERT INTO usuario_rol VALUES (SEQ_USUARIO_ROL.NEXTVAL,4,3);
INSERT INTO usuario_rol VALUES (SEQ_USUARIO_ROL.NEXTVAL,5,3);
INSERT INTO usuario_rol VALUES (SEQ_USUARIO_ROL.NEXTVAL,6,3);

-- CATEGORIAS
INSERT INTO categoria VALUES (seq_categoria.NEXTVAL,'MORAL','Formación ética','Y');
INSERT INTO categoria VALUES (seq_categoria.NEXTVAL,'MILITAR','Formación militar','Y');
INSERT INTO categoria VALUES (seq_categoria.NEXTVAL,'ACADEMICO','Formación académica','Y');

-- CURSOS
INSERT INTO curso VALUES (seq_curso.NEXTVAL,'Formación Moral','Valores institucionales',1,2,1,'Y');
INSERT INTO curso VALUES (seq_curso.NEXTVAL,'Orden Cerrado','Instrucción militar',2,2,1,'Y');
INSERT INTO curso VALUES (seq_curso.NEXTVAL,'Motores Aeronáuticos I','Motores',3,3,1,'Y');

-- MATRICULAS
INSERT INTO curso_discente VALUES(seq_curso_discente.NEXTVAL,1,4,SYSDATE,'Y');
INSERT INTO curso_discente VALUES(seq_curso_discente.NEXTVAL,2,4,SYSDATE,'Y');
INSERT INTO curso_discente VALUES(seq_curso_discente.NEXTVAL,3,4,SYSDATE,'Y');
INSERT INTO curso_discente VALUES(seq_curso_discente.NEXTVAL,1,5,SYSDATE,'Y');
INSERT INTO curso_discente VALUES(seq_curso_discente.NEXTVAL,2,6,SYSDATE,'Y');

-- EVALUACIONES
INSERT INTO evaluacion VALUES(seq_evaluacion.NEXTVAL,1,'Conducta','MORAL',100,SYSDATE,'Y');
INSERT INTO evaluacion VALUES(seq_evaluacion.NEXTVAL,2,'Orden Cerrado','PRACTICA',100,SYSDATE,'Y');
INSERT INTO evaluacion VALUES(seq_evaluacion.NEXTVAL,3,'Parcial','EXAMEN',40,SYSDATE,'Y');

-- NOTAS
INSERT INTO nota VALUES(seq_nota.NEXTVAL,1,4,18,'Excelente','Y');
INSERT INTO nota VALUES(seq_nota.NEXTVAL,2,4,17,'Bueno','Y');
INSERT INTO nota VALUES(seq_nota.NEXTVAL,3,4,16,'Bueno','Y');
INSERT INTO nota VALUES(seq_nota.NEXTVAL,1,5,19,'Excelente','Y');
INSERT INTO nota VALUES(seq_nota.NEXTVAL,2,6,15,'Regular','Y');

-- RESULTADOS
INSERT INTO resultado_curso VALUES(seq_resultado.NEXTVAL,1,4,18,'APROBADO',SYSDATE,'Y');
INSERT INTO resultado_curso VALUES(seq_resultado.NEXTVAL,1,5,19,'APROBADO',SYSDATE,'Y');
INSERT INTO resultado_curso VALUES(seq_resultado.NEXTVAL,2,6,15,'APROBADO',SYSDATE,'Y');

-- SEMANAS
INSERT INTO semana_academica VALUES(seq_semana_academica.NEXTVAL,1,'Semana 1',DATE '2026-09-01',DATE '2026-09-07','Y');
INSERT INTO semana_academica VALUES(seq_semana_academica.NEXTVAL,2,'Semana 2',DATE '2026-09-08',DATE '2026-09-14','Y');

-- AULAS
INSERT INTO aula VALUES(seq_aula.NEXTVAL,'Aula 101',30,'Pabellón A','Y');
INSERT INTO aula VALUES(seq_aula.NEXTVAL,'Aula 102',30,'Pabellón A','Y');

-- BLOQUES
INSERT INTO bloque_horario VALUES(seq_bloque_horario.NEXTVAL,'08:00','10:00','Y');
INSERT INTO bloque_horario VALUES(seq_bloque_horario.NEXTVAL,'10:00','12:00','Y');

-- ACTIVIDADES
INSERT INTO actividad VALUES(seq_actividad.NEXTVAL,'Clase Moral','CLASE',1,'Y');
INSERT INTO actividad VALUES(seq_actividad.NEXTVAL,'Práctica Militar','PRACTICA',2,'Y');

-- HORARIOS
-- HORARIOS
INSERT INTO horario
(
    id_horario,
    dia_semana,
    fecha,
    id_semana,
    id_aula,
    id_bloque,
    id_actividad,
    id_usuario,
    estado
)
VALUES
    (
        seq_horario.NEXTVAL,
        'MARTES',
        DATE '2026-09-01',
        1,
        1,
        1,
        1,
        2,
        'Y'
    );

INSERT INTO horario
(
    id_horario,
    dia_semana,
    fecha,
    id_semana,
    id_aula,
    id_bloque,
    id_actividad,
    id_usuario,
    estado
)
VALUES
    (
        seq_horario.NEXTVAL,
        'MARTES',
        DATE '2026-09-08',
        2,
        2,
        2,
        2,
        3,
        'Y'
    );



COMMIT;
