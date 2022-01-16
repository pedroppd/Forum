package br.com.forum.service;

import br.com.forum.models.Topic;;
import br.com.forum.repository.ITopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class TopicService {

    @Autowired
    private ITopicRepository topicRepository;

    public Topic findByTittle(String tittle) {
        return topicRepository.findByTittle(tittle).orElse(null);
    }

    public Topic findByUuid(UUID uuid) {
        return topicRepository.findByUuid(uuid).orElse(null);
    }

    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

}
