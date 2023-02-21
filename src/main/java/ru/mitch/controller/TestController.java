package ru.mitch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mitch.security.user.CurrentUserProvider;
import ru.mitch.security.user.UserProfile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test() {
        UserProfile user = CurrentUserProvider.getUser();
        return ResponseEntity.ok(user.getId().toString());
    }

}
