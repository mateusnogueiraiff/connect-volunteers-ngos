package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.oportunidade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Inscricao;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Vaga;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;

@Service
public class InscricaoService {

    private List<Inscricao> inscricoes = new ArrayList<>();
    private Long nextId = 1L;

    public Inscricao saveInscricao(Voluntario voluntario, Vaga vaga)  {

        // verifica se já existe inscrição do voluntário nessa vaga
        Inscricao existente = jaInscrito(voluntario, vaga);
        if (existente != null) {
            return existente;
        }

        // caso não tenha, cria uma nova
        Inscricao inscricao = new Inscricao(nextId++,voluntario, vaga, "INSCRITO", LocalDateTime.now());

        inscricoes.add(inscricao);
        return inscricao;
    }
    public Inscricao jaInscrito(Voluntario voluntario, Vaga vaga) {
        for (Inscricao i : inscricoes) {
            if (i.getVoluntario().getIdUser().equals(voluntario.getIdUser()) && i.getVaga().getId().equals(vaga.getId()) && !"CANCELADO".equalsIgnoreCase(i.getStatus())) {
                return i;
            }
        }
        return null;
    }

    public List<Inscricao> findAllInscricoes() {
        return inscricoes;
    }

    public List<Inscricao> findAllByVoluntario(Voluntario voluntario) {
        List<Inscricao> result = new ArrayList<>();
        for (Inscricao i : inscricoes) {
            if (i.getVoluntario() != null && i.getVoluntario().getIdUser().equals(voluntario.getIdUser())) {
                result.add(i);
            }
        }
        return result;
    }

    public List<Inscricao> findAllByVaga(Vaga vaga) {
        List<Inscricao> result = new ArrayList<>();
        for (Inscricao i : inscricoes) {
            if (i.getVaga() != null && i.getVaga().getId().equals(vaga.getId())) {
                result.add(i);
            }
        }
        return result;
    }
}
