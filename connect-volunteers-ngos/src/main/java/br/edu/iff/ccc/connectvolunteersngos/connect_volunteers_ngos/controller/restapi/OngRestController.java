package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.restapi;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Ong;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.OngService;
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
@RequestMapping("/api/v1/ongs")
@Tag(name = "ONGs", description = "Endpoints para gerenciamento de ONGs")
public class OngRestController {

    @Autowired
    private OngService ongService;

    @GetMapping
    @Operation(summary = "Lista todas as ONGs")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de ONGs retornada com sucesso")
    })
    public ResponseEntity<List<Ong>> getAllOngs() {
        return ResponseEntity.ok(ongService.findAllOngs());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma ONG por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "ONG encontrada"),
        @ApiResponse(responseCode = "404", description = "ONG não encontrada")
    })
    public ResponseEntity<Ong> getOngById(@PathVariable Long id) {
        return ResponseEntity.ok(ongService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Cria uma nova ONG")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "ONG criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<Ong> createOng(@RequestBody Ong ong) {
        Ong novaOng = ongService.saveOng(ong);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaOng);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma ONG existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "ONG atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "ONG não encontrada para atualização")
    })
    public ResponseEntity<Ong> updateOng(@PathVariable Long id, @RequestBody Ong ong) {
        Ong existente = ongService.findById(id);

        existente.setNome(ong.getNome());
        existente.setCnpj(ong.getCnpj());
        existente.setDescricao(ong.getDescricao());
        existente.setTelefone(ong.getTelefone());
        existente.setEmail(ong.getEmail());

        Ong atualizada = ongService.saveOng(existente);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma ONG por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "ONG excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "ONG não encontrada para exclusão")
    })
    public ResponseEntity<Void> deleteOng(@PathVariable Long id) {
        // Garante que a ONG existe antes de tentar deletar, ativando o erro 404 se não existir.
        ongService.findById(id);
        ongService.deleteOng(id);
        return ResponseEntity.noContent().build();
    }
}