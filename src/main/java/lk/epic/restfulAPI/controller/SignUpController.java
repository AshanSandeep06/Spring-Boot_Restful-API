package lk.epic.restfulAPI.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lk.epic.restfulAPI.dto.SignUpDTO;
import lk.epic.restfulAPI.service.SignUpService;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/signup")
@CrossOrigin
@Api(value = "SignUp Controller", protocols = "http")
// REST API Endpoints
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @PostMapping
    @ApiOperation(value = "To SignUp a User", response = ResponseEntity.class, code = 200)
    public ResponseEntity<ResponseUtil> signUp(@Valid @RequestBody SignUpDTO signUpDTO) {
        ResponseUtil signUp = signUpService.signUp(signUpDTO);

        switch (signUp.getResponseCode()) {
            case "00":
                return ResponseEntity.status(HttpStatus.OK).body(signUp);
            case "04":
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(signUp);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(signUp);
        }

        /*if (signUp.getResponseCode().equals("00")) {
            return ResponseEntity.status(HttpStatus.OK).body(signUp);
        } else if (signUp.getResponseCode().equals("04")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(signUp);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(signUp);
        }*/
    }
}
