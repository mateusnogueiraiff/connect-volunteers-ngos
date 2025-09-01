package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.oportunidade;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Vaga;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Ong;

@Service
public class VagaService {

    private ArrayList<Vaga> vagas = new ArrayList<>();
    private Long nextId = 1L;

    public VagaService() {
        // dados fictícios de exemplo
        Ong ongExemplo = new Ong(1L, "ONG Exemplo", "00.000.000/0001-00", "ONG de teste", "(22) 99999-9999", "ong@teste.com");
        vagas.add(new Vaga(nextId++, "Professor de Informática", "Dar aulas básicas de informática", "Conhecimento básico em Windows", "Centro Comunitário", "2025-09-10 14:00", 5, ongExemplo));
    }

    public ArrayList<Vaga> findAllVagas() {
        return vagas;
    }

    public Vaga findVagaById(Long id) {
        if (id == null) {
            return null;
        }

        for (Vaga vaga : vagas) {
            if (vaga.getId().equals(id)) {
                return vaga;
            }
        }
        return null;
    }

    public void saveVaga(Vaga vaga) {
        if (vaga.getId() == null) {
            vaga.setId(nextId++);
        }
        vagas.add(vaga);
    }
}
