package fap.SistemaGestionEducativa.controller.academico;

import fap.SistemaGestionEducativa.dto.request.academico.CursoRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.CursoResponse;
import fap.SistemaGestionEducativa.service.business.CursoService;
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
@RequestMapping("/api/academico/cursos")
@Tag(name = "Académico - Cursos")
public class CursoController {

    private final CursoService service;

    @PostMapping
    @Operation(summary = "Registrar curso", description = "Crea un curso académico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso creado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Referencia no encontrada"),
            @ApiResponse(responseCode = "409", description = "Curso duplicado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<CursoResponse>> registrar(@Valid @RequestBody CursoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @PutMapping("/{idCurso}")
    @Operation(summary = "Actualizar curso", description = "Modifica un curso académico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso actualizado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
            @ApiResponse(responseCode = "409", description = "Curso duplicado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<CursoResponse>> actualizar(@PathVariable Long idCurso,
                                                                  @Valid @RequestBody CursoRequest request) {
        return ResponseEntity.ok(service.actualizar(idCurso, request));
    }

    @GetMapping("/{idCurso}")
    @Operation(summary = "Obtener curso", description = "Obtiene un curso por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<CursoResponse>> obtenerPorId(@PathVariable Long idCurso) {
        return ResponseEntity.ok(service.obtenerPorId(idCurso));
    }

    @GetMapping
    @Operation(summary = "Listar cursos", description = "Obtiene todos los cursos activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<CursoResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{idCurso}")
    @Operation(summary = "Eliminar curso", description = "Elimina lógicamente un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso eliminado"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idCurso) {
        return ResponseEntity.ok(service.eliminar(idCurso));
    }
}
