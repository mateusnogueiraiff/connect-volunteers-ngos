package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Administrador;

@Service
public class AdministradorService {

    private ArrayList<Administrador> administradores; // atributo global
    private Long nextId = 3L; 

    public AdministradorService() {
        administradores = new ArrayList<>();

        administradores.add(new Administrador(1L, "administrador1", "teste1@administrador.com", "123", "(22) 98989-9898", "administrador"));

        administradores.add(new Administrador(2L, "administrador2", "teste2@administrador.com", "321", "(22) 98989-4444", "administrador"));

    }

    public void saveAdministrador(Administrador administrador) {
        if (administrador.getIdUser() == null) {
            administrador.setIdUser(nextId++);
        }

        administradores.add(administrador);

        System.out.println("ID: " + administrador.getIdUser() +
        " Nome: " + administrador.getNome() +
        " Email: " + administrador.getEmail() +
        " Telefone: " + administrador.getTelefone() + 
        " Função: " + administrador.getFuncao());
        

    }

    public Administrador findAdministradorById(Long id) {
        
        if (id == null) {
            return null;
        }

        for (Administrador administrador : administradores) {
            if (administrador.getIdUser() != null && administrador.getIdUser().equals(id)) {
                return administrador;
            }
        }

        return null;
    }

    public ArrayList<Administrador> findAllAdministradores() {
        return administradores;
    }
}
