package com.cinarcorp.orderLine.repository;

import com.cinarcorp.orderLine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User getUserById(String id);
    User getUserByEmail(String email);


}
