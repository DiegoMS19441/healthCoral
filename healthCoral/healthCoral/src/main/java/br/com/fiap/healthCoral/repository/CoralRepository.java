package br.com.fiap.healthCoral.repository;

import br.com.fiap.healthCoral.model.Coral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoralRepository extends JpaRepository<Coral, Long> {

}
