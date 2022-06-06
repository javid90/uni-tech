package az.unibank.unitech.exception;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String MESSAGE = "Argument Validation failed";
    public static final String UNITECH_MESSAGE = "Uni-Tech error";

    // handling specific exception
    /*@ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundHandling(InvalidFieldException exception,
            WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST, MESSAGE,
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }*/

    // handling specific exception
    /*@ExceptionHandler(EngineRuntimeException.class)
    public ResponseEntity<ErrorDetails> filenetExceptionHandling(EngineRuntimeException exception,
            WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.NOT_FOUND,
                FILENET_MESSAGE, exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }*/

    // handling specific exception
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorDetails> generalExceptionHandling(GeneralException exception,
            WebRequest request, HttpServletResponse response) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST,
                "General Exception", exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetails> badCredentialExceptionHandling(BadCredentialsException exception,
            WebRequest request, HttpServletResponse response) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.UNAUTHORIZED,
                "Bad Credentials Exception", exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
