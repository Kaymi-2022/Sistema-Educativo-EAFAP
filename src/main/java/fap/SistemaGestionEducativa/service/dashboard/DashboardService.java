package fap.SistemaGestionEducativa.service.dashboard;

import fap.SistemaGestionEducativa.dto.response.*;
import fap.SistemaGestionEducativa.dto.response.dashboard.*;

import java.util.List;

public interface DashboardService {

    /**
     * Obtiene toda la información del panel principal.
     */
    ApiResponse<DashboardResponse> obtenerDashboard();


    /**
     * Indicadores generales del sistema.
     */
    ApiResponse<DashboardResponse> obtenerIndicadores();


    /**
     * Cursos activos.
     */
    ApiResponse<List<CursoDashboardResponse>> obtenerCursosActivos();


    /**
     * Cursos por categoría.
     */
    ApiResponse<List<ResumenCategoriaResponse>> obtenerCursosPorCategoria();


    /**
     * Evaluaciones programadas pendientes.
     */
    ApiResponse<List<EvaluacionPendienteResponse>> obtenerEvaluacionesPendientes();


    /**
     * Horario del día.
     */
    ApiResponse<List<HorarioHoyResponse>> obtenerHorarioHoy();

}