package pe.edu.upc.noctuwellbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(IncompleteDataException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ExeceptionMessage incompleteDataException(IncompleteDataException e, WebRequest request) {
        return new ExeceptionMessage(
                HttpStatus.NOT_ACCEPTABLE.value(),
                "IncompleteDataException",
                e.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    }


    @ExceptionHandler(InvalidDataRangeException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ExeceptionMessage invalidDataRangeException(InvalidDataRangeException e, WebRequest request) {
        return new ExeceptionMessage(
                HttpStatus.NOT_ACCEPTABLE.value(),
                "InvalidDataRangeException",
                e.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExeceptionMessage notFoundException(ResourceNotFoundException e, WebRequest request) {
        return new ExeceptionMessage(
                HttpStatus.NOT_FOUND.value(),
                "ResourceNotFoundException",
                e.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    }


    @ExceptionHandler(KeyRepeatedDataExeception.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ExeceptionMessage notFoundException(KeyRepeatedDataExeception e, WebRequest request) {
        return new ExeceptionMessage(
                HttpStatus.NOT_ACCEPTABLE.value(),
                "KeyRepeatedDataExeception",
                e.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    }


}
