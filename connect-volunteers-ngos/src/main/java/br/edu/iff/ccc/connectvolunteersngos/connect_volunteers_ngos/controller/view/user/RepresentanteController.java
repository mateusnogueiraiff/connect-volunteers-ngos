package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.user;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Ong;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Representante;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.OngService;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service.user.RepresentanteService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;



@Controller
@RequestMapping(path = "representantes")
public class RepresentanteController {

    @Autowired
    RepresentanteService representanteService;

    @Autowired
    OngService ongService;

    @GetMapping("/{id}")
    public String getRepresentantesPage(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {

        Representante representante = representanteService.findRepresentanteById(id);

        if (representante == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Representante não encontrado!");
            return "redirect:/representantes";
        }

        model.addAttribute("representante", representante);
        return "user/representante/representante.html";
    }

    @GetMapping()
    public String getAllRepresentantes(Model model) {
        model.addAttribute("representantes", representanteService.findAllRepresentantes());
        return "user/representante/representantes.html";
    }

    @GetMapping("/new")
    public String createNewRepresentante(Model model) {
        model.addAttribute("representante", new Representante());
        model.addAttribute("ongs", ongService.findAllOngs()); //enviando a lista de ONGs para o select
        return "user/representante/cadastro-representante.html";
    }

    @PostMapping()
    public String saveRepresentante(@Valid Representante representante, BindingResult error, @RequestParam("ongOption") String ongOption, Model model, RedirectAttributes redirectAttributes) {
        if (error.hasErrors()) {
            model.addAttribute("errorMessage", "Erro ao salvar o representante.");
            model.addAttribute("ongs", ongService.findAllOngs());
            return "user/representante/cadastro-representante.html";
        }

        if ("existente".equals(ongOption)) {
            // busca ONG já cadastrada
            Ong ongSelecionada = null;
            for (Ong ong : ongService.findAllOngs()) {
                if (ong.getId().equals(representante.getOng().getId())) {
                    ongSelecionada = ong;
                    break;
                }
            }
            representante.setOng(ongSelecionada);

        } else if ("nova".equals(ongOption)) {
            // cria nova ONG
            Ong novaOng = ongService.saveOng(representante.getOng());
            representante.setOng(novaOng);
        }

        representanteService.saveRepresentante(representante);
        redirectAttributes.addFlashAttribute("successMessage", "Representante salvo com sucesso!");
        return "redirect:/representantes";
    }
}
