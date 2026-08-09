package fap.SistemaGestionEducativa.mapper.seguridad;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.seguridad.UsuarioRolRequest;
import fap.SistemaGestionEducativa.dto.response.seguridad.UsuarioRolResponse;
import fap.SistemaGestionEducativa.model.seguridad.UsuarioRol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface UsuarioRolMapper {

    @Mapping(target = "idUsuarioRol", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "rol", ignore = true)
    UsuarioRol toEntity(UsuarioRolRequest request);

    @Mapping(source = "usuario.idUsuario", target = "idUsuario")
    @Mapping(source = "usuario.username", target = "usuario")

    @Mapping(source = "rol.idRol", target = "idRol")
    @Mapping(source = "rol.nombreRol", target = "rol")
    UsuarioRolResponse toResponse(UsuarioRol entity);

    List<UsuarioRolResponse> toResponseList(List<UsuarioRol> entities);

}
