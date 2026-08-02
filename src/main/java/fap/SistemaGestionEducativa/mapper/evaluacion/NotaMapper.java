package fap.SistemaGestionEducativa.mapper.evaluacion;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.evaluacion.NotaRequest;
import fap.SistemaGestionEducativa.dto.response.evaluacion.NotaResponse;
import fap.SistemaGestionEducativa.model.evaluacion.Nota;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralMapperConfig.class)
public interface NotaMapper {

    @Mapping(target = "idNota", ignore = true)
    @Mapping(target = "evaluacion", ignore = true)
    @Mapping(target = "estudiante", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Nota toEntity(NotaRequest request);

    @Mapping(source = "evaluacion.idEvaluacion", target = "idEvaluacion")
    @Mapping(source = "evaluacion.nombre", target = "evaluacion")

    @Mapping(source = "estudiante.idUsuario", target = "idDiscente")

    @Mapping(
            target = "discente",
            expression = "java(entity.getEstudiante().getNombres()+\" \"+entity.getEstudiante().getApellidos())"
    )
    NotaResponse toResponse(Nota entity);

}
