package fap.SistemaGestionEducativa.mapper.academico;

import fap.SistemaGestionEducativa.Config.CentralMapperConfig;
import fap.SistemaGestionEducativa.dto.request.academico.HorarioRequest;
import fap.SistemaGestionEducativa.dto.response.academico.HorarioResponse;
import fap.SistemaGestionEducativa.model.academico.BloqueHorario;
import fap.SistemaGestionEducativa.model.academico.Horario;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface HorarioMapper {

    // ==========================================
    // 1. DTO Request -> Entidad JPA
    // ==========================================
    @Mapping(target = "idHorario", ignore = true)
    @Mapping(target = "semanaAcademica", ignore = true)
    @Mapping(target = "aula", ignore = true)
    @Mapping(target = "bloqueHorario", ignore = true)
    @Mapping(target = "actividad", ignore = true)
    @Mapping(target = "docente", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Horario toEntity(HorarioRequest request);

    // ==========================================
    // 2. Entidad JPA -> DTO Response
    // ==========================================
    @Mapping(source = "semanaAcademica.idSemana", target = "idSemana")
    @Mapping(source = "semanaAcademica.numeroSemana", target = "numeroSemana")
    @Mapping(source = "aula.idAula", target = "idAula")
    @Mapping(source = "aula.nombre", target = "aula")
    @Mapping(source = "bloqueHorario.idBloque", target = "idBloque")
    @Mapping(source = "bloqueHorario", target = "bloqueHorario") // Mapea mediante mapRangoHorario
    @Mapping(source = "actividad.idActividad", target = "idActividad")
    @Mapping(source = "actividad.nombre", target = "actividad")
    @Mapping(source = "docente.idUsuario", target = "idInstructor")
    @Mapping(source = "docente", target = "instructor") // Mapea mediante mapInstructorNombreCompleto
    HorarioResponse toResponse(Horario entity);

    // ==========================================
    // 3. Mapeo de Colección
    // ==========================================
    List<HorarioResponse> toResponseList(List<Horario> entities);

    // ==========================================
    // 4. Métodos Auxiliares Seguros (Null-Safe)
    // ==========================================

    // Concatena las horas: "08:00 - 10:00"
    default String mapRangoHorario(BloqueHorario bloqueHorario) {
        if (bloqueHorario == null) {
            return null;
        }
        String inicio = bloqueHorario.getHoraInicio() != null ? bloqueHorario.getHoraInicio() : "";
        String fin = bloqueHorario.getHoraFin() != null ? bloqueHorario.getHoraFin() : "";

        if (inicio.isEmpty() && fin.isEmpty()) {
            return null;
        }
        return inicio + " - " + fin;
    }

    // Concatena Nombre y Apellido: "Juan Pérez"
    default String mapInstructorNombreCompleto(Usuario docente) {
        if (docente == null) {
            return null;
        }
        String nombres = docente.getNombres() != null ? docente.getNombres() : "";
        String apellidos = docente.getApellidos() != null ? docente.getApellidos() : "";
        return (nombres + " " + apellidos).trim();
    }

}
