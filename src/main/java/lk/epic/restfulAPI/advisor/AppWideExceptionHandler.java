package lk.epic.restfulAPI.advisor;

import io.jsonwebtoken.MalformedJwtException;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@CrossOrigin
public class AppWideExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseUtil> handleExceptions(Exception e) {
        System.out.println(e.getMessage());
        System.out.println(e.getLocalizedMessage());
        System.out.println(e.getClass().getName());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseUtil("06", "Bad Request", null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseUtil> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(new ResponseUtil("06", "Validation Failed", errors));
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ResponseUtil> handleBadCredentialsExceptions(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(new ResponseUtil("03", "Invalid Credentials", null));
    }

    @ExceptionHandler({MalformedJwtException.class})
    public ResponseEntity<ResponseUtil> handleMalformedJwtExceptionExceptions(MalformedJwtException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseUtil("03", "Not Authorized", null));
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ResponseUtil> handleUnauthorizedAccessException(HttpClientErrorException.Forbidden e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseUtil("03", "Not Authorized", null));
    }

    private Throwable getRootCause(Throwable throwable) {
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }
}
