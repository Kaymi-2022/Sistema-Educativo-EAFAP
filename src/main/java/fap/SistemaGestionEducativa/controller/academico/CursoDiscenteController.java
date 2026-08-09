package fap.SistemaGestionEducativa.controller.academico;

import fap.SistemaGestionEducativa.dto.request.academico.MatriculaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.CursoDiscenteResponse;
import fap.SistemaGestionEducativa.service.business.CursoDiscenteService;
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
@RequestMapping("/api/academico/matriculas")
@Tag(name = "Académico - Matrículas")
public class CursoDiscenteController {

    private final CursoDiscenteService service;

    @PostMapping
    @Operation(summary = "Registrar matrícula", description = "Matricula un estudiante en un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Matrícula creada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Referencia no encontrada"),
            @ApiResponse(responseCode = "409", description = "Matrícula duplicada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<CursoDiscenteResponse>> registrar(@Valid @RequestBody MatriculaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @GetMapping("/{idCursoDiscente}")
    @Operation(summary = "Obtener matrícula", description = "Obtiene una matrícula por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula encontrada"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<CursoDiscenteResponse>> obtenerPorId(@PathVariable Long idCursoDiscente) {
        return ResponseEntity.ok(service.obtenerPorId(idCursoDiscente));
    }

    @GetMapping
    @Operation(summary = "Listar matrículas", description = "Obtiene todas las matrículas activas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<List<CursoDiscenteResponse>>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{idCursoDiscente}")
    @Operation(summary = "Eliminar matrícula", description = "Elimina lógicamente una matrícula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula eliminada"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<RestResponse<Void>> eliminar(@PathVariable Long idCursoDiscente) {
        return ResponseEntity.ok(service.eliminar(idCursoDiscente));
    }
}
