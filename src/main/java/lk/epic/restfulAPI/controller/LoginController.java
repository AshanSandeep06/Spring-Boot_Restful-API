package lk.epic.restfulAPI.controller;

import lk.epic.restfulAPI.dto.LoginDTO;
import lk.epic.restfulAPI.service.LoginService;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    public ResponseUtil login(@RequestBody LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }
}
