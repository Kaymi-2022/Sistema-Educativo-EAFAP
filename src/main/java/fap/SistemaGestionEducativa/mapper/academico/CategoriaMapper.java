package fap.SistemaGestionEducativa.mapper.academico;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.academico.CategoriaRequest;
import fap.SistemaGestionEducativa.dto.response.academico.CategoriaResponse;
import fap.SistemaGestionEducativa.model.academico.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface CategoriaMapper {

    Categoria toEntity(CategoriaRequest request);

    CategoriaResponse toResponse(Categoria entity);

    List<CategoriaResponse> toResponseList(List<Categoria> entities);

}