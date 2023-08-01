package lk.epic.restfulAPI.controller;

import lk.epic.restfulAPI.dto.SignUpDTO;
import lk.epic.restfulAPI.service.SignUpService;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/signup")
@CrossOrigin
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public ResponseEntity<ResponseUtil> signUp(@RequestBody SignUpDTO signUpDTO) {
        ResponseUtil signUp = signUpService.signUp(signUpDTO);

        if (signUp.getResponseCode().equals("00")) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(signUp);
        } else if (signUp.getResponseCode().equals("04")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(signUp);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(signUp);
        }
    }
}
