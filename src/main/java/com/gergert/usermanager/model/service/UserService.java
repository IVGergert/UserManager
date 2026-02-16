package com.gergert.usermanager.model.service;

import com.gergert.usermanager.model.entity.Role;
import com.gergert.usermanager.model.entity.User;
import com.gergert.usermanager.model.exception.ServiceException;

import java.util.List;

public interface UserService {
    User createUser(User user) throws ServiceException;

    List<User> findAllUsers();
    User findUserById(Long id) throws ServiceException;

    User updateUser(Long id, User user) throws ServiceException;
    User changeUserRole(Long id, Role role) throws ServiceException;

    void deleteUser(Long id) throws ServiceException;
}
