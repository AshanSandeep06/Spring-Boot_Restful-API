package lk.epic.restfulAPI.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lk.epic.restfulAPI.dto.LoginDTO;
import lk.epic.restfulAPI.service.LoginService;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin
@Api(value = "Login Controller", protocols = "http")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    @ApiOperation(value = "Authenticate Or Login a User", response = ResponseEntity.class, code = 200)
    public ResponseEntity<ResponseUtil> authenticate(@Valid @RequestBody LoginDTO loginDTO) {
        ResponseUtil authenticate = loginService.authenticate(loginDTO);

        switch (authenticate.getResponseCode()) {
            case "00":
                return ResponseEntity.status(HttpStatus.OK).body(authenticate);
            case "02":
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(authenticate);
            case "03":
                return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(authenticate);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authenticate);
        }

        /*if (authenticate.getResponseCode().equals("00")) {
            return ResponseEntity.status(HttpStatus.OK).body(authenticate);
        } else if (authenticate.getResponseCode().equals("02")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(authenticate);
        } else if (authenticate.getResponseCode().equals("03")) {
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(authenticate);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authenticate);
        }*/
    }
}
