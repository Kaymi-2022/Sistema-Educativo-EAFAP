package fap.SistemaGestionEducativa.controller.seguridad;

import fap.SistemaGestionEducativa.dto.request.seguridad.RolRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.RolResponse;
import fap.SistemaGestionEducativa.service.business.RolService;
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
@RequestMapping("/api/seguridad/roles")
@Tag(name = "Seguridad - Roles")
public class RolController {

    private final RolService service;

    @PostMapping
    @Operation(summary = "Registrar rol", description = "Crea un nuevo rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rol creado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Rol duplicado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<RolResponse>> registrar(@Valid @RequestBody RolRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @PutMapping("/{idRol}")
    @Operation(summary = "Actualizar rol", description = "Modifica un rol existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol actualizado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado"),
            @ApiResponse(responseCode = "409", description = "Rol duplicado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<RolResponse>> actualizar(@PathVariable Long idRol,
                                                                @Valid @RequestBody RolRequest request) {
        return ResponseEntity.ok(service.actualizar(idRol, request));
    }

    @GetMapping("/{idRol}")
    @Operation(summary = "Obtener rol", description = "Obtiene un rol por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol encontrado"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<RolResponse>> obtenerPorId(@PathVariable Long idRol) {
        return ResponseEntity.ok(service.obtenerPorId(idRol));
    }

    @GetMapping
    @Operation(summary = "Listar roles", description = "Obtiene todos los roles activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<RolResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{idRol}")
    @Operation(summary = "Eliminar rol", description = "Elimina lógicamente un rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol eliminado"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idRol) {
        return ResponseEntity.ok(service.eliminar(idRol));
    }
}
