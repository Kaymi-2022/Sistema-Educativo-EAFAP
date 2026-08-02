package fap.SistemaGestionEducativa.mapper.academico;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.academico.SemanaAcademicaRequest;
import fap.SistemaGestionEducativa.dto.response.academico.SemanaAcademicaResponse;
import fap.SistemaGestionEducativa.model.academico.SemanaAcademica;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface SemanaAcademicaMapper {

    SemanaAcademica toEntity(SemanaAcademicaRequest request);

    SemanaAcademicaResponse toResponse(SemanaAcademica entity);

}
