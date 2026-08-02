package fap.SistemaGestionEducativa.mapper.academico;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.academico.PeriodoAcademicoRequest;
import fap.SistemaGestionEducativa.dto.response.academico.PeriodoAcademicoResponse;
import fap.SistemaGestionEducativa.model.academico.PeriodoAcademico;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface PeriodoAcademicoMapper {

    PeriodoAcademico toEntity(PeriodoAcademicoRequest request);

    PeriodoAcademicoResponse toResponse(PeriodoAcademico entity);

    List<PeriodoAcademicoResponse> toResponseList(List<PeriodoAcademico> entities);

}