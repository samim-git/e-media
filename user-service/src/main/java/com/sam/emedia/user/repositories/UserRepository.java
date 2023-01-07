package com.sam.emedia.user.repositories;

import com.sam.emedia.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(int id);

    User findUserByPhoneOrEmail(String phone, String email);
    User findUserByEmail(String email);
}
