package com.test.demo.Services;

import com.test.demo.models.User;
import com.test.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService  {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public UserService() {
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Transactional
    public User getUserWithRoles(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        Hibernate.initialize(user.getRole());
        return user;
    }

    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
    @Transactional
    public User registerUser(@Valid User user) {
        // Vérifier l'unicité de l'e-mail
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("L'e-mail est déjà utilisé par un autre utilisateur.");
        }

        // Enregistrer l'utilisateur
        return userRepository.save(user);
    }
    public User findUserById(Long id){
        return this.userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsers() {
        // You can implement this method to retrieve users from your repository or return an empty list
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser);
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


}
