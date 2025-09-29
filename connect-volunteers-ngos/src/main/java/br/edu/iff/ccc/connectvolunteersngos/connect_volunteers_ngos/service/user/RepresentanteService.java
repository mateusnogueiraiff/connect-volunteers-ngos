package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Representante;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception.users.RepresentanteNaoEncontradoException;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.repository.users.RepresentanteRepository;

@Service
public class RepresentanteService {
    
    @Autowired
    private RepresentanteRepository representanteRepository;

    public Representante saveRepresentante(Representante representante) {
        return representanteRepository.save(representante);
    }

    public Representante findRepresentanteById(Long id) {
        return representanteRepository.findById(id)
                .orElseThrow(() -> new RepresentanteNaoEncontradoException("Representante com id " + id + " não encontrado."));
    }

    public List<Representante> findAllRepresentantes() {
        return representanteRepository.findAll();
    }

    public void deleteRepresentante(Long id) {
        representanteRepository.deleteById(id);
    }

    /* Query - Buscas Personalizadas */
    // Busca por nome aproximado
    public List<Representante> searchVoluntariosByNome(String nomePart) {
        return representanteRepository.searchByNomeLike(nomePart);
    }

    // Busca por email exato
    public Representante findVoluntarioByEmail(String email) {
        return representanteRepository.findByEmailExact(email);
    }
}