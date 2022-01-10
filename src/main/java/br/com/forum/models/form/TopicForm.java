package br.com.forum.models.form;

import br.com.forum.models.Course;
import br.com.forum.models.Topic;
import br.com.forum.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TopicForm {
    private String tittle;
    private String message;
    //mocado temporariamente
    private String authorUuid = "965aab64-2d27-4391-ad95-3fc3736d1045";
    private String courseUuid;
}
