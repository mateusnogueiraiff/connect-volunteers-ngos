package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;

@Service
public class VoluntarioService {

    private ArrayList<Voluntario> voluntarios; // atributo global
    private Long nextId = 4L; // já existem 3 na lista inicial - SOMENTE PARA SIMULAÇÃO

    public VoluntarioService() {
        voluntarios = new ArrayList<>();
        voluntarios.add(new Voluntario(1L, "Voluntário 1", "teste1@voluntario.com", "123", "(11) 98989-9898", "Voluntário"));
        voluntarios.add(new Voluntario(2L, "Voluntário 2", "teste2@voluntario.com", "321", "(22) 98989-9898", "Voluntário"));
        voluntarios.add(new Voluntario(3L, "Voluntário 3", "teste3@voluntario.com", "000", "(33) 98989-9898", "Voluntário"));
    }

    public void saveVoluntario(Voluntario voluntario) {
        if (voluntario.getIdUser() == null) {
            voluntario.setIdUser(nextId++);
        }
        voluntarios.add(voluntario);

        System.out.println("ID: " + voluntario.getIdUser() +
        " Nome: " + voluntario.getNome() + 
        " Email: " + voluntario.getEmail() +
        " Telefone: " + voluntario.getTelefone() + 
        " Função: " + voluntario.getFuncao());

    }

    public Voluntario findVoluntarioById(Long id) {
        // testando com o arraylist
        if (id == null) {
            return null;
        }

        for (Voluntario voluntario : voluntarios) {
            if (voluntario.getIdUser() != null && voluntario.getIdUser().equals(id)) {
                return voluntario;
            }
        }
        return null;

        /* teste com usuário fixo 
        Voluntario voluntario = new Voluntario();

        if (id == null) {
            return null;
        }
    
        voluntario.setIdUser(id);
        voluntario.setNome("Voluntário Exemplo");
        voluntario.setEmail("volunt@gmail.com");
        voluntario.setTelefone("(22) 99999-8888");
        voluntario.setFuncao("Voluntário");      
        return voluntario;
        */
    }

    public ArrayList<Voluntario> findAllVoluntarios() {
        return voluntarios;
    }
}
