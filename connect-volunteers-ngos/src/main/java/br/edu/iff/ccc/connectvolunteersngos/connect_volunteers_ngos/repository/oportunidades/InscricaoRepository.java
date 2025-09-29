package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.repository.oportunidades;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    // Encontra todas as inscrições de um voluntário específico
    List<Inscricao> findByVoluntarioIdUser(Long voluntarioId);

    // Encontra todas as inscrições para uma vaga específica
    List<Inscricao> findByVagaId(Long vagaId);

    // Encontra uma inscrição específica de um voluntário em uma vaga
    Optional<Inscricao> findByVoluntarioIdUserAndVagaId(Long voluntarioId, Long vagaId);
}