package br.com.forum.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Answer {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String message;

    @ManyToOne()
    @JoinColumn()
    private Topic topic;

    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne()
    @JoinColumn(name = "authorUuid")
    private User author;

    private Boolean solved = false;

    public Answer() {

    }
}
