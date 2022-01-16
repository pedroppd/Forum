package br.com.forum.models.form;

import br.com.forum.models.Course;
import br.com.forum.models.Topic;
import br.com.forum.models.User;
import br.com.forum.service.CourseService;
import br.com.forum.service.UserService;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class TopicForm {
    @NotNull @NotEmpty
    private String tittle;
    @NotNull @NotEmpty
    private String message;
    //mocado temporariamente
    @NotNull @NotEmpty
    private String authorUuid = "965aab64-2d27-4391-ad95-3fc3736d1045";
    @NotNull @NotEmpty
    private String courseUuid;

    public Topic converter(CourseService courseService, UserService userService) {
                Course course = courseService.findByUuid(UUID.fromString(this.getCourseUuid()));
                User user = userService.findByUuid(UUID.fromString(this.getAuthorUuid()));
                return new Topic()
                        .toBuilder()
                        .uuid(UUID.randomUUID())
                        .tittle(this.getTittle())
                        .message(this.getMessage())
                        .course(course)
                        .author(user)
                        .build();
    }
}
