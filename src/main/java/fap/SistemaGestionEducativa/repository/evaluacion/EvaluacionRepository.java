package fap.SistemaGestionEducativa.repository.evaluacion;

import fap.SistemaGestionEducativa.model.evaluacion.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    boolean existsByCursoIdCursoAndNombreIgnoreCaseAndEstado(
            Long idCurso,
            String nombre,
            String estado
    );

    List<Evaluacion> findAllByEstado(
            String estado
    );

    List<Evaluacion> findAllByCursoIdCursoAndEstado(
            Long idCurso,
            String estado
    );

}
