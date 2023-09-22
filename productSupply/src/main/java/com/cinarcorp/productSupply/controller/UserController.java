package com.cinarcorp.productSupply.controller;

import com.cinarcorp.productSupply.dto.CreateUserOrderRequest;
import com.cinarcorp.productSupply.dto.UpdateUserRequest;
import com.cinarcorp.productSupply.dto.UserDto;
import com.cinarcorp.productSupply.dto.UserOrderedDto;
import com.cinarcorp.productSupply.exception.UserNotFoundException;
import com.cinarcorp.productSupply.model.User;
import com.cinarcorp.productSupply.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserOrderedDto> getUserById(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }


    @GetMapping("/userMail/{email}")
    public ResponseEntity<UserOrderedDto> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/{email}/{isComplete}")
    public ResponseEntity<UserOrderedDto>getUserOrderByEmailAndIsComplete(@PathVariable String email, @PathVariable boolean isComplete){
        return ResponseEntity.ok(userService.getUserOrderByEmailAndIsComplete(email,isComplete));
    }
    @GetMapping("/search/{id}/{isComplete}")
    public ResponseEntity<UserOrderedDto>getUserOrderByIdAndIsComplete(@PathVariable String id,@PathVariable boolean isComplete){
        return ResponseEntity.ok(userService.getUserOrderByIdAndIsComplete(id,isComplete));
    }
    /*@PostMapping("/createUserOrder")
    public  ResponseEntity<UserOrderedDto> createNewUserOrder(@RequestBody CreateUserOrderRequest createUserOrderRequest){
        return ResponseEntity.ok(userService.createNewUserOrder(createUserOrderRequest));
    }*/
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto>updateUser(@RequestBody UpdateUserRequest request,@PathVariable String id){
        return ResponseEntity.ok(userService.updateUser(request,id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/delete/{id}/{isComplete}")
    public ResponseEntity<Void> deleteUserIsCompleteOrder(@PathVariable String id,@PathVariable boolean isComplete) {
        userService.deleteUserIsCompleteOrder(id,isComplete);
        return ResponseEntity.ok().build();

    }
}
