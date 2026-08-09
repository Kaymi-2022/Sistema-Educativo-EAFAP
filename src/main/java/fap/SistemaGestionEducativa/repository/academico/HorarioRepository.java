package fap.SistemaGestionEducativa.repository.academico;

import fap.SistemaGestionEducativa.model.academico.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findAllByEstado(String estado);

    List<Horario> findAllByDocenteIdUsuarioAndEstado(Long idUsuario, String estado);

    List<Horario> findAllByActividadCursoIdCursoAndEstado(Long idCurso, String estado);

    List<Horario> findAllBySemanaAcademicaIdSemanaAndEstado(Long idSemana, String estado);

    boolean existsByFechaAndBloqueHorarioIdBloqueAndAulaIdAulaAndEstado(
            java.time.LocalDate fecha,
            Long idBloque,
            Long idAula,
            String estado);

    boolean existsByFechaAndBloqueHorarioIdBloqueAndDocenteIdUsuarioAndEstado(
            java.time.LocalDate fecha,
            Long idBloque,
            Long idDocente,
            String estado);

}
