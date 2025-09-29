package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.repository;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OngRepository extends JpaRepository<Ong, Long> {
    // add consultas dps.
}