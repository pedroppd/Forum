package br.com.forum.controllers;

import br.com.forum.models.Topic;
import br.com.forum.models.dto.TopicDto;
import br.com.forum.models.form.TopicForm;
import br.com.forum.models.form.TopicUpdateForm;
import br.com.forum.repository.ITopicRepository;
import br.com.forum.service.CourseService;
import br.com.forum.service.TopicService;
import br.com.forum.service.UserService;
import br.com.forum.utils.TopicUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
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
    @Cacheable(value = "getTopics")
    public ResponseEntity<Page<TopicDto>> getTopics(@RequestParam(value = "tittle", required = false) String tittle,
                                                    @PageableDefault(sort = "uuid", direction = Sort.Direction.DESC) Pageable pagination) {
        UUID tid = UUID.randomUUID();
        log.info("Starting getTopics endpoint", tid);
            if(TopicUtils.hasValue(tittle)){
                Page<Topic> topic = topicService.findByTittle(tittle, pagination);
                if(topic != null){
                    return ResponseEntity.ok().body(TopicDto.converter(topic));
                }
                return ResponseEntity.notFound().build();
            }
            Page<Topic> result = topicRepository.findAll(pagination);
            return ResponseEntity.ok().body(TopicDto.converter(result));
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<TopicDto> getTopicsByUuid(@PathVariable String uuid) {
        UUID tid = UUID.randomUUID();
        log.info("Starting getTopicsByUuid endpoint", tid);
        Topic topic = topicService.findByUuid(UUID.fromString(uuid));
        if(topic != null){
            return ResponseEntity.ok().body(new TopicDto(topic));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @CacheEvict(value = "getTopics", allEntries = true)
    public ResponseEntity<TopicDto> saveTopic(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriBuilder) throws Exception {
        UUID tid = UUID.randomUUID();
        log.info("Starting saveTopic endpoint", tid);
        Topic topicBuild = topicForm.converter(courseService, userService);
        Topic topic = topicService.saveTopic(topicBuild);
        URI uri = uriBuilder.path("/topic/{uuid}").buildAndExpand(topic.getUuid()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @PutMapping(value = "/{uuid}")
    @Transactional
    @CacheEvict(value = "getTopics", allEntries = true)
    public ResponseEntity<TopicDto> upadateTopic(@PathVariable String uuid, @RequestBody @Valid TopicUpdateForm topicForm)  {
        UUID tid = UUID.randomUUID();
        log.info("Starting upadateTopic endpoint", tid);
        Topic topic = topicForm.update(UUID.fromString(uuid), topicService);
        if(topic != null){
            topicService.saveTopic(topic);
            return ResponseEntity.ok().body(new TopicDto(topic));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{uuid}")
    @CacheEvict(value = "getTopics", allEntries = true)
    public ResponseEntity<TopicDto> deleteTopic(@PathVariable String uuid)  {
        UUID tid = UUID.randomUUID();
        log.info("Starting deleteTopic endpoint", tid);
        Topic topic = topicService.findByUuid(UUID.fromString(uuid));
        if(topic != null){
            topicService.deleteTopic(topic);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
