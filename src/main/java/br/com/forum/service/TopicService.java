package br.com.forum.service;

import br.com.forum.models.Course;
import br.com.forum.models.Topic;
import br.com.forum.models.User;
import br.com.forum.models.form.TopicForm;
import br.com.forum.repository.ITopicRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class TopicService {

    @Autowired
    private ITopicRepository topicRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    public Topic findByTittle(String tittle) throws Exception {
        try{
            Optional<Topic> topic = topicRepository.findByTittle(tittle);
            if(topic.isPresent()){
                return topic.get();
            }
            return null;
        }catch(Exception ex){
            log.error(String.format("Error: %s", ex.getMessage()));
            throw new Exception(ex.getMessage());
        }

    }

    public Topic saveTopic(Topic topic) throws Exception {
        try{
            return topicRepository.save(topic);
        }catch(Exception ex){
            log.error(String.format("Error: %s", ex.getMessage()));
            throw new Exception(ex.getMessage());
        }
    }

    public Topic buildTopic(TopicForm topicForm) throws Exception {
        try{
            Course course = courseService.findByUuid(UUID.fromString(topicForm.getCourseUuid()));
            User user = userService.findByUuid(UUID.fromString(topicForm.getAuthorUuid()));

            return new Topic()
                    .toBuilder()
                    .uuid(UUID.randomUUID())
                    .tittle(topicForm.getTittle())
                    .message(topicForm.getMessage())
                    .course(course)
                    .author(user)
                    .build();

        }catch(Exception ex){
            log.error(String.format("Error: %s", ex.getMessage()));
            throw new Exception(ex.getMessage());
        }
    }

}
