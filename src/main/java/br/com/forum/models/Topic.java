package br.com.forum.models;

import br.com.forum.models.enums.TopicState;
import br.com.forum.models.form.TopicForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder(toBuilder=true)
@AllArgsConstructor
public class Topic {

    @Id
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID uuid;
    private String tittle;
    private String message;

    @Column(name = "status")
    private TopicState status = TopicState.NOT_ANSWERED;

    @ManyToOne()
    @JoinColumn(name = "authorUuid")
    private User author;

    @ManyToOne()
    @JoinColumn(name = "courseUuid")
    private Course course;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    @JsonIgnore
    private List<Answer> answers = new ArrayList<>();

    @Column(name = "createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    public Topic() {
    }
}
