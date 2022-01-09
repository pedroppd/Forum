package br.com.forum.models;

import br.com.forum.models.enums.TopicState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Topic {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String tittle;
    private String message;
    private LocalDateTime creationDate = LocalDateTime.now();
    private TopicState status = TopicState.NOT_ANSWERED;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private User author;

    @ManyToOne()
    @JoinColumn(name = "courseUuid")
    private Course course;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    @JsonIgnore
    private List<Answer> answers = new ArrayList<>();

    public Topic() {

    }
}
