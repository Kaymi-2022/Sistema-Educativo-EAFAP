package fap.SistemaGestionEducativa.mapper.seguridad;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.seguridad.UsuarioRequest;
import fap.SistemaGestionEducativa.dto.response.seguridad.UsuarioResponse;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface UsuarioMapper {

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioRoles", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    @Mapping(target = "cursosDiscente", ignore = true)
    Usuario toEntity(UsuarioRequest request);

    UsuarioResponse toResponse(Usuario entity);

    List<UsuarioResponse> toResponseList(List<Usuario> entities);

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "usuarioRoles", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    @Mapping(target = "cursosDiscente", ignore = true)
    void updateEntity(UsuarioRequest request, @MappingTarget Usuario entity);
}
