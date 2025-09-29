package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Ong;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception.OngNaoEncontradaException;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.repository.OngRepository;

@Service
public class OngService {

    @Autowired
    private OngRepository ongRepository;

    public List<Ong> findAllOngs() {
        return ongRepository.findAll();
    }

    public Ong saveOng(Ong ong) {
        return ongRepository.save(ong);
    }

    public Ong findById(Long id) {
        return ongRepository.findById(id)
                .orElseThrow(() -> new OngNaoEncontradaException("ONG com id " + id + " não encontrada."));
    }
    
    public void deleteOng(Long id) {
        Ong ong = findById(id);
        ongRepository.delete(ong);
    }
}