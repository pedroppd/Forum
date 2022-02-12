package br.com.forum.service;

import br.com.forum.models.Topic;;
import br.com.forum.repository.ITopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class TopicService {

    @Autowired
    private ITopicRepository topicRepository;

    public Page<Topic> findByTittle(String tittle, Pageable pagination) {
        return topicRepository.findByTittle(tittle, pagination);
    }

    public Topic findByUuid(UUID uuid) {
        return topicRepository.findByUuid(uuid).orElse(null);
    }

    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public void deleteTopic(Topic topic) {
        topic.setDeletedAt(LocalDateTime.now());
        topic.setUpdatedAt(LocalDateTime.now());
        this.saveTopic(topic);
    }

}
