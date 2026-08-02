package fap.SistemaGestionEducativa.service.reporte;

public interface ReporteService {

    /**
     * Obtiene el historial académico completo del estudiante.
     *
     * @param codigoEstudiante código (username) del estudiante.
     */
    ApiResponse<ReporteAcademicoResponse> obtenerHistorialAcademico(
            String codigoEstudiante);


    /**
     * Obtiene todas las notas del estudiante.
     */
    ApiResponse<List<NotaDetalleResponse>> obtenerNotas(
            String codigoEstudiante);


    /**
     * Obtiene los resultados finales por curso.
     */
    ApiResponse<List<ResultadoCursoResponse>> obtenerResultados(
            String codigoEstudiante);


    /**
     * Obtiene el promedio general del estudiante.
     */
    ApiResponse<PromedioGeneralResponse> obtenerPromedioGeneral(
            String codigoEstudiante);


    /**
     * Obtiene el detalle de un curso específico.
     */
    ApiResponse<CursoReporteResponse> obtenerCurso(
            String codigoEstudiante,
            Long idCurso);

}