package br.com.forum.service;

import br.com.forum.models.User;
import br.com.forum.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;


    public User findByUuid(UUID uuid){
        Optional<User> user = this.userRepository.findByUuid(uuid);
        return user.orElse(null);
    }

}
