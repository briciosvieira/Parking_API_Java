package com.sousa.demo_parking_api.web.exception;

import com.sousa.demo_parking_api.runtimeException.UsernameUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    //argumento enviado não é válido
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageException> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                                 HttpServletRequest request,
                                                                                 BindingResult result){
        log.error("Api error", ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessageException(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo Invalido", result));

    }


    // error 500, erro interno.
    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity<ErrorMessageException> handleUsernameUniqueViolationException(RuntimeException ex,
                                                                HttpServletRequest request){
        log.error("Api error 500", ex);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessageException(request, HttpStatus.CONFLICT, ex.getMessage()));

    }
}
