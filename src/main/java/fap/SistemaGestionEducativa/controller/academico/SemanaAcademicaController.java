package fap.SistemaGestionEducativa.controller.academico;

import fap.SistemaGestionEducativa.dto.request.academico.SemanaAcademicaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.SemanaAcademicaResponse;
import fap.SistemaGestionEducativa.service.business.SemanaAcademicaService;
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
@RequestMapping("/api/academico/semanas")
@Tag(name = "Académico - Semanas")
public class SemanaAcademicaController {

    private final SemanaAcademicaService service;

    @PostMapping
    @Operation(summary = "Registrar semana", description = "Crea una semana académica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Semana creada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Semana duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<SemanaAcademicaResponse>> registrar(@Valid @RequestBody SemanaAcademicaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @PutMapping("/{idSemana}")
    @Operation(summary = "Actualizar semana", description = "Modifica una semana académica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Semana actualizada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Semana no encontrada"),
            @ApiResponse(responseCode = "409", description = "Semana duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<SemanaAcademicaResponse>> actualizar(@PathVariable Long idSemana,
                                                                            @Valid @RequestBody SemanaAcademicaRequest request) {
        return ResponseEntity.ok(service.actualizar(idSemana, request));
    }

    @GetMapping
    @Operation(summary = "Listar semanas", description = "Obtiene todas las semanas activas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<SemanaAcademicaResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{idSemana}")
    @Operation(summary = "Eliminar semana", description = "Elimina lógicamente una semana")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Semana eliminada"),
            @ApiResponse(responseCode = "404", description = "Semana no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idSemana) {
        return ResponseEntity.ok(service.eliminar(idSemana));
    }
}
