package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.repository.oportunidades;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    // consultas...
}