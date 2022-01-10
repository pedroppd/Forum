package br.com.forum.controllers;

import br.com.forum.models.Course;
import br.com.forum.models.Topic;
import br.com.forum.models.dto.TopicDto;
import br.com.forum.models.form.TopicForm;
import br.com.forum.repository.ITopicRepository;
import br.com.forum.service.CourseService;
import br.com.forum.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j()
@RestController()
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private ITopicRepository topicRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getTopics(@RequestParam(value = "tittle", required = false) String tittle) {
        UUID tid = UUID.randomUUID();
        log.info("Starting getTopics endpoint", tid);
        try{
            if(tittle != null){
                Topic topic = topicService.findByTittle(tittle);
                if(topic != null){
                    return ResponseEntity.ok().body(new TopicDto(topic));
                }
                return ResponseEntity.ok().body(String.format("Topic with tittle '%s' not found", tittle));
            }
            return ResponseEntity.ok().body(new TopicDto().converter(topicRepository.findAll()));
        }catch(Exception ex){
            log.error(String.format("Error: %s", ex.getMessage()), tid);
            return ResponseEntity.status(500).body(new Object[]{"Internal server error", tid});
        }
    }

    @PostMapping
    public ResponseEntity<?> saveTopic(@RequestBody TopicForm topicForm ){
        UUID tid = UUID.randomUUID();
        log.info("Stating getTopics saveTopic", tid);
        try{
            Topic topicBuild = topicService.buildTopic(topicForm);
            Topic topic = topicService.saveTopic(topicBuild);
            return ResponseEntity.status(201).body(new TopicDto(topic));
        }catch (Exception ex){
            log.error(String.format("Error: %s", ex.getMessage()), tid);
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}
