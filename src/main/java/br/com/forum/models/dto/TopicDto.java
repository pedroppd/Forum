package br.com.forum.models.dto;


import br.com.forum.models.Course;
import br.com.forum.models.Topic;
import br.com.forum.models.enums.TopicState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TopicDto {

    private UUID uuid;
    private String tittle;
    private String message;
    private LocalDateTime creationDate;
    private TopicState status;


    public TopicDto(Topic topic){
        this.setUuid(topic.getUuid());
        this.setTittle(topic.getTittle());
        this.setMessage(topic.getMessage());
        this.setStatus(topic.getStatus());
        this.setCreationDate(topic.getCreationDate());
    }

    public List<TopicDto> converter(List<Topic> topics){
        return topics.stream().map(TopicDto::new).collect(Collectors.toList());
    }
}
