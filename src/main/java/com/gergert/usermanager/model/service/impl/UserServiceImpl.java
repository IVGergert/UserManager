package com.gergert.usermanager.model.service.impl;

import com.gergert.usermanager.model.entity.Role;
import com.gergert.usermanager.model.entity.User;
import com.gergert.usermanager.model.exception.ServiceException;
import com.gergert.usermanager.model.repository.UserRepository;
import com.gergert.usermanager.model.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(User user) throws ServiceException {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new ServiceException("User with this email already exists");
        }

        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) throws ServiceException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("User with " + id + " not found"));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User updateUser(Long id, User user) throws ServiceException {
        User existingUser = findUserById(id);

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());

        return userRepository.save(existingUser);
    }

    @Override
    public User changeUserRole(Long id, Role role) throws ServiceException {
        User user = findUserById(id);
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) throws ServiceException{
        if (!userRepository.existsById(id)){
            throw new ServiceException("Unable to delete: user not found");
        }

        userRepository.deleteById(id);
    }
}
