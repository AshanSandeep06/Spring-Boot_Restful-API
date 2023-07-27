package lk.epic.restfulAPI.controller;

import lk.epic.restfulAPI.dto.SignUpDTO;
import lk.epic.restfulAPI.service.SignUpService;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/signup")
@CrossOrigin
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public ResponseUtil signUp(@RequestBody SignUpDTO signUpDTO) {
        return signUpService.signUp(signUpDTO);
    }
}
