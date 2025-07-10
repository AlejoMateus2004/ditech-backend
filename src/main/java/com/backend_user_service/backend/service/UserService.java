package com.backend_user_service.backend.service;

import com.backend_user_service.backend.model.AppUser;
import com.backend_user_service.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public AppUser saveUser(AppUser user){
        return userRepository.save(user);
    }

    @Transactional
    public String deleteUser(AppUser user){
        userRepository.delete(user);
        return "User deleted successfully";
    }

    @Transactional(readOnly = true)
    public Optional<AppUser> getUserById(String id) {
        return userRepository.findById(Long.valueOf(id));
    }

    @Transactional(readOnly = true)
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }


}
