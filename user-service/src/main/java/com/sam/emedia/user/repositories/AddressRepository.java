package com.sam.emedia.user.repositories;

import com.sam.emedia.user.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    public List<Address> getAddressesByUserId(int userId);

    @Query("SELECT address from Address address WHERE address.user.id = :userId AND address.defaultAddress = true")
    public Address getUserDefaultAddress(@Param("userId") int userId);
    public Address getAddressById(int id);
}
