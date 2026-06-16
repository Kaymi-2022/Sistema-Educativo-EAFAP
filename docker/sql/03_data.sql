-- ROLES
INSERT INTO rol
VALUES (seq_rol.NEXTVAL, 'ADMIN', 'Y');
INSERT INTO rol
VALUES (seq_rol.NEXTVAL, 'DOCENTE', 'Y');
INSERT INTO rol
VALUES (seq_rol.NEXTVAL, 'DISCENTE', 'Y');

-- USUARIOS
INSERT INTO usuario
VALUES (seq_usuario.NEXTVAL, '45678901', 'Juan', 'Pérez',
        'admin@eafap.mil.pe', 'admin', '1234', 'Y');

INSERT INTO usuario
VALUES (seq_usuario.NEXTVAL, '46781234', 'Carlos', 'Rojas',
        'docente@eafap.mil.pe', 'crojas', '1234', 'Y');

INSERT INTO usuario
VALUES (seq_usuario.NEXTVAL, '47892345', 'Luis', 'Fernández',
        'alumno@eafap.mil.pe', 'lfernandez', '1234', 'Y');

-- USUARIO_ROL
INSERT INTO usuario_rol
VALUES (1, 1);
INSERT INTO usuario_rol
VALUES (2, 2);
INSERT INTO usuario_rol
VALUES (3, 3);

-- CATEGORIAS
INSERT INTO categoria
VALUES (seq_categoria.NEXTVAL, 'MORAL', 'Formación ética', 'Y');
INSERT INTO categoria
VALUES (seq_categoria.NEXTVAL, 'MILITAR', 'Disciplina militar', 'Y');
INSERT INTO categoria
VALUES (seq_categoria.NEXTVAL, 'ACADEMICO', 'Formación académica', 'Y');

-- CURSOS
INSERT INTO curso
VALUES (seq_curso.NEXTVAL, 'Formación Moral', 'Valores institucionales', 1, 2, '2026-I', 'Y');
INSERT INTO curso
VALUES (seq_curso.NEXTVAL, 'Instrucción Militar Básica', 'Orden cerrado', 2, 2, '2026-I', 'Y');
INSERT INTO curso
VALUES (seq_curso.NEXTVAL, 'Motores Aeronáuticos I', 'Motores aeronáuticos', 3, 2, '2026-I', 'Y');

-- MATRÍCULA
INSERT INTO curso_discente
VALUES (seq_curso_discente.NEXTVAL, 1, 3, SYSDATE, 'Y');
INSERT INTO curso_discente
VALUES (seq_curso_discente.NEXTVAL, 2, 3, SYSDATE, 'Y');
INSERT INTO curso_discente
VALUES (seq_curso_discente.NEXTVAL, 3, 3, SYSDATE, 'Y');

-- EVALUACIONES
INSERT INTO evaluacion
VALUES (seq_evaluacion.NEXTVAL, 1, 'Conducta General', 'MORAL', 100, SYSDATE, 'Y');
INSERT INTO evaluacion
VALUES (seq_evaluacion.NEXTVAL, 2, 'Orden Cerrado', 'MILITAR', 100, SYSDATE, 'Y');
INSERT INTO evaluacion
VALUES (seq_evaluacion.NEXTVAL, 3, 'Examen Parcial', 'ACADEMICO', 50, SYSDATE, 'Y');

-- NOTAS
INSERT INTO nota
VALUES (seq_nota.NEXTVAL, 1, 3, 18.0, 'Excelente conducta', 'Y');
INSERT INTO nota
VALUES (seq_nota.NEXTVAL, 2, 3, 16.5, 'Buen desempeño', 'Y');
INSERT INTO nota
VALUES (seq_nota.NEXTVAL, 3, 3, 14.0, 'Debe reforzar', 'Y');

-- RESULTADOS
INSERT INTO resultado_curso
VALUES (seq_resultado.NEXTVAL, 1, 3, 18.0, 'APTO', SYSDATE, 'Y');
INSERT INTO resultado_curso
VALUES (seq_resultado.NEXTVAL, 2, 3, 16.5, 'APTO', SYSDATE, 'Y');
INSERT INTO resultado_curso
VALUES (seq_resultado.NEXTVAL, 3, 3, 14.0, 'NO APTO', SYSDATE, 'Y');

-- SEMANA ACADÉMICA
INSERT INTO SEMANA_ACADEMICA (ID_SEMANA, NUMERO_SEMANA, FECHA_INICIO, FECHA_FIN, ESTADO)
VALUES (seq_semana.NEXTVAL, 1, DATE '2026-04-01', DATE '2026-04-07', 'Y');
INSERT INTO SEMANA_ACADEMICA (ID_SEMANA, NUMERO_SEMANA, FECHA_INICIO, FECHA_FIN, ESTADO)
VALUES (seq_semana.NEXTVAL, 2, DATE '2026-04-08', DATE '2026-04-14', 'Y');

-- BLOQUES HORARIOS
INSERT INTO BLOQUE_HORARIO (ID_BLOQUE, HORA_INICIO, HORA_FIN, ESTADO)
VALUES (seq_bloque_horario.NEXTVAL, '08:00', '10:00', 'Y');
INSERT INTO BLOQUE_HORARIO (ID_BLOQUE, HORA_INICIO, HORA_FIN, ESTADO)
VALUES (seq_bloque_horario.NEXTVAL, '10:00', '12:00', 'Y');

-- AULAS
INSERT INTO AULA (ID_AULA, NOMBRE, CAPACIDAD, UBICACION, ESTADO)
VALUES (seq_aula.NEXTVAL, 'A-101', 30, NULL, 'Y');
INSERT INTO AULA (ID_AULA, NOMBRE, CAPACIDAD, UBICACION, ESTADO)
VALUES (seq_aula.NEXTVAL, 'SIM-01', 15, NULL, 'Y');

-- ACTIVIDADES
INSERT INTO ACTIVIDAD (ID_ACTIVIDAD, NOMBRE, TIPO, ID_CURSO, ESTADO)
VALUES (seq_actividad.NEXTVAL, 'Examen Parcial', 'EXAMEN', 1, 'Y');
INSERT INTO ACTIVIDAD (ID_ACTIVIDAD, NOMBRE, TIPO, ID_CURSO, ESTADO)
VALUES (seq_actividad.NEXTVAL, 'Evaluación Continua', 'PRACTICA', 1, 'Y');

INSERT INTO HORARIO (ID_HORARIO,
                     DIA_SEMANA,
                     FECHA,
                     ID_SEMANA,
                     ID_AULA,
                     ID_BLOQUE,
                     ID_ACTIVIDAD,
                     ID_INSTRUCTOR,
                     ESTADO)
VALUES (seq_horario.NEXTVAL,
        'LUNES',
        DATE '2026-04-01',
        1,
        1,
        1,
        1,
        1,
        'Y');


COMMIT;