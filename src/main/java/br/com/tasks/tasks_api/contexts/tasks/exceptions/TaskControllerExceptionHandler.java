package br.com.tasks.tasks_api.contexts.tasks.exceptions;

import br.com.tasks.tasks_api.libs.exception.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class TaskControllerExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException(NoSuchElementException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Tarefa não encontrada", "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Informações inválidas para atualização da Tarefa", "400");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Informações inválidas para criação da Tarefa", "400");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }
}
