package fap.SistemaGestionEducativa.controller.evaluacion;

import fap.SistemaGestionEducativa.dto.request.evaluacion.NotaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.NotaResponse;
import fap.SistemaGestionEducativa.service.business.NotaService;
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
@RequestMapping("/api/evaluacion/notas")
@Tag(name = "Evaluación - Notas")
public class NotaController {

    private final NotaService service;

    @PostMapping
    @Operation(summary = "Registrar nota", description = "Crea una nota para un estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nota creada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Referencia no encontrada"),
            @ApiResponse(responseCode = "409", description = "Nota duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<NotaResponse>> registrar(@Valid @RequestBody NotaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @PutMapping("/{idNota}")
    @Operation(summary = "Actualizar nota", description = "Modifica una nota existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nota actualizada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Nota no encontrada"),
            @ApiResponse(responseCode = "409", description = "Nota duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<NotaResponse>> actualizar(@PathVariable Long idNota,
                                                                 @Valid @RequestBody NotaRequest request) {
        return ResponseEntity.ok(service.actualizar(idNota, request));
    }

    @GetMapping("/{idNota}")
    @Operation(summary = "Obtener nota", description = "Obtiene una nota por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nota encontrada"),
            @ApiResponse(responseCode = "404", description = "Nota no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<NotaResponse>> obtenerPorId(@PathVariable Long idNota) {
        return ResponseEntity.ok(service.obtenerPorId(idNota));
    }

    @GetMapping
    @Operation(summary = "Listar notas", description = "Obtiene todas las notas activas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<NotaResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{idNota}")
    @Operation(summary = "Eliminar nota", description = "Elimina lógicamente una nota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nota eliminada"),
            @ApiResponse(responseCode = "404", description = "Nota no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idNota) {
        return ResponseEntity.ok(service.eliminar(idNota));
    }
}
