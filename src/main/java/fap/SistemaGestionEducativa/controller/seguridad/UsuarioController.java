package fap.SistemaGestionEducativa.controller.seguridad;

import fap.SistemaGestionEducativa.dto.request.seguridad.UsuarioRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.UsuarioResponse;
import fap.SistemaGestionEducativa.service.business.UsuarioService;
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
@RequestMapping("/api/seguridad/usuarios")
@Tag(name = "Seguridad - Usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    @Operation(summary = "Registrar usuario", description = "Crea un usuario nuevo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Usuario duplicado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<UsuarioResponse>> registrar(@Valid @RequestBody UsuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @PutMapping("/{idUsuario}")
    @Operation(summary = "Actualizar usuario", description = "Modifica un usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "409", description = "Usuario duplicado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<UsuarioResponse>> actualizar(@PathVariable Long idUsuario,
                                                                    @Valid @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(service.actualizar(idUsuario, request));
    }

    @GetMapping("/{idUsuario}")
    @Operation(summary = "Obtener usuario", description = "Obtiene un usuario por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<UsuarioResponse>> obtenerPorId(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(service.obtenerPorId(idUsuario));
    }

    @GetMapping
    @Operation(summary = "Listar usuarios", description = "Obtiene todos los usuarios activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<UsuarioResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{idUsuario}")
    @Operation(summary = "Eliminar usuario", description = "Elimina lógicamente un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(service.eliminar(idUsuario));
    }
}
