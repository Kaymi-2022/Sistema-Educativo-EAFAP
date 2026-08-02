package fap.SistemaGestionEducativa.mapper.seguridad;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.seguridad.RolRequest;
import fap.SistemaGestionEducativa.dto.response.seguridad.RolResponse;
import fap.SistemaGestionEducativa.model.seguridad.Rol;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface RolMapper {

    Rol toEntity(RolRequest request);

    RolResponse toResponse(Rol entity);

    List<RolResponse> toResponseList(List<Rol> entities);


}
