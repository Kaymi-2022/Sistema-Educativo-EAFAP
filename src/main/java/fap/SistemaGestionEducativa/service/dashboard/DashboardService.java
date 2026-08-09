package fap.SistemaGestionEducativa.service.dashboard;

import fap.SistemaGestionEducativa.dto.response.*;
import fap.SistemaGestionEducativa.dto.response.dashboard.*;

import java.util.List;

public interface DashboardService {

    /**
     * Obtiene toda la información del panel principal.
     */
    RestResponse<DashboardResponse> obtenerDashboard();


    /**
     * Indicadores generales del sistema.
     */
    RestResponse<DashboardResponse> obtenerIndicadores();


    /**
     * Cursos activos.
     */
    RestResponse<List<CursoDashboardResponse>> obtenerCursosActivos();


    /**
     * Cursos por categoría.
     */
    RestResponse<List<ResumenCategoriaResponse>> obtenerCursosPorCategoria();


    /**
     * Evaluaciones programadas pendientes.
     */
    RestResponse<List<EvaluacionPendienteResponse>> obtenerEvaluacionesPendientes();


    /**
     * Horario del día.
     */
    RestResponse<List<HorarioHoyResponse>> obtenerHorarioHoy();

}