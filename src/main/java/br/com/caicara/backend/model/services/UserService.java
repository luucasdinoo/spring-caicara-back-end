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

    @Transactional
    public void deleteUserById(UUID id){
        userRepository.deleteById(id);
    }

    @Transactional
    public User updatePassword(UUID id, String currentPassword, String newPassword, String confirmPassword){
        if (!newPassword.equals(confirmPassword))
            throw new RuntimeException("Nova senha não confere com confirmação de senha");

        User user = findUserById(id);
        if (!currentPassword.equals(user.getPassword()))
            throw new RuntimeException("Sua senha não confere");

        user.setPassword(newPassword);
        return user;
    }
}
