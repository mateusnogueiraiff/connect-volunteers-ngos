package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.restapi.users;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception.users.VoluntarioNaoEncontradoException;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user.VoluntarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/voluntarios")
@Tag(name = "Voluntários", description = "Endpoints para gerenciamento de Voluntários")
public class VoluntarioRestController {

    @Autowired
    private VoluntarioService voluntarioService;

    // --- Endpoints CRUD ---
    
    @GetMapping()
    @Operation(summary = "Lista todos os voluntários")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de voluntários retornada com sucesso")
    })
    public ResponseEntity<List<Voluntario>> getAllVoluntarios() {
        List<Voluntario> voluntarios = voluntarioService.findAllVoluntarios();
        return ResponseEntity.ok(voluntarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um voluntário por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voluntário encontrado"),
        @ApiResponse(responseCode = "404", description = "Voluntário não encontrado")
    })
    public ResponseEntity<Voluntario> getVoluntarioById(@PathVariable Long id) {
        Voluntario voluntario = voluntarioService.findVoluntarioById(id);
        return ResponseEntity.ok(voluntario);
    }

    @PostMapping()
    @Operation(summary = "Cria um novo voluntário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Voluntário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<Voluntario> createVoluntario(@RequestBody Voluntario voluntario) {
        voluntarioService.saveVoluntario(voluntario);
        return ResponseEntity.status(HttpStatus.CREATED).body(voluntario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um voluntário existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voluntário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Voluntário não encontrado para atualização")
    })
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um voluntário por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Voluntário excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Voluntário não encontrado para exclusão")
    })
    public ResponseEntity<Void> deleteVoluntario(@PathVariable Long id) {
        Voluntario existente = voluntarioService.findVoluntarioById(id);
        if (existente == null) {
            throw new VoluntarioNaoEncontradoException("Voluntário com id " + id + " não encontrado.");
        }
        voluntarioService.deleteVoluntario(id);
        return ResponseEntity.noContent().build();
    }

    // --- Endpoints de Busca Personalizada ---

    @GetMapping("/search")
    @Operation(summary = "Busca voluntários por parte do nome")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de voluntários encontrada"),
        @ApiResponse(responseCode = "204", description = "Nenhum voluntário encontrado para o nome fornecido")
    })
    public ResponseEntity<List<Voluntario>> searchVoluntariosByNome(@RequestParam String nome) {
        List<Voluntario> voluntarios = voluntarioService.searchVoluntariosByNome(nome);
        if (voluntarios.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 se não encontrar ninguém
        }
        return ResponseEntity.ok(voluntarios);
    }

    @GetMapping("/email")
    @Operation(summary = "Busca um voluntário pelo email exato")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voluntário encontrado"),
        @ApiResponse(responseCode = "404", description = "Voluntário não encontrado para o email fornecido")
    })
    public ResponseEntity<Voluntario> getVoluntarioByEmail(@RequestParam String email) {
        Voluntario voluntario = voluntarioService.findVoluntarioByEmail(email);
        if (voluntario == null) {
            throw new VoluntarioNaoEncontradoException("Voluntário com email " + email + " não encontrado.");
        }
        return ResponseEntity.ok(voluntario);
    }
}