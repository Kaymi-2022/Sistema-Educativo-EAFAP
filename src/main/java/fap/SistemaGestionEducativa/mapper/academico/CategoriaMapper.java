package fap.SistemaGestionEducativa.mapper.academico;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.academico.CategoriaRequest;
import fap.SistemaGestionEducativa.dto.response.academico.CategoriaResponse;
import fap.SistemaGestionEducativa.model.academico.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface CategoriaMapper {

    @Mapping(target = "idCategoria", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Categoria toEntity(CategoriaRequest request);

    CategoriaResponse toResponse(Categoria entity);

    List<CategoriaResponse> toResponseList(List<Categoria> entities);

    @Mapping(target = "idCategoria", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    void updateEntity(CategoriaRequest request, @MappingTarget Categoria entity);

}