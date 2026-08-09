package fap.SistemaGestionEducativa.controller.academico;

import fap.SistemaGestionEducativa.dto.request.academico.PeriodoAcademicoRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.PeriodoAcademicoResponse;
import fap.SistemaGestionEducativa.service.business.PeriodoAcademicoService;
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
@RequestMapping("/api/academico/periodos")
@Tag(name = "Académico - Períodos")
public class PeriodoAcademicoController {

    private final PeriodoAcademicoService service;

    @PostMapping
    @Operation(summary = "Registrar período", description = "Crea un período académico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Período creado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Período duplicado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<PeriodoAcademicoResponse>> registrar(@Valid @RequestBody PeriodoAcademicoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @PutMapping("/{idPeriodo}")
    @Operation(summary = "Actualizar período", description = "Modifica un período académico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Período actualizado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Período no encontrado"),
            @ApiResponse(responseCode = "409", description = "Período duplicado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<PeriodoAcademicoResponse>> actualizar(@PathVariable Long idPeriodo,
                                                                             @Valid @RequestBody PeriodoAcademicoRequest request) {
        return ResponseEntity.ok(service.actualizar(idPeriodo, request));
    }

    @GetMapping("/{idPeriodo}")
    @Operation(summary = "Obtener período", description = "Obtiene un período por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Período encontrado"),
            @ApiResponse(responseCode = "404", description = "Período no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<PeriodoAcademicoResponse>> obtenerPorId(@PathVariable Long idPeriodo) {
        return ResponseEntity.ok(service.obtenerPorId(idPeriodo));
    }

    @GetMapping
    @Operation(summary = "Listar períodos", description = "Obtiene todos los períodos activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<PeriodoAcademicoResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{idPeriodo}")
    @Operation(summary = "Eliminar período", description = "Elimina lógicamente un período")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Período eliminado"),
            @ApiResponse(responseCode = "404", description = "Período no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idPeriodo) {
        return ResponseEntity.ok(service.eliminar(idPeriodo));
    }
}
