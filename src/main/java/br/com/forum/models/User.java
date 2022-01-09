package br.com.forum.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID uuid;
    private String name;
    private String email;
    private String password;

    public User() {

    }
}
