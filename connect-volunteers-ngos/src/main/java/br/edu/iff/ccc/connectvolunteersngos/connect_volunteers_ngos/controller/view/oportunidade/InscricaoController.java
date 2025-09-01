package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.oportunidade;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Inscricao;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade.Vaga;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.oportunidade.InscricaoService;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.oportunidade.VagaService;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user.VoluntarioService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @Autowired
    private VagaService vagaService;

    @Autowired
    private VoluntarioService voluntarioService;

    /**
     * Inscreve o voluntário em uma vaga.
     * Exemplo de chamada: POST /inscricoes/vaga/1/voluntario/2
     */
    @PostMapping("/vaga/{vagaId}")
    public String inscreverEmVaga(@PathVariable Long vagaId, HttpSession session, RedirectAttributes redirectAttributes) {

        Voluntario voluntario = (Voluntario) session.getAttribute("usuarioLogado");
        Vaga vaga = vagaService.findVagaById(vagaId);

        if (vaga == null || voluntario == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Vaga ou Voluntário não encontrados.");
            return "redirect:/vagas";
        }

        Inscricao inscricao = inscricaoService.saveInscricao(voluntario, vaga);

        if (inscricao != null && "INSCRITO".equalsIgnoreCase(inscricao.getStatus())) {
            redirectAttributes.addFlashAttribute("successMessage", "Inscrição realizada com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("infoMessage", "Você já está inscrito nessa vaga.");
        }

        return "redirect:/vagas";
    }

    /**
     * Lista todas as inscrições de um voluntário
     */
    @GetMapping("/voluntario/{voluntarioId}")
    public String listarInscricoesVoluntario(@PathVariable Long voluntarioId, Model model, RedirectAttributes redirectAttributes) {

        Voluntario voluntario = voluntarioService.findVoluntarioById(voluntarioId);

        if (voluntario == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Voluntário não encontrado.");
            return "redirect:/voluntarios";
        }

        model.addAttribute("inscricoes", inscricaoService.findAllByVoluntario(voluntario));
        model.addAttribute("voluntario", voluntario);

        return "oportunidade/inscricoes-voluntario.html"; 
    }

    /**
     * Lista todas as inscrições de uma vaga (útil para o representante da ONG ver quem se inscreveu)
     */
    @GetMapping("/vaga/{vagaId}")
    public String listarInscricoesVaga(@PathVariable Long vagaId, Model model, RedirectAttributes redirectAttributes) {

        Vaga vaga = vagaService.findVagaById(vagaId);

        if (vaga == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Vaga não encontrada.");
            return "redirect:/vagas";
        }

        model.addAttribute("inscricoes", inscricaoService.findAllByVaga(vaga));
        model.addAttribute("vaga", vaga);

        return "oportunidade/inscricoes-vaga.html"; 
    }
}
