package fap.SistemaGestionEducativa.mapper.academico;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.academico.PeriodoAcademicoRequest;
import fap.SistemaGestionEducativa.dto.response.academico.PeriodoAcademicoResponse;
import fap.SistemaGestionEducativa.model.academico.PeriodoAcademico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface PeriodoAcademicoMapper {

    @Mapping(target = "idPeriodo", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    PeriodoAcademico toEntity(PeriodoAcademicoRequest request);

    @Mapping(source = "idPeriodo", target = "idPeriodoAcademico")
    PeriodoAcademicoResponse toResponse(PeriodoAcademico entity);

    List<PeriodoAcademicoResponse> toResponseList(List<PeriodoAcademico> entities);

}