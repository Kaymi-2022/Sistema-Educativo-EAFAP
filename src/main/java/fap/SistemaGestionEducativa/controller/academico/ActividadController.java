package fap.SistemaGestionEducativa.controller.academico;

import fap.SistemaGestionEducativa.dto.request.academico.ActividadRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.ActividadResponse;
import fap.SistemaGestionEducativa.service.business.ActividadService;
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
@RequestMapping("/api/academico/actividades")
@Tag(name = "Académico - Actividades")
public class ActividadController {

    private final ActividadService service;

    @PostMapping
    @Operation(summary = "Registrar actividad", description = "Crea una actividad académica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Actividad creada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Referencia no encontrada"),
            @ApiResponse(responseCode = "409", description = "Actividad duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<ActividadResponse>> registrar(@Valid @RequestBody ActividadRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @PutMapping("/{idActividad}")
    @Operation(summary = "Actualizar actividad", description = "Modifica una actividad académica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actividad actualizada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Actividad no encontrada"),
            @ApiResponse(responseCode = "409", description = "Actividad duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<ActividadResponse>> actualizar(@PathVariable Long idActividad,
                                                                      @Valid @RequestBody ActividadRequest request) {
        return ResponseEntity.ok(service.actualizar(idActividad, request));
    }

    @GetMapping
    @Operation(summary = "Listar actividades", description = "Obtiene todas las actividades activas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<ActividadResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/curso/{idCurso}")
    @Operation(summary = "Listar actividades por curso", description = "Obtiene actividades de un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<ActividadResponse>>> listarPorCurso(@PathVariable Long idCurso) {
        return ResponseEntity.ok(service.listarPorCurso(idCurso));
    }

    @DeleteMapping("/{idActividad}")
    @Operation(summary = "Eliminar actividad", description = "Elimina lógicamente una actividad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actividad eliminada"),
            @ApiResponse(responseCode = "404", description = "Actividad no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idActividad) {
        return ResponseEntity.ok(service.eliminar(idActividad));
    }
}
