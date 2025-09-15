package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ViewGlobalAdviceException {

    // devolve uma pag de erro
    @ExceptionHandler(VoluntarioNaoEncontradoException.class)
    public String handleVoluntarioNaoEncontrado(VoluntarioNaoEncontradoException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}