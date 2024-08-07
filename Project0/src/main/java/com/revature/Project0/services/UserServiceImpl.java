package com.revature.Project0.services;

import com.revature.Project0.models.Cat;
import com.revature.Project0.models.User;
import com.revature.Project0.repositories.CatRepo;
import com.revature.Project0.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo ur;
    @Autowired
    CatRepo cr;

    @Override
    public User signup(User u) {
        if(ur.userExists(u.getUsername()) == 0 && u.getPassword().length() > 4 && !u.getUsername().isEmpty()) {
            return ur.save(u);
        }
        else
            return null;
    }

    @Override
    public Optional<User> login(User u) {
        if(!u.getUsername().isEmpty() && u.getPassword().length() > 4) {
            return ur.findByUsernameAndPassword(u.getUsername(), u.getPassword());
        }
        else
            return Optional.empty();
    }

    @Override
    public List<Cat> getUserCats(int user_id) {
        return cr.findByOwnerId(user_id);
    }

    @Override
    public List<User> getAllUsers() {
        return ur.findAll();
    }

    @Override
    public Optional<User> getUser(int user_id) {
        return ur.findById(user_id);
    }

    public Integer usernameExists(String username){
        return ur.userExists(username);
    }
    public Integer idExists(Integer user_id){
        return ur.idExists(user_id);
    }
}