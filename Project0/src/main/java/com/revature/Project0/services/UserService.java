package com.revature.Project0.services;

import com.revature.Project0.models.Cat;
import com.revature.Project0.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User signup(User u);
    public Optional<User> login(User u);
    public List<Cat> getUserCats(int user_id);
    public List<User> getAllUsers();
    public Optional<User> getUser(int user_id);
}