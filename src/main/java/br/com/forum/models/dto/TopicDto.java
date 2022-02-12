package br.com.forum.models.dto;


import br.com.forum.models.Course;
import br.com.forum.models.Topic;
import br.com.forum.models.enums.TopicState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private TopicState status;


    public TopicDto(Topic topic){
        this.setUuid(topic.getUuid());
        this.setTittle(topic.getTittle());
        this.setMessage(topic.getMessage());
        this.setStatus(topic.getStatus());
        this.setCreatedAt(topic.getCreatedAt());
        this.setUpdatedAt(topic.getUpdatedAt());
        this.setDeletedAt(topic.getDeletedAt());
    }

    public TopicDto() {

    }

    public static Page<TopicDto> converter(Page<Topic> topics){
        return topics.map(TopicDto::new);
    }
}
