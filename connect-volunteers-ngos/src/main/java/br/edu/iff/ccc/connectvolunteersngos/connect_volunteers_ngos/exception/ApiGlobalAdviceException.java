package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception.users.AdministradorNaoEncontradoException;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception.users.RepresentanteNaoEncontradoException;
import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.exception.users.VoluntarioNaoEncontradoException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestControllerAdvice
public class ApiGlobalAdviceException {

    //Voluntario
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

    //Representante
    @ExceptionHandler(RepresentanteNaoEncontradoException.class)
    public ProblemDetail handleRepresentanteNaoEncontrado(HttpServletRequest req, RepresentanteNaoEncontradoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());

        problemDetail.setTitle("Representante não encontrado");
        problemDetail.setProperty("url", req.getRequestURL().toString());
        problemDetail.setProperty("timestamp", LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toString());
        problemDetail.setProperty("status", HttpStatus.NOT_FOUND.value());
        problemDetail.setProperty("message", ex.getMessage());
        problemDetail.setProperty("exception", ex.getClass().getName());
        problemDetail.setProperty("path", req.getRequestURI());

        return problemDetail;
    }

    //ONG
    @ExceptionHandler(OngNaoEncontradaException.class)
    public ProblemDetail handleOngNaoEncontrada(HttpServletRequest req, OngNaoEncontradaException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());

        problemDetail.setTitle("ONG não encontrada");
        problemDetail.setProperty("url", req.getRequestURL().toString());
        problemDetail.setProperty("timestamp", LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toString());
        problemDetail.setProperty("status", HttpStatus.NOT_FOUND.value());
        problemDetail.setProperty("message", ex.getMessage());
        problemDetail.setProperty("exception", ex.getClass().getName());
        problemDetail.setProperty("path", req.getRequestURI());

        return problemDetail;
    }

    //Administrador
    @ExceptionHandler(AdministradorNaoEncontradoException.class)
    public ProblemDetail handleAdministradorNaoEncontrado(HttpServletRequest req, AdministradorNaoEncontradoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());

        problemDetail.setTitle("Administrador não encontrado");
        problemDetail.setProperty("url", req.getRequestURL().toString());
        problemDetail.setProperty("timestamp", LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toString());
        problemDetail.setProperty("status", HttpStatus.NOT_FOUND.value());
        problemDetail.setProperty("message", ex.getMessage());
        problemDetail.setProperty("exception", ex.getClass().getName());
        problemDetail.setProperty("path", req.getRequestURI());

        return problemDetail;
    }
}