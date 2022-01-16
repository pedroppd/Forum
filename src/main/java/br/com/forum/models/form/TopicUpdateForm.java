package br.com.forum.models.form;

import br.com.forum.models.Topic;
import br.com.forum.service.TopicService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Setter
public class TopicUpdateForm {
    @NotNull
    @NotEmpty
    private String tittle;
    @NotNull @NotEmpty
    private String message;

    public Topic update(UUID uuid, TopicService topicService) {
        return topicService.findByUuid(uuid)
                .toBuilder()
                .message(this.getMessage())
                .tittle(this.getTittle())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
