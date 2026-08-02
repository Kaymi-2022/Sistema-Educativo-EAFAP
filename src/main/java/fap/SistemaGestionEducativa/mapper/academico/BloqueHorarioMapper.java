package fap.SistemaGestionEducativa.mapper.academico;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.academico.BloqueHorarioRequest;
import fap.SistemaGestionEducativa.dto.response.academico.BloqueHorarioResponse;
import fap.SistemaGestionEducativa.model.academico.BloqueHorario;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface BloqueHorarioMapper {

    BloqueHorario toEntity(BloqueHorarioRequest request);

    BloqueHorarioResponse toResponse(BloqueHorario entity);

}
