package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestControllerAdvice
public class ApiGlobalAdviceException {

    @ExceptionHandler(VoluntarioNaoEncontradoException.class)
    public ProblemDetail handleVoluntarioNaoEncontrado(HttpServletRequest req, VoluntarioNaoEncontradoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());

        problemDetail.setTitle("Voluntário não encontrado");
        problemDetail.setProperty("url", req.getRequestURL().toString());
        problemDetail.setProperty("timestamp", LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toString());
        problemDetail.setProperty("status", HttpStatus.NOT_FOUND.value());
        problemDetail.setProperty("message", ex.getMessage());
        problemDetail.setProperty("exception", ex.getClass().getName());
        problemDetail.setProperty("path", req.getRequestURI());

        return problemDetail;
    }
}