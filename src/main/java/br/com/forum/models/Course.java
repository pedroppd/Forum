package br.com.forum.models;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
public class Course {

    @Id
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
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
