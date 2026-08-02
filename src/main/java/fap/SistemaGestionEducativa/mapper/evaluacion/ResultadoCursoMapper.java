package fap.SistemaGestionEducativa.mapper.evaluacion;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.response.evaluacion.ResultadoCursoResponse;
import fap.SistemaGestionEducativa.model.evaluacion.ResultadoCurso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralMapperConfig.class)
public interface ResultadoCursoMapper {

    @Mapping(source = "curso.idCurso", target = "idCurso")
    @Mapping(source = "curso.nombre", target = "curso")

    @Mapping(source = "estudiante.idUsuario", target = "idDiscente")

    @Mapping(
            target = "discente",
            expression = "java(entity.getEstudiante().getNombres()+\" \"+entity.getEstudiante().getApellidos())"
    )
    ResultadoCursoResponse toResponse(ResultadoCurso entity);

}
