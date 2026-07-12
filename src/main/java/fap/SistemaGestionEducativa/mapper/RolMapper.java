package fap.SistemaGestionEducativa.mapper;

import fap.SistemaGestionEducativa.dto.request.RolRequest;
import fap.SistemaGestionEducativa.dto.response.RolResponse;
import fap.SistemaGestionEducativa.model.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper {

    Rol toEntity (RolRequest rolRequest);

    RolResponse toRequest (Rol rol);
}
