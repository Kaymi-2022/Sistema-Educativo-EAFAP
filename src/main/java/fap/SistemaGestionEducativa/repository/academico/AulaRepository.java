package fap.SistemaGestionEducativa.repository.academico;

import fap.SistemaGestionEducativa.model.academico.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Long> {

    List<Aula> findAllByEstado(String estado);

    boolean existsByNombreIgnoreCaseAndEstado(String nombre, String estado);

}
