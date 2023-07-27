package lk.epic.restfulAPI.advisor;

import lk.epic.restfulAPI.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@CrossOrigin
public class AppWideExceptionHandler {
    //Response Code - 500
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RuntimeException.class})
    public ResponseUtil handleRuntimeExceptions(RuntimeException e) {
        System.out.println(e.getMessage());
        return new ResponseUtil("Error", e.getMessage(), null);
    }
}
