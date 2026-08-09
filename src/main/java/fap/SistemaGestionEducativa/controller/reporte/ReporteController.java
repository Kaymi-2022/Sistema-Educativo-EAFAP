package fap.SistemaGestionEducativa.controller.reporte;

import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.ResultadoCursoResponse;
import fap.SistemaGestionEducativa.dto.response.reporte.CursoReporteResponse;
import fap.SistemaGestionEducativa.dto.response.reporte.NotaDetalleResponse;
import fap.SistemaGestionEducativa.dto.response.reporte.PromedioGeneralResponse;
import fap.SistemaGestionEducativa.dto.response.reporte.ReporteAcademicoResponse;
import fap.SistemaGestionEducativa.service.reporte.ReporteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reportes")
@Tag(name = "Reportes")
public class ReporteController {

    private final ReporteService service;

    @GetMapping("/{codigoEstudiante}")
    public ResponseEntity<RestResponse<ReporteAcademicoResponse>> obtenerHistorialAcademico(@PathVariable String codigoEstudiante) {
        return ResponseEntity.ok(service.obtenerHistorialAcademico(codigoEstudiante));
    }

    @GetMapping("/{codigoEstudiante}/notas")
    public ResponseEntity<RestResponse<List<NotaDetalleResponse>>> obtenerNotas(@PathVariable String codigoEstudiante) {
        return ResponseEntity.ok(service.obtenerNotas(codigoEstudiante));
    }

    @GetMapping("/{codigoEstudiante}/resultados")
    public ResponseEntity<RestResponse<List<ResultadoCursoResponse>>> obtenerResultados(@PathVariable String codigoEstudiante) {
        return ResponseEntity.ok(service.obtenerResultados(codigoEstudiante));
    }

    @GetMapping("/{codigoEstudiante}/promedio")
    public ResponseEntity<RestResponse<PromedioGeneralResponse>> obtenerPromedioGeneral(@PathVariable String codigoEstudiante) {
        return ResponseEntity.ok(service.obtenerPromedioGeneral(codigoEstudiante));
    }

    @GetMapping("/{codigoEstudiante}/cursos/{idCurso}")
    public ResponseEntity<RestResponse<CursoReporteResponse>> obtenerCurso(@PathVariable String codigoEstudiante,
                                                                           @PathVariable Long idCurso) {
        return ResponseEntity.ok(service.obtenerCurso(codigoEstudiante, idCurso));
    }
}
