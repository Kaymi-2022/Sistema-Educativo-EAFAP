package fap.SistemaGestionEducativa.controller.academico;

import fap.SistemaGestionEducativa.dto.request.academico.CategoriaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.CategoriaResponse;
import fap.SistemaGestionEducativa.service.business.CategoriaService;
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
@RequestMapping("/api/academico/categorias")
@Tag(name = "Académico - Categorías")
public class CategoriaController {

    private final CategoriaService service;

    @PostMapping
    @Operation(summary = "Registrar categoría", description = "Crea una categoría académica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Categoría duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<CategoriaResponse>> registrar(@Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @PutMapping("/{idCategoria}")
    @Operation(summary = "Actualizar categoría", description = "Modifica una categoría académica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría actualizada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
            @ApiResponse(responseCode = "409", description = "Categoría duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<CategoriaResponse>> actualizar(@PathVariable Long idCategoria,
                                                                      @Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(service.actualizar(idCategoria, request));
    }

    @GetMapping("/{idCategoria}")
    @Operation(summary = "Obtener categoría", description = "Obtiene una categoría por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<CategoriaResponse>> obtenerPorId(@PathVariable Long idCategoria) {
        return ResponseEntity.ok(service.obtenerPorId(idCategoria));
    }

    @GetMapping
    @Operation(summary = "Listar categorías", description = "Obtiene todas las categorías activas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<CategoriaResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{idCategoria}")
    @Operation(summary = "Eliminar categoría", description = "Elimina lógicamente una categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría eliminada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idCategoria) {
        return ResponseEntity.ok(service.eliminar(idCategoria));
    }
}
