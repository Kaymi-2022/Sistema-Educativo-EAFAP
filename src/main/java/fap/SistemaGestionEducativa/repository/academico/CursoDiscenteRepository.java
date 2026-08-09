package fap.SistemaGestionEducativa.repository.academico;

import fap.SistemaGestionEducativa.model.academico.Curso;
import fap.SistemaGestionEducativa.model.academico.CursoDiscente;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoDiscenteRepository extends JpaRepository<CursoDiscente, Long> {
    boolean existsByCursoAndEstudianteAndEstado(Curso curso, Usuario estudiante, String estado);

    List<CursoDiscente> findAllByEstado(String estado);

    boolean existsByCursoIdCursoAndEstudianteIdUsuarioAndEstado(Long idCurso, Long idEstudiante, String estado);

    List<CursoDiscente> findAllByEstudianteAndEstado(Usuario estudiante, String estado);

    List<CursoDiscente> findAllByEstudianteIdUsuarioAndEstado(Long idEstudiante, String estado);

    List<CursoDiscente> findAllByCursoAndEstado(Curso curso, String estado);
}
