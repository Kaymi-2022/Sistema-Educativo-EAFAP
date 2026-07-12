package fap.SistemaGestionEducativa.mapper;

import fap.SistemaGestionEducativa.dto.response.RolResponse;
import fap.SistemaGestionEducativa.model.Rol;
import fap.SistemaGestionEducativa.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface RolUsuarioMapper {

    @Mappings({
        @Mapping(source = "usuario.idUsuario", target = "idUsuario"),
        @Mapping(source = "usuario.dni", target = "dni"),
        @Mapping(source = "usuario.nombres", target = "nombres"),
        @Mapping(source = "usuario.apellidos", target = "apellidos"),
        @Mapping(source = "usuario.email", target = "email"),
        @Mapping(source = "usuario.username", target = "username"),
        @Mapping(source = "usuario.estado", target = "estadoUsuario"),
        @Mapping(source = "rol.idRol", target = "idRol"),
        @Mapping(source = "rol.nombre", target = "nombreRol"),
        @Mapping(source = "rol.estado", target = "estadoRol")})
    RolResponse toRolUsuarioResponse(Usuario usuario, Rol rol);

}
