package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Administrador;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception.users.AdministradorNaoEncontradoException;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.repository.users.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public Administrador saveAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public Administrador findAdministradorById(Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new AdministradorNaoEncontradoException("Administrador com id " + id + " não encontrado."));
    }

    public List<Administrador> findAllAdministradores() {
        return administradorRepository.findAll();
    }

    public void deleteAdministrador(Long id) {
        Administrador admin = findAdministradorById(id);
        administradorRepository.delete(admin);
    }
}