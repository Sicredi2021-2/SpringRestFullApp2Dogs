package br.pucrs.sicredi.dogs.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.pucrs.sicredi.dogs.service.DogsNotFoundException;
import br.pucrs.sicredi.dogs.service.DogsServiceException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@Slf4j		// Anotação do Lombok para geração de campo de log
public class DogsServiceErrorAdvice {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
    	System.out.println("************* RunTime ################");
        return error(INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({DogsNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(DogsNotFoundException e) {
    	System.out.println("************* Not Found ################");    	
        return error(NOT_FOUND, e);
    }

    @ExceptionHandler({DogsServiceException.class})
    public ResponseEntity<String> handleDogsServiceException(DogsServiceException e){
    	System.out.println("************* Internal Error ################");
        return error(INTERNAL_SERVER_ERROR, e);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
    	System.out.println("************* N A ################");
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}

//@ControllerAdvice
//public class DogsServiceErrorAdvice {
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler({DogsNotFoundException.class})
//    public void handle(DogsNotFoundException e) {}
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler({DogsServiceException.class, SQLException.class, NullPointerException.class})
//    public void handle() {}
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({DogsServiceValidationException.class})
//    public void handle(DogsServiceValidationException e) {}
//}