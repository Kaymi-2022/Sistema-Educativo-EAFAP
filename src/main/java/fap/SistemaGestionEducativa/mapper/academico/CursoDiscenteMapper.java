package fap.SistemaGestionEducativa.mapper.academico;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.academico.MatriculaRequest;
import fap.SistemaGestionEducativa.dto.response.academico.CursoDiscenteResponse;
import fap.SistemaGestionEducativa.model.academico.CursoDiscente;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface CursoDiscenteMapper {

    // ==========================================
    // 1. DTO Request -> Entidad JPA
    // ==========================================
    @Mapping(target = "idCursoDiscente", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "estudiante", ignore = true)
    @Mapping(target = "fechaMatricula", ignore = true)
    @Mapping(target = "estado", ignore = true)
    CursoDiscente toEntity(MatriculaRequest request);

    // ==========================================
    // 2. Entidad JPA -> DTO Response
    // ==========================================
    @Mapping(source = "curso.idCurso", target = "idCurso")
    @Mapping(source = "curso.nombre", target = "curso")
    @Mapping(source = "estudiante.idUsuario", target = "idDiscente")
    @Mapping(source = "estudiante", target = "discente") // Se conecta automáticamente con el método default
    CursoDiscenteResponse toResponse(CursoDiscente entity);

    // ==========================================
    // 3. Mapeo de Lista
    // ==========================================
    List<CursoDiscenteResponse> toResponseList(List<CursoDiscente> entities);

    // ==========================================
    // 4. Método auxiliar seguro contra NullPointerException
    // ==========================================
    default String mapDiscenteNombreCompleto(Usuario estudiante) {
        if (estudiante == null) {
            return null;
        }
        String nombres = estudiante.getNombres() != null ? estudiante.getNombres() : "";
        String apellidos = estudiante.getApellidos() != null ? estudiante.getApellidos() : "";
        return (nombres + " " + apellidos).trim();
    }

}
