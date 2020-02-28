package ro.enel.backoffice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/v1/users")
public class UserProfileController {

    @GetMapping("/info")
    public ResponseEntity userInfo() {
        return ResponseEntity.ok().build();
    }
}
