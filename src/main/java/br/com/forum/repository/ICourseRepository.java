package br.com.forum.repository;

import br.com.forum.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ICourseRepository extends JpaRepository<Course, UUID> {

    public Optional<Course> findByUuid(UUID uuid);
}
