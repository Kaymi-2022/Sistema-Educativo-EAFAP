package fap.SistemaGestionEducativa.controller.evaluacion;

import fap.SistemaGestionEducativa.dto.request.evaluacion.EvaluacionRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.EvaluacionResponse;
import fap.SistemaGestionEducativa.service.business.EvaluacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/evaluacion/evaluaciones")
@Tag(name = "Evaluación - Evaluaciones")
public class EvaluacionController {

    private final EvaluacionService service;

    @PostMapping
    @Operation(summary = "Registrar evaluación", description = "Crea una evaluación para un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evaluación creada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Referencia no encontrada"),
            @ApiResponse(responseCode = "409", description = "Evaluación duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<EvaluacionResponse>> registrar(@Valid @RequestBody EvaluacionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @PutMapping("/{idEvaluacion}")
    @Operation(summary = "Actualizar evaluación", description = "Modifica una evaluación existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluación actualizada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Evaluación no encontrada"),
            @ApiResponse(responseCode = "409", description = "Evaluación duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<EvaluacionResponse>> actualizar(@PathVariable Long idEvaluacion,
                                                                       @Valid @RequestBody EvaluacionRequest request) {
        return ResponseEntity.ok(service.actualizar(idEvaluacion, request));
    }

    @GetMapping("/{idEvaluacion}")
    @Operation(summary = "Obtener evaluación", description = "Obtiene una evaluación por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluación encontrada"),
            @ApiResponse(responseCode = "404", description = "Evaluación no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<EvaluacionResponse>> obtenerPorId(@PathVariable Long idEvaluacion) {
        return ResponseEntity.ok(service.obtenerPorId(idEvaluacion));
    }

    @GetMapping
    @Operation(summary = "Listar evaluaciones", description = "Obtiene todas las evaluaciones activas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<EvaluacionResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{idEvaluacion}")
    @Operation(summary = "Eliminar evaluación", description = "Elimina lógicamente una evaluación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluación eliminada"),
            @ApiResponse(responseCode = "404", description = "Evaluación no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idEvaluacion) {
        return ResponseEntity.ok(service.eliminar(idEvaluacion));
    }
}
