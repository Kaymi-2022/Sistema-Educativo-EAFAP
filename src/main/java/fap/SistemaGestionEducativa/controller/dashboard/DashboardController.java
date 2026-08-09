package fap.SistemaGestionEducativa.controller.dashboard;

import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.dashboard.CursoDashboardResponse;
import fap.SistemaGestionEducativa.dto.response.dashboard.DashboardResponse;
import fap.SistemaGestionEducativa.dto.response.dashboard.EvaluacionPendienteResponse;
import fap.SistemaGestionEducativa.dto.response.dashboard.HorarioHoyResponse;
import fap.SistemaGestionEducativa.dto.response.dashboard.ResumenCategoriaResponse;
import fap.SistemaGestionEducativa.service.dashboard.DashboardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard")
public class DashboardController {

    private final DashboardService service;

    @GetMapping
    public ResponseEntity<RestResponse<DashboardResponse>> obtenerDashboard() {
        return ResponseEntity.ok(service.obtenerDashboard());
    }

    @GetMapping("/indicadores")
    public ResponseEntity<RestResponse<DashboardResponse>> obtenerIndicadores() {
        return ResponseEntity.ok(service.obtenerIndicadores());
    }

    @GetMapping("/cursos")
    public ResponseEntity<RestResponse<List<CursoDashboardResponse>>> obtenerCursosActivos() {
        return ResponseEntity.ok(service.obtenerCursosActivos());
    }

    @GetMapping("/categorias")
    public ResponseEntity<RestResponse<List<ResumenCategoriaResponse>>> obtenerCursosPorCategoria() {
        return ResponseEntity.ok(service.obtenerCursosPorCategoria());
    }

    @GetMapping("/evaluaciones-pendientes")
    public ResponseEntity<RestResponse<List<EvaluacionPendienteResponse>>> obtenerEvaluacionesPendientes() {
        return ResponseEntity.ok(service.obtenerEvaluacionesPendientes());
    }

    @GetMapping("/horario-hoy")
    public ResponseEntity<RestResponse<List<HorarioHoyResponse>>> obtenerHorarioHoy() {
        return ResponseEntity.ok(service.obtenerHorarioHoy());
    }
}
