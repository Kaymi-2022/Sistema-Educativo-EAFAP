package fap.SistemaGestionEducativa.mapper.academico;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.academico.ActividadRequest;
import fap.SistemaGestionEducativa.dto.response.academico.ActividadResponse;
import fap.SistemaGestionEducativa.model.academico.Actividad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralMapperConfig.class)
public interface ActividadMapper {

    @Mapping(target = "idActividad", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Actividad toEntity(ActividadRequest request);

    @Mapping(source = "curso.idCurso", target = "idCurso")
    @Mapping(source = "curso.nombre", target = "curso")
    ActividadResponse toResponse(Actividad entity);

}
