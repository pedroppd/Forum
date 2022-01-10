package br.com.forum.service;

import br.com.forum.models.Topic;;
import br.com.forum.repository.ITopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class TopicService {

    @Autowired
    private ITopicRepository topicRepository;

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

}
