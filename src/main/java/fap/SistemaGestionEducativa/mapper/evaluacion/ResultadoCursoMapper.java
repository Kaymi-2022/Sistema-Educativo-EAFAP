package fap.SistemaGestionEducativa.mapper.evaluacion;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.evaluacion.ResultadoCursoRequest;
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

    @Mapping(target = "idResultado", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "discente", ignore = true)
    @Mapping(target = "promedioFinal", ignore = true)
    @Mapping(target = "estadoAprobacion", ignore = true)
    @Mapping(target = "fechaCierre", ignore = true)
    @Mapping(target = "estado", ignore = true)
    ResultadoCurso toEntity(ResultadoCursoRequest request);


}
