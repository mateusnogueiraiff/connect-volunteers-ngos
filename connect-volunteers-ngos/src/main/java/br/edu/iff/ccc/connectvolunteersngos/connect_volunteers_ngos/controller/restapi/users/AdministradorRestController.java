package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.restapi.users;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Administrador;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user.AdministradorService;
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
@RequestMapping("/api/v1/administradores")
@Tag(name = "Administradores", description = "Endpoints para gerenciamento de Administradores")
public class AdministradorRestController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    @Operation(summary = "Lista todos os administradores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de administradores retornada com sucesso")
    })
    public ResponseEntity<List<Administrador>> getAllAdministradores() {
        return ResponseEntity.ok(administradorService.findAllAdministradores());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um administrador por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
        @ApiResponse(responseCode = "404", description = "Administrador não encontrado")
    })
    public ResponseEntity<Administrador> getAdministradorById(@PathVariable Long id) {
        return ResponseEntity.ok(administradorService.findAdministradorById(id));
    }

    @PostMapping
    @Operation(summary = "Cria um novo administrador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Administrador criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<Administrador> createAdministrador(@RequestBody Administrador administrador) {
        Administrador novoAdmin = administradorService.saveAdministrador(administrador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAdmin);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um administrador existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Administrador não encontrado para atualização")
    })
    public ResponseEntity<Administrador> updateAdministrador(@PathVariable Long id, @RequestBody Administrador administrador) {
        Administrador existente = administradorService.findAdministradorById(id);

        existente.setNome(administrador.getNome());
        existente.setEmail(administrador.getEmail());
        existente.setTelefone(administrador.getTelefone());
        existente.setSenha(administrador.getSenha());

        Administrador atualizado = administradorService.saveAdministrador(existente);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um administrador por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Administrador excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Administrador não encontrado para exclusão")
    })
    public ResponseEntity<Void> deleteAdministrador(@PathVariable Long id) {
        administradorService.deleteAdministrador(id);
        return ResponseEntity.noContent().build();
    }
}