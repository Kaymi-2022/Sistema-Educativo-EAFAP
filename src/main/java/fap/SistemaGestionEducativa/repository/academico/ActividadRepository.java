package fap.SistemaGestionEducativa.repository.academico;


import fap.SistemaGestionEducativa.model.academico.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    List<Actividad> findByCursoIdCurso(Long idCurso);

    List<Actividad> findByTipo(String tipo);

    List<Actividad> findAllByEstado(String estado);

    List<Actividad> findAllByCursoIdCursoAndEstado(Long idCurso, String estado);

    boolean existsByNombreIgnoreCaseAndCursoIdCursoAndEstado(String nombre, Long idCurso, String estado);

}
