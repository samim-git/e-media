package com.sam.emedia.user.services;

import com.sam.emedia.user.components.JWTComponents;
import com.sam.emedia.user.entities.Address;
import com.sam.emedia.user.entities.User;
import com.sam.emedia.user.models.ResponseObject;
import com.sam.emedia.user.models.UserLogin;
import com.sam.emedia.user.models.UserResponse;
import com.sam.emedia.user.repositories.AddressRepository;
import com.sam.emedia.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    private final JWTComponents jwt;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @Override
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


    @Override
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

    @Override
    public ResponseObject saveAddress(Address address, int userId) {
        System.out.println("userId-----: "+ userId);
        User user = userRepository.findById(userId);
        address.setUser(user);
        if(address.isDefaultAddress()) {
            removeDefaultAddress(userId);
        }
        return ResponseObject.getSuccess(addressRepository.save(address));
    }

    @Override
    public ResponseObject getAddress(int userId, int addressId) {
        return null;
    }

    @Override
    public ResponseObject getAddresses(int userId) {
        return ResponseObject.getSuccess(addressRepository.getAddressesByUserId(userId));
    }

    @Override
    public ResponseObject getDefaultAddress(int userId) {
        return ResponseObject.getSuccess(addressRepository.getUserDefaultAddress(userId));
    }

    @Override
    public ResponseObject changeDefaultAddress(int userId, int newDefaultAddressId) {
        removeDefaultAddress(userId);
        Address newDefault = addressRepository.getAddressById(newDefaultAddressId);
        newDefault.setDefaultAddress(true);
        return ResponseObject.getSuccess(newDefault);
    }

    @Override
    public ResponseObject deleteAddress(int addressId) {
        Address address = addressRepository.getAddressById(addressId);
        if(address.isDefaultAddress())
            return ResponseObject.getResponse(address,"Cannot delete default address",false);
        else {
            addressRepository.delete(address);
        }
        return ResponseObject.getSuccess("address deleted");
    }

    private void removeDefaultAddress(int userId) {
        Address currentDefault = addressRepository.getUserDefaultAddress(userId);
        if(currentDefault != null) {
            currentDefault.setDefaultAddress(false);
            addressRepository.save(currentDefault);
        }
    }

}
