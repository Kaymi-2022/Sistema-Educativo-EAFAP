package fap.SistemaGestionEducativa.repository.academico;

import fap.SistemaGestionEducativa.model.academico.BloqueHorario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloqueHorarioRepository extends JpaRepository<BloqueHorario, Long> {

    List<BloqueHorario> findByEstado(String estado);

}