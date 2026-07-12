package fap.SistemaGestionEducativa.mapper;

import fap.SistemaGestionEducativa.dto.request.UsuarioRequest;
import fap.SistemaGestionEducativa.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequest usuarioRequest);

    UsuarioRequest toRequest(Usuario usuario);
}
