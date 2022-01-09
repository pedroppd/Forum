package br.com.forum.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
public class Course {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String name;
    private String category;

    public Course(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Course() {

    }
}
