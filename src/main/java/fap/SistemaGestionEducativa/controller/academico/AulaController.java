package fap.SistemaGestionEducativa.controller.academico;

import fap.SistemaGestionEducativa.dto.request.academico.AulaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.AulaResponse;
import fap.SistemaGestionEducativa.service.business.AulaService;
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
@RequestMapping("/api/academico/aulas")
@Tag(name = "Académico - Aulas")
public class AulaController {

    private final AulaService service;

    @PostMapping
    @Operation(summary = "Registrar aula", description = "Crea un aula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aula creada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Aula duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<AulaResponse>> registrar(@Valid @RequestBody AulaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @PutMapping("/{idAula}")
    @Operation(summary = "Actualizar aula", description = "Modifica un aula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula actualizada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Aula no encontrada"),
            @ApiResponse(responseCode = "409", description = "Aula duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<AulaResponse>> actualizar(@PathVariable Long idAula,
                                                                 @Valid @RequestBody AulaRequest request) {
        return ResponseEntity.ok(service.actualizar(idAula, request));
    }

    @GetMapping("/{idAula}")
    @Operation(summary = "Obtener aula", description = "Obtiene un aula por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula encontrada"),
            @ApiResponse(responseCode = "404", description = "Aula no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<AulaResponse>> obtenerPorId(@PathVariable Long idAula) {
        return ResponseEntity.ok(service.obtenerPorId(idAula));
    }

    @GetMapping
    @Operation(summary = "Listar aulas", description = "Obtiene todas las aulas activas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<AulaResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{idAula}")
    @Operation(summary = "Eliminar aula", description = "Elimina lógicamente un aula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula eliminada"),
            @ApiResponse(responseCode = "404", description = "Aula no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idAula) {
        return ResponseEntity.ok(service.eliminar(idAula));
    }
}
