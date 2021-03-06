package br.com.forum.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Answer {

    @Id
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID uuid;
    private String message;

    @ManyToOne()
    @JoinColumn()
    private Topic topic;

    @Column(name = "createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt = LocalDateTime.now();

    @ManyToOne()
    @JoinColumn(name = "authorUuid")
    private User author;

    private Boolean solved = false;

    public Answer() {

    }
}
