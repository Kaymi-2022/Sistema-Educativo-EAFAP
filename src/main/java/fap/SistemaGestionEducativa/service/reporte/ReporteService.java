package fap.SistemaGestionEducativa.service.reporte;

import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.ResultadoCursoResponse;
import fap.SistemaGestionEducativa.dto.response.reporte.CursoReporteResponse;
import fap.SistemaGestionEducativa.dto.response.reporte.NotaDetalleResponse;
import fap.SistemaGestionEducativa.dto.response.reporte.PromedioGeneralResponse;
import fap.SistemaGestionEducativa.dto.response.reporte.ReporteAcademicoResponse;

import java.util.List;

public interface ReporteService {

    /**
     * Obtiene el historial académico completo del estudiante.
     */
    RestResponse<ReporteAcademicoResponse> obtenerHistorialAcademico(String codigoEstudiante);


    /**
     * Obtiene todas las notas del estudiante.
     */
    RestResponse<List<NotaDetalleResponse>> obtenerNotas(String codigoEstudiante);


    /**
     * Obtiene los resultados finales por curso.
     */
    RestResponse<List<ResultadoCursoResponse>> obtenerResultados(String codigoEstudiante);


    /**
     * Obtiene el promedio general del estudiante.
     */
    RestResponse<PromedioGeneralResponse> obtenerPromedioGeneral(String codigoEstudiante);


    /**
     * Obtiene el detalle de un curso específico.
     */
    RestResponse<CursoReporteResponse> obtenerCurso(String codigoEstudiante, Long idCurso);

}