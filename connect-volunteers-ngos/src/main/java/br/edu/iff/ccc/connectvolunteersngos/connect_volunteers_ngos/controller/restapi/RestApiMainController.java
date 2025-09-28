package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.restapi;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user.VoluntarioService;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception.VoluntarioNaoEncontradoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestApiMainController {

    @Autowired
    private VoluntarioService voluntarioService;
    
    @GetMapping
    public ResponseEntity<String> getApiHome() {
        return ResponseEntity.ok("API ConnectVolunteersNGOs - versão 1.0");
    }

    @GetMapping("/voluntarios")
    public ResponseEntity<List<Voluntario>> getAllVoluntarios() {
        List<Voluntario> voluntarios = voluntarioService.findAllVoluntarios();
        return ResponseEntity.ok(voluntarios);
    }

    @GetMapping("/voluntarios/{id}")
    public ResponseEntity<Voluntario> getVoluntarioById(@PathVariable Long id) {
        Voluntario voluntario = voluntarioService.findVoluntarioById(id);
        return ResponseEntity.ok(voluntario);
    }

    @PostMapping("/voluntarios")
    public ResponseEntity<Voluntario> createVoluntario(@RequestBody Voluntario voluntario) {
        voluntarioService.saveVoluntario(voluntario);
        return ResponseEntity.status(HttpStatus.CREATED).body(voluntario);
    }

    @PutMapping("/voluntarios/{id}")
    public ResponseEntity<Voluntario> updateVoluntario(@PathVariable Long id, @RequestBody Voluntario voluntario) {
        Voluntario existente = voluntarioService.findVoluntarioById(id);
        if (existente == null) {
            throw new VoluntarioNaoEncontradoException("Voluntário com id " + id + " não encontrado.");
        }

        existente.setNome(voluntario.getNome());
        existente.setEmail(voluntario.getEmail());
        existente.setTelefone(voluntario.getTelefone());
        existente.setSenha(voluntario.getSenha());
        existente.setFuncao(voluntario.getFuncao());

        voluntarioService.saveVoluntario(existente);
        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/voluntarios/{id}")
    public ResponseEntity<Void> deleteVoluntario(@PathVariable Long id) {
        Voluntario existente = voluntarioService.findVoluntarioById(id);
        if (existente == null) {
            throw new VoluntarioNaoEncontradoException("Voluntário com id " + id + " não encontrado.");
        }
        voluntarioService.deleteVoluntario(id);
        return ResponseEntity.noContent().build();
    }

    /* Query - Buscas Personalizadas */
    // Buscar voluntários por parte do nome
    @GetMapping("/voluntarios/search")
    public ResponseEntity<List<Voluntario>> searchVoluntariosByNome(@RequestParam String nome) {
        List<Voluntario> voluntarios = voluntarioService.searchVoluntariosByNome(nome);
        if (voluntarios.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 se não encontrar ninguém
        }
        return ResponseEntity.ok(voluntarios);
    }

    // Buscar voluntário por email exato
    @GetMapping("/voluntarios/email")
    public ResponseEntity<Voluntario> getVoluntarioByEmail(@RequestParam String email) {
        Voluntario voluntario = voluntarioService.findVoluntarioByEmail(email);
        if (voluntario == null) {
            throw new VoluntarioNaoEncontradoException("Voluntário com email " + email + " não encontrado.");
        }
        return ResponseEntity.ok(voluntario);
    }
}