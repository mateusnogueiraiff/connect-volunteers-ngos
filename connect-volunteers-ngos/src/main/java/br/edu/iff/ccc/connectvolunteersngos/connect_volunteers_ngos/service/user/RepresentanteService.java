package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Ong;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Representante;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.OngService;

@Service
public class RepresentanteService {
    
    private ArrayList<Representante> representantes; // atributo global
    private Long nextId = 2L; // já existem 3 na lista inicial - SOMENTE PARA SIMULAÇÃO

    public RepresentanteService() {
        representantes = new ArrayList<>();

        OngService ongService = new OngService();
        Ong ongTeste = ongService.findAllOngs().get(2);

        representantes.add(new Representante(1L, "Representante 1", "teste1@representante.com", "123", "(22) 98989-9898", "Representante", ongTeste));
    }

    public void saveRepresentante(Representante representante) {
        if (representante.getIdUser() == null) {
            representante.setIdUser(nextId++);
        }

        representantes.add(representante);

        System.out.println("ID: " + representante.getIdUser() +
        " Nome: " + representante.getNome() +
        " Email: " + representante.getEmail() +
        " Telefone: " + representante.getTelefone() + 
        " Função: " + representante.getFuncao() +
        " ONG: " + representante.getOng().getNome());

    }

    public Representante findRepresentanteById(Long id) {
        // testando com o arraylist
        if (id == null) {
            return null;
        }

        for (Representante representante : representantes) {
            if (representante.getIdUser() != null && representante.getIdUser().equals(id)) {
                return representante;
            }
        }

        return null;
    }

    public ArrayList<Representante> findAllRepresentantes() {
        return representantes;
    }
}
