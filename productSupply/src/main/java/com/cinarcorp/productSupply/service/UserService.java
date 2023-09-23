package com.cinarcorp.productSupply.service;

import com.cinarcorp.productSupply.dto.*;
import com.cinarcorp.productSupply.dto.converter.UserDtoConverter;
import com.cinarcorp.productSupply.dto.converter.UserOrderedDtoConverter;
import com.cinarcorp.productSupply.exception.EmailAlreadyExistsException;
import com.cinarcorp.productSupply.exception.UserNotFoundException;
import com.cinarcorp.productSupply.model.Order;
import com.cinarcorp.productSupply.model.Product;
import com.cinarcorp.productSupply.model.User;
import com.cinarcorp.productSupply.repository.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final UserOrderedDtoConverter userOrderedDtoConverter;
    private final CreateUserOrderRequest createUserOrderRequest;


    public UserService(UserRepository userRepository,
                       UserDtoConverter userDtoConverter,
                       UserOrderedDtoConverter userOrderedDtoConverter,
                       CreateUserOrderRequest createUserOrderRequest) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.userOrderedDtoConverter = userOrderedDtoConverter;
        this.createUserOrderRequest = createUserOrderRequest;
    }

    protected User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("User could not find by id: " + id));

    }

    public UserOrderedDto getUserById(String userId) {
        return userOrderedDtoConverter.convert(findUserById(userId));
    }

    protected User findUserByEmail(String email) {
        try {
            return userRepository.getUserByEmail(email);
        } catch (Exception e) {
            return null;
        }
    }

    public UserOrderedDto getUserByEmail(String email) {
        return userOrderedDtoConverter.convert(findUserByEmail(email));
    }

    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(userDtoConverter::convertToUserDto).collect(Collectors.toList());
    }

    public UserOrderedDto getUserOrderByEmailAndIsComplete(String emailId, boolean isComplete) {
        var user = userRepository.getUserByEmail(emailId);
        if (user == null) {
            return null;
        }
        List<Order> filteredOrders = user.getOrder().stream()
                .filter(order -> order.isComplete() == isComplete)
                .collect(Collectors.toList());
        User userOrdered = new User();
// User userOrdered = new User(user.getFirstName()
             //                 ,user.getLastName(),user.getAddress(),user.getEmail(),filteredOrders);
        userOrdered.setFirstName(user.getFirstName());
        userOrdered.setLastName(user.getLastName());
        userOrdered.setAddress(user.getAddress());
        userOrdered.setEmail(user.getEmail());
        userOrdered.setOrder(filteredOrders);

        return userOrderedDtoConverter.convert(userOrdered);
    }


    public UserOrderedDto getUserOrderByIdAndIsComplete(String id, boolean isComplete) {
        var user = userRepository.getUserById(id);
        if (user == null) {
            return null;
        }
        List<Order> filteredOrders = user.getOrder().stream()
                .filter(order -> order.isComplete() == isComplete)
                .collect(Collectors.toList());

        User userOrdered = new User();

        userOrdered.setFirstName(user.getFirstName());
        userOrdered.setLastName(user.getLastName());
        userOrdered.setAddress(user.getAddress());
        userOrdered.setEmail(user.getEmail());
        userOrdered.setOrder(filteredOrders);

        return userOrderedDtoConverter.convert(userOrdered);
    }
   /* public UserOrderedDto createNewUserOrder(CreateUserOrderRequest createUserOrderRequest) {
        User user = User.builder()
                .firstName(createUserOrderRequest.getFirstName())
                .lastName(createUserOrderRequest.getLastName())
                .address(createUserOrderRequest.getAddress())
                .email(createUserOrderRequest.getEmail())
                .build();

        List<Order> orders = createUserOrderRequest.getOrder().
                .stream()
                .map(orderRequest -> {
                    Order order = Order.builder().user(user)
                            .product(orderRequest.getProduct()
                                    .stream()
                                    .map(productRequest -> Product.builder()
                                            .productName(productRequest.getProductName())
                                            .description(productRequest.getDescription())
                                            .price(productRequest.getPrice())
                                            .build())
                                    .collect(Collectors.toList()))
                            .totalPaid(orderRequest.getTotalPaid())
                            .piece(orderRequest.getPiece())
                            .isComplete(orderRequest.isComplete())
                            .orderDate(LocalDateTime.now()).build();
                    return order;
                })
                .collect(Collectors.toList());

        user.setOrder(orders);

        return userOrderedDtoConverter.convert(userRepository.save(user));
    }*/


    public UserDto updateUser(UpdateUserRequest request, String id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("Belirtilen kullanıcı bulunamadı.");
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAddress(request.getAddress());

        if (user.getEmail().equals(request.getEmail())) {
            user.setEmail(request.getEmail());
        } else throw new EmailAlreadyExistsException("Email Already taken");
        return userDtoConverter.convertToUserDto(userRepository.save(user));
    }

    public void deleteUser(String id) {

        var user = findUserById(id);

        userRepository.deleteById(user.getId());

    }
    public void deleteUserIsCompleteOrder(String id,boolean isComplete) {

        var user = findUserById(id);
        List<Order> filteredOrders = user.getOrder().stream()
                .filter(order -> order.isComplete() == isComplete)
                .collect(Collectors.toList());

        user.setOrder(filteredOrders);
        userRepository.deleteById(user.getId());

    }




}

