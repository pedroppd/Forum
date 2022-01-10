package br.com.forum.repository;

import br.com.forum.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {

    public Optional<User> findByUuid(UUID uuid);
}
