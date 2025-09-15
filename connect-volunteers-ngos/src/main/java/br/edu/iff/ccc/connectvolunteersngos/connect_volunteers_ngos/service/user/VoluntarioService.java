package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.repository.VoluntarioRepository;

// Alterado - agora utilizando o JPA Repository
@Service
public class VoluntarioService {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    public Voluntario saveVoluntario(Voluntario voluntario) {
        return voluntarioRepository.save(voluntario);
    }

    public Voluntario findVoluntarioById(Long id) {
        return voluntarioRepository.findById(id).orElse(null);
    }

    public List<Voluntario> findAllVoluntarios() {
        return voluntarioRepository.findAll();
    }

    public Voluntario findByNome(String nome) {
        return voluntarioRepository.findByNome(nome);
    }

    public Voluntario findByEmail(String email) {
        return voluntarioRepository.findByEmail(email);
    }
}