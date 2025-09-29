package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.restapi.users;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Representante;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user.RepresentanteService;
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
@RequestMapping("/api/v1/representantes")
@Tag(name = "Representantes", description = "Endpoints para gerenciamento de Representantes de ONGs")
public class RepresentanteRestController {

    @Autowired
    private RepresentanteService representanteService;

    @GetMapping
    @Operation(summary = "Lista todos os representantes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de representantes retornada com sucesso")
    })
    public ResponseEntity<List<Representante>> getAllRepresentantes() {
        return ResponseEntity.ok(representanteService.findAllRepresentantes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um representante por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Representante encontrado"),
        @ApiResponse(responseCode = "404", description = "Representante não encontrado")
    })
    public ResponseEntity<Representante> getRepresentanteById(@PathVariable Long id) {
        return ResponseEntity.ok(representanteService.findRepresentanteById(id));
    }

    @PostMapping
    @Operation(summary = "Cria um novo representante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Representante criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<Representante> createRepresentante(@RequestBody Representante representante) {
        Representante novoRepresentante = representanteService.saveRepresentante(representante);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRepresentante);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um representante existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Representante atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Representante não encontrado para atualização")
    })
    public ResponseEntity<Representante> updateRepresentante(@PathVariable Long id, @RequestBody Representante representante) {
        Representante existente = representanteService.findRepresentanteById(id);

        existente.setNome(representante.getNome());
        existente.setEmail(representante.getEmail());
        existente.setTelefone(representante.getTelefone());
        existente.setSenha(representante.getSenha());
        // A ONG associada também poderia ser atualizada aqui, se necessário.

        Representante atualizado = representanteService.saveRepresentante(existente);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um representante por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Representante excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Representante não encontrado para exclusão")
    })
    public ResponseEntity<Void> deleteRepresentante(@PathVariable Long id) {
        // Verifica se o representante existe antes de deletar para lançar a exceção 404
        representanteService.findRepresentanteById(id);
        representanteService.deleteRepresentante(id);
        return ResponseEntity.noContent().build();
    }
}