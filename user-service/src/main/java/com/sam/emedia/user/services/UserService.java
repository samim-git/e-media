package com.sam.emedia.user.services;

import com.sam.emedia.user.entities.Address;
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
    public ResponseObject saveAddress(Address address, int userId);
    public ResponseObject getAddress(int userId, int addressId);
    public ResponseObject getAddresses(int userId);
    public ResponseObject getDefaultAddress(int userId);

    public ResponseObject changeDefaultAddress(int userId, int newDefaultAddressId);
    public ResponseObject deleteAddress(int addressId);

}
