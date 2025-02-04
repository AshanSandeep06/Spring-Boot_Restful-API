package lk.epic.restfulAPI.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    @GetMapping
    public String callDemoMethod() {
        return "Hello from demo";
    }
}
