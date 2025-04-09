package br.com.fiap.roardemo.service;

import br.com.fiap.roardemo.model.User;
import br.com.fiap.roardemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createOrUpdateUser(String id, String name, String email, String avatarUrl) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(name);
            user.setEmail(email);
            user.setAvatarUrl(avatarUrl);
            return userRepository.save(user);
        } else {
            User newUser = new User(id, name, email, avatarUrl);
            return userRepository.save(newUser);
        }
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
}