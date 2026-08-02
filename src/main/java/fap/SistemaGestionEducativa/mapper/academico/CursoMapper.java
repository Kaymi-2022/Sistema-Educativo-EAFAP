package fap.SistemaGestionEducativa.mapper.academico;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.academico.CursoRequest;
import fap.SistemaGestionEducativa.dto.response.academico.CursoResponse;
import fap.SistemaGestionEducativa.model.academico.Curso;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface CursoMapper {

    // ==========================================
    // 1. DTO Request -> Entidad JPA
    // ==========================================
    @Mapping(target = "idCurso", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "docente", ignore = true)
    @Mapping(target = "periodoAcademico", ignore = true)
    @Mapping(target = "actividades", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Curso toEntity(CursoRequest request);

    // ==========================================
    // 2. Entidad JPA -> DTO Response
    // ==========================================
    @Mapping(source = "categoria.idCategoria", target = "idCategoria")
    @Mapping(source = "categoria.nombre", target = "categoria")
    @Mapping(source = "docente.idUsuario", target = "idDocente")
    @Mapping(source = "docente", target = "docente") // Usa el método mapDocenteNombreCompleto
    @Mapping(source = "periodoAcademico.idPeriodo", target = "idPeriodoAcademico")
    @Mapping(source = "periodoAcademico.nombre", target = "periodo")
    CursoResponse toResponse(Curso entity);

    // ==========================================
    // 3. Mapeo de Lista
    // ==========================================
    List<CursoResponse> toResponseList(List<Curso> entities);

    // ==========================================
    // 4. Método auxiliar seguro para concatenar
    // ==========================================
    default String mapDocenteNombreCompleto(Usuario docente) {
        if (docente == null) {
            return null;
        }
        String nombres = docente.getNombres() != null ? docente.getNombres() : "";
        String apellidos = docente.getApellidos() != null ? docente.getApellidos() : "";
        return (nombres + " " + apellidos).trim();
    }

}
