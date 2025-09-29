package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.restapi.oportunidade;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Vaga;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.oportunidade.VagaService;
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
@RequestMapping("/api/v1/vagas")
@Tag(name = "Vagas", description = "Endpoints para gerenciamento de Vagas de voluntariado")
public class VagaRestController {

    @Autowired
    private VagaService vagaService;

    @GetMapping
    @Operation(summary = "Lista todas as vagas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de vagas retornada com sucesso")
    })
    public ResponseEntity<List<Vaga>> getAllVagas() {
        return ResponseEntity.ok(vagaService.findAllVagas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma vaga por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vaga encontrada"),
        @ApiResponse(responseCode = "404", description = "Vaga não encontrada")
    })
    public ResponseEntity<Vaga> getVagaById(@PathVariable Long id) {
        return ResponseEntity.ok(vagaService.findVagaById(id));
    }

    @PostMapping
    @Operation(summary = "Cria uma nova vaga")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Vaga criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<Vaga> createVaga(@RequestBody Vaga vaga) {
        Vaga novaVaga = vagaService.saveVaga(vaga);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVaga);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma vaga existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vaga atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Vaga não encontrada para atualização")
    })
    public ResponseEntity<Vaga> updateVaga(@PathVariable Long id, @RequestBody Vaga vaga) {
        Vaga existente = vagaService.findVagaById(id);

        existente.setNome(vaga.getNome());
        existente.setDescricao(vaga.getDescricao());
        existente.setRequisitos(vaga.getRequisitos());
        existente.setLocal(vaga.getLocal());
        existente.setDataHorario(vaga.getDataHorario());
        existente.setQuantidadeDeVagas(vaga.getQuantidadeDeVagas());
        existente.setOng(vaga.getOng());

        Vaga atualizada = vagaService.saveVaga(existente);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma vaga por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Vaga excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Vaga não encontrada para exclusão")
    })
    public ResponseEntity<Void> deleteVaga(@PathVariable Long id) {
        vagaService.deleteVaga(id);
        return ResponseEntity.noContent().build();
    }
}