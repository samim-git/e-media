package com.sam.emedia.user.services;

import com.sam.emedia.user.entities.User;
import com.sam.emedia.user.models.ResponseObject;
import com.sam.emedia.user.models.UserLogin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User getUserById(int userId);
    public List<User> getUsers();
    public ResponseObject registerUser(User user);
    public ResponseObject loginUser(UserLogin userLogin);

}
