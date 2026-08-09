package fap.SistemaGestionEducativa.controller.academico;

import fap.SistemaGestionEducativa.dto.request.academico.HorarioRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.HorarioResponse;
import fap.SistemaGestionEducativa.service.business.HorarioService;
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
@RequestMapping("/api/academico/horarios")
@Tag(name = "Académico - Horarios")
public class HorarioController {

    private final HorarioService service;

    @PostMapping
    @Operation(summary = "Registrar horario", description = "Crea un horario académico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Horario creado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Referencia no encontrada"),
            @ApiResponse(responseCode = "409", description = "Conflicto de horario"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<HorarioResponse>> registrar(@Valid @RequestBody HorarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @PutMapping("/{idHorario}")
    @Operation(summary = "Actualizar horario", description = "Modifica un horario académico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Horario actualizado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Horario no encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflicto de horario"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<HorarioResponse>> actualizar(@PathVariable Long idHorario,
                                                                    @Valid @RequestBody HorarioRequest request) {
        return ResponseEntity.ok(service.actualizar(idHorario, request));
    }

    @GetMapping("/{idHorario}")
    @Operation(summary = "Obtener horario", description = "Obtiene un horario por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Horario encontrado"),
            @ApiResponse(responseCode = "404", description = "Horario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<HorarioResponse>> obtenerPorId(@PathVariable Long idHorario) {
        return ResponseEntity.ok(service.obtenerPorId(idHorario));
    }

    @GetMapping
    @Operation(summary = "Listar horarios", description = "Obtiene todos los horarios activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<HorarioResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/docente/{idDocente}")
    @Operation(summary = "Listar horarios por docente", description = "Obtiene horarios de un docente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "404", description = "Docente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<HorarioResponse>>> listarPorDocente(@PathVariable Long idDocente) {
        return ResponseEntity.ok(service.listarPorDocente(idDocente));
    }

    @GetMapping("/curso/{idCurso}")
    @Operation(summary = "Listar horarios por curso", description = "Obtiene horarios de un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<HorarioResponse>>> listarPorCurso(@PathVariable Long idCurso) {
        return ResponseEntity.ok(service.listarPorCurso(idCurso));
    }

    @GetMapping("/semana/{idSemana}")
    @Operation(summary = "Listar horarios por semana", description = "Obtiene horarios de una semana académica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "404", description = "Semana no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<HorarioResponse>>> listarPorSemana(@PathVariable Long idSemana) {
        return ResponseEntity.ok(service.listarPorSemana(idSemana));
    }

    @DeleteMapping("/{idHorario}")
    @Operation(summary = "Eliminar horario", description = "Elimina lógicamente un horario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Horario eliminado"),
            @ApiResponse(responseCode = "404", description = "Horario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idHorario) {
        return ResponseEntity.ok(service.eliminar(idHorario));
    }
}
