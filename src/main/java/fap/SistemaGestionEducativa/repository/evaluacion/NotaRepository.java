package fap.SistemaGestionEducativa.repository.evaluacion;

import fap.SistemaGestionEducativa.model.evaluacion.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotaRepository extends JpaRepository<Nota, Long> {

//    List<Nota> findByUsuarioEstudianteIdUsuario(Long idUsuario);
//
//    List<Nota> findByEvaluacionIdEvaluacion(Long idEvaluacion);
//
//    Optional<Nota> findByEvaluacionIdEvaluacionAndUsuarioEstudianteIdUsuario(Long idEvaluacion,Long idUsuario);

    boolean existsByEvaluacionIdEvaluacionAndEstudianteIdUsuarioAndEstado(Long idEvaluacion, Long idEstudiante, String estado);

    List<Nota> findAllByEstado(String estado);

    List<Nota> findAllByEstudianteIdUsuarioAndEstado(Long idEstudiante, String estado);

    List<Nota> findAllByEvaluacionIdEvaluacionAndEstado(Long idEvaluacion, String estado);

    List<Nota> findAllByEvaluacionCursoIdCursoAndEstudianteIdUsuarioAndEstado(Long idCurso, Long idUsuario, String estado);
}