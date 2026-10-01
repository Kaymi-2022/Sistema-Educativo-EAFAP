package fap.SistemaGestionEducativa.certificacion.service;

import fap.SistemaGestionEducativa.certificacion.dto.*;
import fap.SistemaGestionEducativa.dto.response.RestResponse;

public interface CertificacionService {
    RestResponse<AcademicRecordResponse> obtenerRecord(Long idDiscente, Long idCurso);
    RestResponse<AcademicMeritResponse> obtenerMerito(Long idCurso);
    RestResponse<AcademicCertificateResponse> obtenerCertificado(Long idDiscente, Long idCurso);
}
