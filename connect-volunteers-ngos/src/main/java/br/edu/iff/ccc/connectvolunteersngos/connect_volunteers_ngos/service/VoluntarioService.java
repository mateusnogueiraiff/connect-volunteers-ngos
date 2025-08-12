package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Voluntario;

@Service
public class VoluntarioService {

    private ArrayList<Voluntario> voluntarios; // atributo global

    public VoluntarioService() {
        voluntarios = new ArrayList<>();
        voluntarios.add(new Voluntario(1L, "Voluntario 1", "voluntario1@email.com", "123", "(22) 99999-8888", "Voluntário"));
        voluntarios.add(new Voluntario(2L, "Voluntario 2", "voluntario2@email.com", "123", "(22) 99999-8888", "Voluntário"));
        voluntarios.add(new Voluntario(3L, "Voluntario 3", "voluntario3@email.com", "123", "(22) 99999-8888", "Voluntário"));
    }

    public void saveVoluntario(Voluntario voluntario) {
        voluntarios.add(voluntario);
    }

    public Voluntario findVolunarioById(Long id) {
        if (id == null) {
            return null;
        }

        for (int i = 0; i < voluntarios.size(); i++) {
            Voluntario voluntario = voluntarios.get(i);
            if (voluntario.getIdVoluntario().equals(id)) {
                return voluntario;
            }
        }
        return null;
    }

    public ArrayList<Voluntario> findAllVoluntarios() {
        return voluntarios;
    }
}
