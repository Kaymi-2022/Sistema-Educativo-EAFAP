package fap.SistemaGestionEducativa.mapper.academico;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.academico.SemanaAcademicaRequest;
import fap.SistemaGestionEducativa.dto.response.academico.SemanaAcademicaResponse;
import fap.SistemaGestionEducativa.model.academico.SemanaAcademica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralMapperConfig.class)
public interface SemanaAcademicaMapper {

    @Mapping(target = "idSemana", ignore = true)
    @Mapping(target = "estado",  ignore = true)
    SemanaAcademica toEntity(SemanaAcademicaRequest request);

    SemanaAcademicaResponse toResponse(SemanaAcademica entity);

}
