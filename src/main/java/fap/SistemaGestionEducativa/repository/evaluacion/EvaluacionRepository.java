package fap.SistemaGestionEducativa.repository.evaluacion;

import fap.SistemaGestionEducativa.model.evaluacion.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    List<Evaluacion> findByCursoIdCurso(Long idCurso);

    List<Evaluacion> findByTipo(String tipo);

}
