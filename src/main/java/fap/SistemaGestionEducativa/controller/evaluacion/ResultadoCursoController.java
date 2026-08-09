package fap.SistemaGestionEducativa.controller.evaluacion;

import fap.SistemaGestionEducativa.dto.request.evaluacion.ResultadoCursoRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.ResultadoCursoResponse;
import fap.SistemaGestionEducativa.service.business.ResultadoCursoService;
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
@RequestMapping("/api/evaluacion/resultados")
@Tag(name = "Evaluación - Resultados")
public class ResultadoCursoController {

    private final ResultadoCursoService service;

    @PostMapping
    @Operation(summary = "Registrar resultado", description = "Calcula y registra el resultado final de un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Resultado creado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Referencia no encontrada"),
            @ApiResponse(responseCode = "409", description = "Resultado duplicado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<ResultadoCursoResponse>> registrar(@Valid @RequestBody ResultadoCursoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @GetMapping("/{idResultado}")
    @Operation(summary = "Obtener resultado", description = "Obtiene un resultado por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado encontrado"),
            @ApiResponse(responseCode = "404", description = "Resultado no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<ResultadoCursoResponse>> obtenerPorId(@PathVariable Long idResultado) {
        return ResponseEntity.ok(service.obtenerPorId(idResultado));
    }

    @GetMapping("/curso/{idCurso}/discente/{idDiscente}")
    @Operation(summary = "Obtener resultado por curso y discente", description = "Busca el resultado final de un estudiante en un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado encontrado"),
            @ApiResponse(responseCode = "404", description = "Resultado no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<ResultadoCursoResponse>> obtenerPorCursoxDiscente(@PathVariable Long idCurso,
                                                                                         @PathVariable Long idDiscente) {
        return ResponseEntity.ok(service.obtenerPorCursoxDiscente(idCurso, idDiscente));
    }

    @GetMapping
    @Operation(summary = "Listar resultados", description = "Obtiene todos los resultados activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<ResultadoCursoResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{idResultado}")
    @Operation(summary = "Eliminar resultado", description = "Elimina lógicamente un resultado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado eliminado"),
            @ApiResponse(responseCode = "404", description = "Resultado no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idResultado) {
        return ResponseEntity.ok(service.eliminar(idResultado));
    }
}
