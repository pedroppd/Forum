package br.com.forum.controllers;

import br.com.forum.models.Course;
import br.com.forum.models.Topic;
import br.com.forum.models.dto.TopicDto;
import br.com.forum.models.form.TopicForm;
import br.com.forum.repository.ITopicRepository;
import br.com.forum.service.CourseService;
import br.com.forum.service.TopicService;
import br.com.forum.service.UserService;
import br.com.forum.utils.TopicUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
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

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<TopicDto>> getTopics(@RequestParam(value = "tittle", required = false) String tittle) throws Exception {
        UUID tid = UUID.randomUUID();
        log.info("Starting getTopics endpoint", tid);
            if(TopicUtils.hasValue(tittle)){
                Topic topic = topicService.findByTittle(tittle);
                if(topic != null){
                    return ResponseEntity.ok().body(Arrays.asList(new TopicDto(topic)));
                }
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(new TopicDto().converter(topicRepository.findAll()));
    }

    @PostMapping
    public ResponseEntity<TopicDto> saveTopic(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriBuilder) throws Exception {
        UUID tid = UUID.randomUUID();
        log.info("Starting saveTopic endpoint", tid);
        Topic topicBuild = topicForm.converter(courseService, userService);
        Topic topic = topicService.saveTopic(topicBuild);
        URI uri = uriBuilder.path("/topic/{uuid}").buildAndExpand(topic.getUuid()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }
}
