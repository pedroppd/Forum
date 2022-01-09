package br.com.forum.controllers;

import br.com.forum.models.Topic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController(value = "/topic")
public class TopicController {

    @PostMapping()
    public ResponseEntity<Topic> saveTopic(){
        return null;
    }
}
