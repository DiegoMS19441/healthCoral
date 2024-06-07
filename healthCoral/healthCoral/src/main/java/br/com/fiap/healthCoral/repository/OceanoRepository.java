package br.com.fiap.healthCoral.repository;

import br.com.fiap.healthCoral.model.Oceano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OceanoRepository extends JpaRepository<Oceano, Long> {
}
