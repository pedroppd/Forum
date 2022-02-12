package br.com.forum.repository;

import br.com.forum.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, UUID> {

     Page<Topic> findByTittle(String tittle, Pageable pagination);
     Optional<Topic> findByUuid(UUID uuid);
}
