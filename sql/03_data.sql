-- ROLES
INSERT INTO rol VALUES (seq_rol.NEXTVAL, 'ADMIN', 'Y');
INSERT INTO rol VALUES (seq_rol.NEXTVAL, 'DOCENTE', 'Y');
INSERT INTO rol VALUES (seq_rol.NEXTVAL, 'DISCENTE', 'Y');

-- USUARIOS
INSERT INTO usuario VALUES (
                               seq_usuario.NEXTVAL, '45678901', 'Juan', 'Pérez',
                               'admin@eafap.mil.pe', 'admin', '1234', 'Y'
                           );

INSERT INTO usuario VALUES (
                               seq_usuario.NEXTVAL, '46781234', 'Carlos', 'Rojas',
                               'docente@eafap.mil.pe', 'crojas', '1234', 'Y'
                           );

INSERT INTO usuario VALUES (
                               seq_usuario.NEXTVAL, '47892345', 'Luis', 'Fernández',
                               'alumno@eafap.mil.pe', 'lfernandez', '1234', 'Y'
                           );

-- USUARIO_ROL
INSERT INTO usuario_rol VALUES (1, 1);
INSERT INTO usuario_rol VALUES (2, 2);
INSERT INTO usuario_rol VALUES (3, 3);

-- CATEGORIAS
INSERT INTO categoria VALUES (seq_categoria.NEXTVAL, 'MORAL', 'Formación ética', 'Y');
INSERT INTO categoria VALUES (seq_categoria.NEXTVAL, 'MILITAR', 'Disciplina militar', 'Y');
INSERT INTO categoria VALUES (seq_categoria.NEXTVAL, 'ACADEMICO', 'Formación académica', 'Y');

-- CURSOS
INSERT INTO curso VALUES (
                             seq_curso.NEXTVAL, 'Formación Moral', 'Valores institucionales', 1, 2, '2026-I', 'Y'
                         );

INSERT INTO curso VALUES (
                             seq_curso.NEXTVAL, 'Instrucción Militar Básica', 'Orden cerrado', 2, 2, '2026-I', 'Y'
                         );

INSERT INTO curso VALUES (
                             seq_curso.NEXTVAL, 'Motores Aeronáuticos I', 'Motores aeronáuticos', 3, 2, '2026-I', 'Y'
                         );

-- MATRÍCULA
INSERT INTO curso_discente VALUES (seq_curso_discente.NEXTVAL, 1, 3, SYSDATE, 'Y');
INSERT INTO curso_discente VALUES (seq_curso_discente.NEXTVAL, 2, 3, SYSDATE, 'Y');
INSERT INTO curso_discente VALUES (seq_curso_discente.NEXTVAL, 3, 3, SYSDATE, 'Y');

-- EVALUACIONES
INSERT INTO evaluacion VALUES (seq_evaluacion.NEXTVAL, 1, 'Conducta General', 'MORAL', 100, SYSDATE, 'Y');
INSERT INTO evaluacion VALUES (seq_evaluacion.NEXTVAL, 2, 'Orden Cerrado', 'MILITAR', 100, SYSDATE, 'Y');
INSERT INTO evaluacion VALUES (seq_evaluacion.NEXTVAL, 3, 'Examen Parcial', 'ACADEMICO', 50, SYSDATE, 'Y');

-- NOTAS
INSERT INTO nota VALUES (seq_nota.NEXTVAL, 1, 3, 18.0, 'Excelente conducta', 'Y');
INSERT INTO nota VALUES (seq_nota.NEXTVAL, 2, 3, 16.5, 'Buen desempeño', 'Y');
INSERT INTO nota VALUES (seq_nota.NEXTVAL, 3, 3, 14.0, 'Debe reforzar', 'Y');

-- RESULTADOS
INSERT INTO resultado_curso VALUES (seq_resultado.NEXTVAL, 1, 3, 18.0, 'APTO', SYSDATE, 'Y');
INSERT INTO resultado_curso VALUES (seq_resultado.NEXTVAL, 2, 3, 16.5, 'APTO', SYSDATE, 'Y');
INSERT INTO resultado_curso VALUES (seq_resultado.NEXTVAL, 3, 3, 11.0, 'NO APTO', SYSDATE, 'Y');

-- SEMANA ACADEMICA
INSERT INTO semana_academica VALUES (seq_semana_academica.NEXTVAL, 'Semana 1', 'Introducción a la formación militar', TO_DATE('01-09-2026', 'DD-MM-YYYY'), TO_DATE('07-09-2026', 'DD-MM-YYYY'), 'Y');
INSERT INTO semana_academica VALUES (seq_semana_academica.NEXTVAL, 'Semana 2', 'Técnicas de orden cerrado', TO_DATE('08-09-2026', 'DD-MM-YYYY'), TO_DATE('14-09-2026', 'DD-MM-YYYY'), 'Y');
INSERT INTO semana_academica VALUES (seq_semana_academica.NEXTVAL, 'Semana 3', 'Fundamentos de motores aeronáuticos', TO_DATE('15-09-2026', 'DD-MM-YYYY'), TO_DATE('21-09-2026', 'DD-MM-YYYY'), 'Y');

-- AULAS
INSERT INTO aula VALUES (seq_aula.NEXTVAL, 'Aula 101', 30, 'Escuela de Formación', 'Y');
INSERT INTO aula VALUES (seq_aula.NEXTVAL, 'Aula 102', 30, 'Escuela de Formación', 'Y');
INSERT INTO aula VALUES (seq_aula.NEXTVAL, 'Aula 103', 30, 'Escuela de Formación', 'Y');

-- BLOQUE DE HORARIO
INSERT INTO bloque_horario VALUES (seq_bloque_horario.NEXTVAL, '08:00', '10:00', 'Y');
INSERT INTO bloque_horario VALUES (seq_bloque_horario.NEXTVAL, '10:00', '12:00', 'Y');
INSERT INTO bloque_horario VALUES (seq_bloque_horario.NEXTVAL, '14:00', '16:00', 'Y');

-- TABLA ACTIVIDAD
-- Corregido: nombre, tipo, id_curso, estado
INSERT INTO actividad VALUES (seq_actividad.NEXTVAL, 'Formación Moral', 'CLASE', 1, 'Y');
INSERT INTO actividad VALUES (seq_actividad.NEXTVAL, 'Instrucción Militar Básica', 'PRACTICA', 2, 'Y');
INSERT INTO actividad VALUES (seq_actividad.NEXTVAL, 'Motores Aeronáuticos I', 'EVALUACION', 3, 'Y');

-- HORARIO
-- Corregido: Uso de comillas simples para los días de la semana
INSERT INTO horario VALUES (seq_horario.NEXTVAL, 'LUNES', SYSDATE, 1, 1, 1, 1, 2, 'Y');
INSERT INTO horario VALUES (seq_horario.NEXTVAL, 'MARTES', SYSDATE, 2, 2, 2, 2, 2, 'Y');
INSERT INTO horario VALUES (seq_horario.NEXTVAL, 'MIERCOLES', SYSDATE, 3, 3, 3, 3, 2, 'Y');

COMMIT;