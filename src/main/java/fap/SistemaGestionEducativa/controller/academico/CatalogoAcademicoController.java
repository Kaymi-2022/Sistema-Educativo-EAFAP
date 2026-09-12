package fap.SistemaGestionEducativa.controller.academico;

import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.UsuarioCatalogoResponse;
import fap.SistemaGestionEducativa.service.business.CatalogoAcademicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/academico/catalogos")
@Tag(name = "Académico - Catálogos")
public class CatalogoAcademicoController {

    private final CatalogoAcademicoService service;

    @GetMapping("/docentes")
    @Operation(summary = "Listar docentes activos", description = "Obtiene usuarios activos con rol DOCENTE")
    public ResponseEntity<RestResponse<List<UsuarioCatalogoResponse>>> listarDocentes() {
        return ResponseEntity.ok(service.listarDocentes());
    }

    @GetMapping("/discentes")
    @Operation(summary = "Listar discentes activos", description = "Obtiene usuarios activos con rol DISCENTE")
    public ResponseEntity<RestResponse<List<UsuarioCatalogoResponse>>> listarDiscentes() {
        return ResponseEntity.ok(service.listarDiscentes());
    }
}
