package br.com.caicara.backend.model.services;

import br.com.caicara.backend.model.entities.user.User;
import br.com.caicara.backend.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(User user){
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUserById(UUID id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("Id= '%s' não encontrado", id)));
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}
