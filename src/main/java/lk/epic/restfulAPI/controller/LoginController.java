package lk.epic.restfulAPI.controller;

import lk.epic.restfulAPI.dto.LoginDTO;
import lk.epic.restfulAPI.service.LoginService;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<ResponseUtil> authenticate(@RequestBody LoginDTO loginDTO) {
//        return loginService.authenticate(loginDTO);
        ResponseUtil authenticate = loginService.authenticate(loginDTO);
        if (authenticate.getResponseCode().equals("00")) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(authenticate);
        } else if (authenticate.getResponseCode().equals("02")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(authenticate);
        } else if (authenticate.getResponseCode().equals("03")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticate);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authenticate);
        }
    }
}
