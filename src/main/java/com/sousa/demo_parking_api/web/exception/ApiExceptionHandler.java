package com.sousa.demo_parking_api.web.exception;

import com.sousa.demo_parking_api.customException.EntityNotFoundException;
import com.sousa.demo_parking_api.customException.PasswordInvalidException;
import com.sousa.demo_parking_api.customException.UsernameUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;




@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    //Acesso negado
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessageException> accessDeniedException(AccessDeniedException ex,
                                                                       HttpServletRequest request
                                                                        ){
        log.error("Api error", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessageException(request, HttpStatus.FORBIDDEN, "Acesso negado"));

    }


    //Bad Request erro de campo de senha
    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorMessageException> passwordInvalidException(RuntimeException ex,
                                                                          HttpServletRequest request
                                                                        ){
        log.error("Api error", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessageException(request, HttpStatus.BAD_REQUEST, ex.getMessage()));

    }


    //argumento enviado não é válido // ex: campo faltando caracteres ou senha fora do padrão
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageException> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                                 HttpServletRequest request,
                                                                                 BindingResult result){
        log.error("Api error", ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessageException(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo Invalido", result));

    }

    //Bad request
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessageException> httpMessageNotReadableException(HttpMessageNotReadableException ex,
                                                                                 HttpServletRequest request){
        log.error("Api error", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessageException(request, HttpStatus.BAD_REQUEST, "Verifique os campos e tente novamente."));

    }

    // username unique// bloqueio de nomes repetidos
    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity<ErrorMessageException> usernameUniqueViolationException(RuntimeException ex,
                                                                                  HttpServletRequest request){
        log.error("Api error 500", ex);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessageException(request, HttpStatus.CONFLICT, ex.getMessage()));

    }

    // error 404, erro interno.
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageException> entityNotFoundException(RuntimeException ex,
                                                                         HttpServletRequest request){
        log.error("Api error 500", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessageException(request, HttpStatus.NOT_FOUND, ex.getMessage()));

    }
}
