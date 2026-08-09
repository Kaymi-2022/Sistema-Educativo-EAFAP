package fap.SistemaGestionEducativa.controller.seguridad;

import fap.SistemaGestionEducativa.dto.request.seguridad.UsuarioRolRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.UsuarioRolResponse;
import fap.SistemaGestionEducativa.service.business.UsuarioRolService;
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
@RequestMapping("/api/seguridad/usuarios-roles")
@Tag(name = "Seguridad - Usuarios Roles")
public class UsuarioRolController {

    private final UsuarioRolService service;

    @PostMapping
    @Operation(summary = "Asignar rol", description = "Asigna un rol a un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rol asignado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Asignación duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<UsuarioRolResponse>> asignarRol(@Valid @RequestBody UsuarioRolRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.asignarRol(request));
    }

    @GetMapping("/{idUsuario}")
    @Operation(summary = "Listar roles del usuario", description = "Obtiene los roles asignados a un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<UsuarioRolResponse>>> listarRolesUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(service.listarRolesUsuario(idUsuario));
    }

    @DeleteMapping("/{idUsuario}/roles/{idRol}")
    @Operation(summary = "Eliminar rol asignado", description = "Elimina una asignación usuario-rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asignación eliminada"),
            @ApiResponse(responseCode = "404", description = "Asignación no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminarRol(@PathVariable Long idUsuario,
                                                          @PathVariable Long idRol) {
        return ResponseEntity.ok(service.eliminarRol(idUsuario, idRol));
    }
}
