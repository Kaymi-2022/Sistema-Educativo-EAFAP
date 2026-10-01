package fap.SistemaGestionEducativa.certificacion.controller;

import fap.SistemaGestionEducativa.certificacion.dto.*;
import fap.SistemaGestionEducativa.certificacion.service.CertificacionService;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/certificacion")
public class CertificacionController {
    private final CertificacionService service;

    @GetMapping("/estudiantes/{idDiscente}/cursos/{idCurso}/record")
    public ResponseEntity<RestResponse<AcademicRecordResponse>> obtenerRecord(@PathVariable Long idDiscente, @PathVariable Long idCurso) {
        return ResponseEntity.ok(service.obtenerRecord(idDiscente, idCurso));
    }
    @GetMapping("/cursos/{idCurso}/merito")
    public ResponseEntity<RestResponse<AcademicMeritResponse>> obtenerMerito(@PathVariable Long idCurso) {
        return ResponseEntity.ok(service.obtenerMerito(idCurso));
    }
    @GetMapping("/cursos/{idCurso}/primer-puesto")
    public ResponseEntity<RestResponse<AcademicMeritResponse>> obtenerPrimerPuesto(@PathVariable Long idCurso) {
        return ResponseEntity.ok(service.obtenerMerito(idCurso));
    }
    @GetMapping("/estudiantes/{idDiscente}/cursos/{idCurso}/certificado")
    public ResponseEntity<RestResponse<AcademicCertificateResponse>> obtenerCertificado(@PathVariable Long idDiscente, @PathVariable Long idCurso) {
        return ResponseEntity.ok(service.obtenerCertificado(idDiscente, idCurso));
    }
}
