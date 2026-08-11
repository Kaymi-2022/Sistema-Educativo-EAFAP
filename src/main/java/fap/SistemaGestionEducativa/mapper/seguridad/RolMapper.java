package fap.SistemaGestionEducativa.mapper.seguridad;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.seguridad.RolRequest;
import fap.SistemaGestionEducativa.dto.response.seguridad.RolResponse;
import fap.SistemaGestionEducativa.model.seguridad.Rol;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface RolMapper {

    @Mapping(target = "idRol", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioRoles", ignore = true)
    Rol toEntity(RolRequest request);

    RolResponse toResponse(Rol entity);

    List<RolResponse> toResponseList(List<Rol> entities);

    @Mapping(target = "idRol", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioRoles", ignore = true)
    void updateEntity(RolRequest request,@MappingTarget Rol rol);
}
