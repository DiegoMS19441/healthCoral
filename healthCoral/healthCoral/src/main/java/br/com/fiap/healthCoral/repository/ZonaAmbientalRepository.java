package br.com.fiap.healthCoral.repository;

import br.com.fiap.healthCoral.model.ZonaAmbiental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaAmbientalRepository extends JpaRepository<ZonaAmbiental, Long> {
}
