package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.oportunidade;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Vaga;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception.oportunidades.VagaNaoEncontradaException;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.repository.oportunidades.VagaRepository;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    public Vaga saveVaga(Vaga vaga) {
        return vagaRepository.save(vaga);
    }

    public Vaga findVagaById(Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new VagaNaoEncontradaException("Vaga com id " + id + " não encontrada."));
    }

    public List<Vaga> findAllVagas() {
        return vagaRepository.findAll();
    }

    public void deleteVaga(Long id) {
        Vaga vaga = findVagaById(id);
        vagaRepository.delete(vaga);
    }
}