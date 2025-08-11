package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.controller.view.login_cadastro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CadastroControllerView {

    @GetMapping("/cadastro")
    public String exibirCadastro() {
        return "login-cadastro/cadastro";
    }

    @PostMapping("/cadastro")
    public String processarCadastro(
            @RequestParam("nome") String nome,
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            @RequestParam("tipoUsuario") String tipoUsuario,
            RedirectAttributes redirectAttributes //envia o nome de user para a pag seguinte
    ) {
        /* futuramente salvará os dados no banco.
        no momento, simulando q o cadastro deu certo e redirecionando para a home específica do tipo de usuário */ 

        // Passando o nome para mostrar na home (simulação)
        redirectAttributes.addFlashAttribute("nome", nome);

        if (tipoUsuario.equals("voluntario")) {
            return "redirect:/home/home-voluntario";
        } else {
            return "redirect:/home/home-representante";
        }
    }
}
