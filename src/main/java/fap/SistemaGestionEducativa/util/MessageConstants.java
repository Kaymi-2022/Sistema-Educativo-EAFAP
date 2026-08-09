package fap.SistemaGestionEducativa.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class MessageConstants {

    // Usuario
    public static final String USER_UPDATED = "Usuario actualizado correctamente.";

    public static final String USER_DELETED = "Usuario eliminado correctamente.";

    public static final String USER_NOT_FOUND = "Usuario no encontrado.";

    public static final String USER_ALREADY_EXISTS = "El usuario ya existe.";

    public static final String SUCCESS = "Operación realizada correctamente.";

    public static final String USER_CREATED = "Usuario registrado correctamente.";

    public static final String USER_DNI_EXISTS = "El DNI ya se encuentra registrado.";

    public static final String USERNAME_EXISTS = "El nombre de usuario ya existe.";

    public static final String EMAIL_EXISTS = "El correo electrónico ya se encuentra registrado.";

    //Rol
    public static final String ROLE_DELETED = "Rol eliminado correctamente.";

    public static final String ROLE_NOT_FOUND = "Rol no encontrado.";

    public static final String ROLE_ALREADY_EXISTS = "El rol ya existe.";

    public static final String ROLE_UPDATED = "Rol actualizado correctamente.";

    public static final String ROLE_CREATED = "Rol registrado correctamente.";

    //Rol-Usuario
    public static final String ROLE_ASSIGNED = "Rol asignado correctamente.";

    public static final String ROLE_REMOVED = "Asignación de rol eliminada correctamente.";

    public static final String ROLE_ALREADY_ASSIGNED = "El usuario ya tiene asignado este rol.";

    public static final String ROLE_ASSIGNMENT_NOT_FOUND = "La asignación del rol para el usuario no existe.";

    public static final String USER_INACTIVE = "El usuario se encuentra inactivo.";

    public static final String ROLE_INACTIVE = "El rol se encuentra inactivo.";


    // Curso

    public static final String COURSE_CREATED = "Curso registrado correctamente.";

    public static final String COURSE_UPDATED = "Curso actualizado correctamente.";

    public static final String COURSE_NOT_FOUND = "Curso no encontrado.";

    public static final String COURSE_DELETED = "Curso eliminado correctamente.";
    public static final String PERIOD_NOT_FOUND = "Período académico no encontrado.";
    public static final String PERIOD_CREATED = "Período académico registrado correctamente.";
    public static final String PERIOD_UPDATED = "Período académico actualizado correctamente.";
    public static final String PERIOD_DELETED = "Período académico eliminado correctamente.";
    public static final String PERIOD_ALREADY_EXISTS = "El período académico ya existe.";
    public static final String PERIOD_INACTIVE = "El período académico se encuentra inactivo.";
    public static final String PERIOD_INVALID_RANGE = "La fecha de inicio debe ser anterior a la fecha fin.";
    public static final String TEACHER_NOT_FOUND = "Docente no encontrado.";
    public static final String TEACHER_INACTIVE = "El docente se encuentra inactivo.";
    public static final String COURSE_INACTIVE = "El curso se encuentra inactivo.";
    public static final String COURSE_ALREADY_EXISTS = "El curso ya existe.";

    // Evaluación

    public static final String EVALUATION_CREATED = "Evaluación registrada correctamente.";

    public static final String GRADE_REGISTERED = "Nota registrada correctamente.";

    // Categoria

    public static final String CATEGORY_CREATED = "Categoría registrada correctamente.";

    public static final String CATEGORY_UPDATED = "Categoría actualizada correctamente.";

    public static final String CATEGORY_NOT_FOUND = "Categoría no encontrada.";

    public static final String CATEGORY_ALREADY_EXISTS = "La categoría ya existe.";

    public static final String CATEGORY_DELETED = "Categoría eliminada correctamente.";

    public static final String CATEGORY_INACTIVE = "La categoría se encuentra inactiva.";
    public static final String WEEK_CREATED = "Semana académica registrada correctamente.";
    public static final String WEEK_UPDATED = "Semana académica actualizada correctamente.";
    public static final String WEEK_DELETED = "Semana académica eliminada correctamente.";
    public static final String WEEK_NOT_FOUND = "Semana académica no encontrada.";
    public static final String WEEK_ALREADY_EXISTS = "La semana académica ya existe.";
    public static final String WEEK_INACTIVE = "La semana académica se encuentra inactiva.";
    public static final String WEEK_INVALID_RANGE = "La fecha de inicio debe ser anterior a la fecha fin.";

    public static final String AULA_CREATED = "Aula registrada correctamente.";
    public static final String AULA_UPDATED = "Aula actualizada correctamente.";
    public static final String AULA_DELETED = "Aula eliminada correctamente.";
    public static final String AULA_NOT_FOUND = "Aula no encontrada.";
    public static final String AULA_ALREADY_EXISTS = "El aula ya existe.";
    public static final String AULA_INACTIVE = "El aula se encuentra inactiva.";

    public static final String BLOCK_CREATED = "Bloque horario registrado correctamente.";
    public static final String BLOCK_UPDATED = "Bloque horario actualizado correctamente.";
    public static final String BLOCK_DELETED = "Bloque horario eliminado correctamente.";
    public static final String BLOCK_NOT_FOUND = "Bloque horario no encontrado.";
    public static final String BLOCK_ALREADY_EXISTS = "El bloque horario ya existe.";
    public static final String BLOCK_INACTIVE = "El bloque horario se encuentra inactivo.";
    public static final String BLOCK_INVALID_RANGE = "La hora de inicio debe ser anterior a la hora fin.";

    public static final String ACTIVITY_CREATED = "Actividad registrada correctamente.";
    public static final String ACTIVITY_UPDATED = "Actividad actualizada correctamente.";
    public static final String ACTIVITY_DELETED = "Actividad eliminada correctamente.";
    public static final String ACTIVITY_NOT_FOUND = "Actividad no encontrada.";
    public static final String ACTIVITY_ALREADY_EXISTS = "La actividad ya existe.";
    public static final String ACTIVITY_INACTIVE = "La actividad se encuentra inactiva.";

    public static final String SCHEDULE_CREATED = "Horario registrado correctamente.";
    public static final String SCHEDULE_UPDATED = "Horario actualizado correctamente.";
    public static final String SCHEDULE_DELETED = "Horario eliminado correctamente.";
    public static final String SCHEDULE_NOT_FOUND = "Horario no encontrado.";
    public static final String SCHEDULE_ALREADY_EXISTS = "El horario ya existe.";
    public static final String SCHEDULE_INACTIVE = "El horario se encuentra inactivo.";
    public static final String SCHEDULE_CONFLICT = "Existe un conflicto con el horario solicitado.";

    public static final String ENROLLMENT_CREATED = "Matrícula registrada correctamente.";
    public static final String ENROLLMENT_DELETED = "Matrícula eliminada correctamente.";
    public static final String ENROLLMENT_NOT_FOUND = "Matrícula no encontrada.";
    public static final String STUDENT_NOT_FOUND = "Estudiante no encontrado.";
    public static final String STUDENT_INACTIVE = "El estudiante se encuentra inactivo.";
    public static final String ENROLLMENT_ALREADY_EXISTS = "La matrícula ya existe.";
    public static final String ENROLLMENT_INACTIVE = "La matrícula se encuentra inactiva.";
    public static final String EVALUATION_UPDATED = "Evaluación actualizada correctamente.";
    public static final String EVALUATION_DELETED = "Evaluación eliminada correctamente.";
    public static final String EVALUATION_NOT_FOUND = "Evaluación no encontrada.";
    public static final String EVALUATION_ALREADY_EXISTS = "La evaluación ya existe.";
    public static final String EVALUATION_INACTIVE = "La evaluación se encuentra inactiva.";
    public static final String GRADE_CREATED = "Nota registrada correctamente.";
    public static final String GRADE_UPDATED = "Nota actualizada correctamente.";
    public static final String GRADE_DELETED = "Nota eliminada correctamente.";
    public static final String GRADE_NOT_FOUND = "Nota no encontrada.";
    public static final String STUDENT_NOT_ENROLLED = "El estudiante no está matriculado en el curso.";
    public static final String GRADE_ALREADY_EXISTS = "La nota ya existe.";
    public static final String GRADE_INACTIVE = "La nota se encuentra inactiva.";
    public static final String RESULT_CREATED = "Resultado registrado correctamente.";
    public static final String RESULT_UPDATED = "Resultado actualizado correctamente.";
    public static final String RESULT_DELETED = "Resultado eliminado correctamente.";
    public static final String RESULT_NOT_FOUND = "Resultado no encontrado.";
    public static final String NO_GRADES_TO_CALCULATE = "No hay notas para calcular.";
    public static final String INVALID_GRADE_DATA = "Datos de nota inválidos.";
    public static final String INVALID_EVALUATION_WEIGHT = "Peso de evaluación inválido.";
    public static final String RESULT_INACTIVE = "El resultado se encuentra inactivo.";
    public static final String RESULT_ALREADY_EXISTS = "El resultado ya existe.";
}
