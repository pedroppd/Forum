package br.com.forum.controllers;

import br.com.forum.models.Topic;
import br.com.forum.models.dto.TopicDto;
import br.com.forum.models.form.AuthForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j()
@RestController()
@RequestMapping("/auth")
public class AuthenticationController {

    @PostMapping()
    public ResponseEntity<AuthForm> authenticate(@RequestBody @Valid AuthForm authForm) {
        UUID tid = UUID.randomUUID();
        log.info(authForm.getEmail() + "-" + authForm.getPassword(), tid);
        return null;
    }
}
