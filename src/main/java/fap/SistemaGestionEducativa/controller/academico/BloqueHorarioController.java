package fap.SistemaGestionEducativa.controller.academico;

import fap.SistemaGestionEducativa.dto.request.academico.BloqueHorarioRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.BloqueHorarioResponse;
import fap.SistemaGestionEducativa.service.business.BloqueHorarioService;
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
@RequestMapping("/api/academico/bloques-horarios")
@Tag(name = "Académico - Bloques Horarios")
public class BloqueHorarioController {

    private final BloqueHorarioService service;

    @PostMapping
    @Operation(summary = "Registrar bloque", description = "Crea un bloque horario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bloque creado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Bloque duplicado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<BloqueHorarioResponse>> registrar(@Valid @RequestBody BloqueHorarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @PutMapping("/{idBloque}")
    @Operation(summary = "Actualizar bloque", description = "Modifica un bloque horario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bloque actualizado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Bloque no encontrado"),
            @ApiResponse(responseCode = "409", description = "Bloque duplicado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<BloqueHorarioResponse>> actualizar(@PathVariable Long idBloque,
                                                                          @Valid @RequestBody BloqueHorarioRequest request) {
        return ResponseEntity.ok(service.actualizar(idBloque, request));
    }

    @GetMapping
    @Operation(summary = "Listar bloques", description = "Obtiene todos los bloques activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<BloqueHorarioResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{idBloque}")
    @Operation(summary = "Eliminar bloque", description = "Elimina lógicamente un bloque horario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bloque eliminado"),
            @ApiResponse(responseCode = "404", description = "Bloque no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idBloque) {
        return ResponseEntity.ok(service.eliminar(idBloque));
    }
}
