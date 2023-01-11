package com.sam.emedia.user.services;

import com.sam.emedia.user.components.JWTComponents;
import com.sam.emedia.user.entities.User;
import com.sam.emedia.user.models.ResponseObject;
import com.sam.emedia.user.models.UserLogin;
import com.sam.emedia.user.models.UserResponse;
import com.sam.emedia.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService{

    private final PasswordEncoder passwordEncoder;
    private final JWTComponents jwt;
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }


    public User getUserById(int userId) {
        return userRepository.findById(userId);
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public ResponseObject registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        ResponseObject responseObject = new ResponseObject();
        try {
            User eUser = userRepository.findUserByPhoneOrEmail(user.getPhone(),user.getEmail());
            if(eUser != null){
                responseObject.setMessage("User with this Phone or Email Address already exist");
                return responseObject;
            }
            responseObject.setData(UserResponse.parsUser(userRepository.save(user)));
            responseObject.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            responseObject.setSuccess(false);
            responseObject.setData(null);
        }
        return responseObject;
    }


    public ResponseObject loginUser(UserLogin userLogin) {
        ResponseObject responseObject = new ResponseObject();
        User loginUser = userRepository.findUserByEmail(userLogin.getEmail());;
        if (loginUser != null && passwordEncoder.matches(userLogin.getPassword(), loginUser.getPassword())) {
            HashMap<String,Object> tokenMap = jwt.getToken(loginUser);
            HashMap<String,Object> dataMap = new HashMap<>();
            dataMap.put("user", UserResponse.parsUser(loginUser));
            dataMap.put("token",tokenMap);
            responseObject.setData(dataMap);
            responseObject.setSuccess(true);
            responseObject.setMessage("Login success");
        } else {
            responseObject.setData(null);
            responseObject.setSuccess(false);
            responseObject.setMessage("Login failed");
        }
        return responseObject;
    }
}
