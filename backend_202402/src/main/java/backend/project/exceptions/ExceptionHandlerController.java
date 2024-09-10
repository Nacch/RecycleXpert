package backend.project.exceptions;

import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(KeyRepeatedDataException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorMessage keyRepeatedDataExceptionHandler(KeyRepeatedDataException ex , WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_ACCEPTABLE.value(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
        return errorMessage;
    }



    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
        return errorMessage;
    }


    @ExceptionHandler(InvalidActionException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage invalidActionExceptionHandler(InvalidActionException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
        return errorMessage;
    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage invalidDataExceptionHandler(InvalidDataException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
        return errorMessage;
    }

}
