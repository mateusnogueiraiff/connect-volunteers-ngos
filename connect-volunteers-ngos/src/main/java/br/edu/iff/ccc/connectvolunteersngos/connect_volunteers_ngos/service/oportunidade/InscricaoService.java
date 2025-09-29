package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.oportunidade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Inscricao;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Vaga;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception.oportunidades.InscricaoNaoEncontradaException;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.repository.oportunidades.InscricaoRepository;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    public Inscricao createInscricao(Voluntario voluntario, Vaga vaga) {
        // verifica se o voluntário já está inscrito na vaga
        Optional<Inscricao> existente = inscricaoRepository.findByVoluntarioIdUserAndVagaId(voluntario.getIdUser(), vaga.getId());
        if (existente.isPresent()) {
            // aq poderia lançar uma exceção de "Já Inscrito".
            return existente.get(); // no momento apenas retornando a inscrição existente.
        }

        Inscricao novaInscricao = new Inscricao();
        novaInscricao.setVoluntario(voluntario);
        novaInscricao.setVaga(vaga);
        novaInscricao.setStatus("INSCRITO");
        novaInscricao.setDataInscricao(LocalDateTime.now());

        return inscricaoRepository.save(novaInscricao);
    }

    public Inscricao findById(Long id) {
        return inscricaoRepository.findById(id)
                .orElseThrow(() -> new InscricaoNaoEncontradaException("Inscrição com id " + id + " não encontrada."));
    }

    public List<Inscricao> findAllByVoluntario(Long voluntarioId) {
        return inscricaoRepository.findByVoluntarioIdUser(voluntarioId);
    }

    public List<Inscricao> findAllByVaga(Long vagaId) {
        return inscricaoRepository.findByVagaId(vagaId);
    }

    public void cancelInscricao(Long inscricaoId) {
        Inscricao inscricao = findById(inscricaoId);
        // poderíamos mudar o status para "CANCELADO"
        // inscricao.setStatus("CANCELADO");
        // inscricaoRepository.save(inscricao);
        inscricaoRepository.delete(inscricao); // optamos por deletar o registro apenas
    }
}