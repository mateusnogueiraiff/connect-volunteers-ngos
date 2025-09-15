package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // captura exceptions globalmente
public class ApiGlobalAdviceException {

    /* se um endpoint REST lançar VoluntarioNaoEncontradoException, a API devolve:
    
        HTTP 404 Not Found
        "Voluntário com id X não encontrado."

    */
    @ExceptionHandler(VoluntarioNaoEncontradoException.class)
    public ResponseEntity<String> handleVoluntarioNaoEncontrado(VoluntarioNaoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // add outros tratamentos de erro dps também
}
