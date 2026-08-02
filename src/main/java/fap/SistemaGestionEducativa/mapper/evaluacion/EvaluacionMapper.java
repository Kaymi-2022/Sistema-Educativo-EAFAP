package fap.SistemaGestionEducativa.mapper.evaluacion;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.evaluacion.EvaluacionRequest;
import fap.SistemaGestionEducativa.dto.response.evaluacion.EvaluacionResponse;
import fap.SistemaGestionEducativa.model.evaluacion.Evaluacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralMapperConfig.class)
public interface EvaluacionMapper {

    @Mapping(target = "idEvaluacion", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Evaluacion toEntity(EvaluacionRequest request);

    @Mapping(source = "curso.idCurso", target = "idCurso")
    @Mapping(source = "curso.nombre", target = "curso")
    EvaluacionResponse toResponse(Evaluacion entity);

}
