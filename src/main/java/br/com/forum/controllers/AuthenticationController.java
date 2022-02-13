package br.com.forum.controllers;

import br.com.forum.config.validation.security.TokenService;
import br.com.forum.models.Topic;
import br.com.forum.models.dto.TokenDto;
import br.com.forum.models.dto.TopicDto;
import br.com.forum.models.form.AuthForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j()
@RestController()
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid AuthForm authForm) {
        UUID tid = UUID.randomUUID();
        try{
            Authentication auth = authenticationManager.authenticate(authForm.converter());
            String token = tokenService.generateTokenJwt(auth);
            return ResponseEntity.ok().body(new TokenDto(token, "Bearer"));
        }catch(AuthenticationException ex) {
            log.error("Error to try authenticate:" +  ex.getMessage(), tid);
            return ResponseEntity.badRequest().build();
        }
    }
}
