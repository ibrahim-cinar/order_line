package com.cinarcorp.productSupply.repository;

import com.cinarcorp.productSupply.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveUser(){
        User user = User.builder().
                firstName("çınar").
                lastName("ssssss").
                address("TÜRKİYE").
                email("sssss@outlook.com")
                .order(new ArrayList<>())
                .build();
        userRepository.save(user);

    }
    @Test
    public void testFindAllUsers() {
        userRepository.findAll().forEach(user -> {
            System.out.println("User ID: " + user.getId());
            System.out.println("First Name: " + user.getFirstName());
            System.out.println("Last Name: " + user.getLastName());
            System.out.println("Address: " + user.getAddress());
            System.out.println("Email: " + user.getEmail());

            // Eğer kullanıcının siparişleri de gerekiyorsa, ayrıca siparişleri de yazdırabilirsiniz.
            user.getOrder().forEach(order -> {
                System.out.println("Order ID: " + order.getId());
                // Diğer sipariş bilgilerini de yazdırabilirsiniz.
            });

            System.out.println("------------------------------");
        });
    }

    @Test
    public void getUserById(){
        var user = userRepository.getUserById("64aed5d5-e239-42bd-8a00-902ceba8ff66");
        System.out.println(user);

    }
    @Test
    public void getUserByEmail(){
        var user = userRepository.getUserByEmail("a-a@outlook.com");
        System.out.println(user);

    }
    /*@Test
    public void getUserByEmailAnd_CompleteTrue(){
        var user = userRepository.getUserByEmailAnd_CompleteTrue("a-a@outlook.com");
        System.out.println(user);

    }*/



}