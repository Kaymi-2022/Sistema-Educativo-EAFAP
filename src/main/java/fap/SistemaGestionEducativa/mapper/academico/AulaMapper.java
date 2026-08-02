package fap.SistemaGestionEducativa.mapper.academico;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.academico.AulaRequest;
import fap.SistemaGestionEducativa.dto.response.academico.AulaResponse;
import fap.SistemaGestionEducativa.model.academico.Aula;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface AulaMapper {

    Aula toEntity(AulaRequest request);

    AulaResponse toResponse(Aula entity);

    List<AulaResponse> toResponseList(List<Aula> entities);

}
