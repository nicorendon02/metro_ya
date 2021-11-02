package com.umanizales.metro_ya.controller;

import com.umanizales.metro_ya.application.dto.ResponseBinaryTreeDto;
import com.umanizales.metro_ya.controller.dto.ErrorDTO;
import com.umanizales.metro_ya.exception.BinaryTreeException;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import org.springframework.boot.context.config.ConfigDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(DataNotFoundException.class)
    protected ResponseEntity<?> handle(DataNotFoundException ex) {

        List<ErrorDTO> errorsNotFound = new ArrayList<>();
        errorsNotFound.add(new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
        String message = "There is no data";
        ResponseBinaryTreeDto response = new ResponseBinaryTreeDto(null, message, errorsNotFound);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BinaryTreeException.class)
    protected ResponseEntity<?> handle(BinaryTreeException ex) {

        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(new ErrorDTO(HttpStatus.CONFLICT.value(), ex.getMessage()));
        String message = "A logic failure has occurred!";
        ResponseBinaryTreeDto response = new ResponseBinaryTreeDto(null, message, errors);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handle(MethodArgumentNotValidException ex) {

        List<ErrorDTO> listErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            listErrors.add(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), fieldName + " " + errorMessage));
        });
        String message = "Algunos campos son inválidos o faltantes, por favor corrija los errores y vuelva a intentarlo";
        ResponseBinaryTreeDto response = new ResponseBinaryTreeDto(null, message, listErrors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConfigDataNotFoundException.class)
    protected ResponseEntity<?> handle(ConfigDataNotFoundException ex) {

        List<ErrorDTO> errorsNotFound = new ArrayList<>();
        errorsNotFound.add(new ErrorDTO(HttpStatus.CONFLICT.value(), ex.getMessage()));
        String message = "No se ha encontrado el dato";
        ResponseBinaryTreeDto response = new ResponseBinaryTreeDto(null, message, errorsNotFound);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
