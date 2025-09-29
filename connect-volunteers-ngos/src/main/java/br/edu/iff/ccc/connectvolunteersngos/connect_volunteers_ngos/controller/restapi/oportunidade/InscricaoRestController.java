package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.restapi.oportunidade;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Inscricao;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Vaga;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.oportunidade.InscricaoService;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.oportunidade.VagaService;
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
@RequestMapping("/api/v1/inscricoes")
@Tag(name = "Inscrições", description = "Endpoints para gerenciamento de Inscrições em Vagas")
public class InscricaoRestController {

    @Autowired
    private InscricaoService inscricaoService;

    @Autowired
    private VoluntarioService voluntarioService;

    @Autowired
    private VagaService vagaService;

    @PostMapping("/vaga/{vagaId}/voluntario/{voluntarioId}")
    @Operation(summary = "Inscreve um voluntário em uma vaga")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Inscrição realizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Vaga ou voluntário não encontrado")
    })
    public ResponseEntity<Inscricao> createInscricao(@PathVariable Long vagaId, @PathVariable Long voluntarioId) {
        Voluntario voluntario = voluntarioService.findVoluntarioById(voluntarioId);
        Vaga vaga = vagaService.findVagaById(vagaId);
        Inscricao novaInscricao = inscricaoService.createInscricao(voluntario, vaga);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaInscricao);
    }

    @GetMapping("/voluntario/{voluntarioId}")
    @Operation(summary = "Lista todas as inscrições de um voluntário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inscrições encontradas"),
        @ApiResponse(responseCode = "404", description = "Voluntário não encontrado")
    })
    public ResponseEntity<List<Inscricao>> getInscricoesByVoluntario(@PathVariable Long voluntarioId) {
        return ResponseEntity.ok(inscricaoService.findAllByVoluntario(voluntarioId));
    }

    @GetMapping("/vaga/{vagaId}")
    @Operation(summary = "Lista todos os inscritos em uma vaga")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inscrições encontradas"),
        @ApiResponse(responseCode = "404", description = "Vaga não encontrada")
    })
    public ResponseEntity<List<Inscricao>> getInscricoesByVaga(@PathVariable Long vagaId) {
        return ResponseEntity.ok(inscricaoService.findAllByVaga(vagaId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancela uma inscrição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Inscrição cancelada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Inscrição não encontrada")
    })
    public ResponseEntity<Void> cancelInscricao(@PathVariable Long id) {
        inscricaoService.cancelInscricao(id);
        return ResponseEntity.noContent().build();
    }
}