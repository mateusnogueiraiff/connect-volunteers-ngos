package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.*;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user.*;

@Service
public class LoginService {

    @Autowired
    private VoluntarioService voluntarioService;

    @Autowired
    private RepresentanteService representanteService;

    public Usuario autenticar(String email, String senha) {

        for (Voluntario v : voluntarioService.findAllVoluntarios()) {
            if (v.getEmail().equals(email) && v.getSenha().equals(senha)) {
                return v;
            }
        }

        for (Representante r : representanteService.findAllRepresentantes()) {
            if (r.getEmail().equals(email) && r.getSenha().equals(senha)) {
                return r;
            }
        }

        //se não encontrar nenhum user
        return null;
    }
}
