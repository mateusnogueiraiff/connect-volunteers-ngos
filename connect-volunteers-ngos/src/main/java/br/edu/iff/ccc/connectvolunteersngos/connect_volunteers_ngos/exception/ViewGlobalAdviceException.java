package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception.users.AdministradorNaoEncontradoException;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception.users.RepresentanteNaoEncontradoException;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception.users.VoluntarioNaoEncontradoException;

@ControllerAdvice
public class ViewGlobalAdviceException {

    // devolve uma pag de erro
    @ExceptionHandler(VoluntarioNaoEncontradoException.class)
    public String handleVoluntarioNaoEncontrado(VoluntarioNaoEncontradoException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(RepresentanteNaoEncontradoException.class)
    public String handleRepresentanteNaoEncontrado(RepresentanteNaoEncontradoException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(OngNaoEncontradaException.class)
    public String handleOngNaoEncontrada(OngNaoEncontradaException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(AdministradorNaoEncontradoException.class)
    public String handleAdministradorNaoEncontrado(AdministradorNaoEncontradoException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}