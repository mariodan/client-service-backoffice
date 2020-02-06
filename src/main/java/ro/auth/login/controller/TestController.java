package ro.auth.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.auth.login.service.error.ErrorResponse;
import org.springframework.http.ResponseEntity;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.ok().build();
    }
}
