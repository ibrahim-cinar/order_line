package com.cinarcorp.orderLine.service;

import com.cinarcorp.orderLine.dto.*;
import com.cinarcorp.orderLine.dto.converter.UserDtoConverter;
import com.cinarcorp.orderLine.dto.converter.UserOrderedDtoConverter;
import com.cinarcorp.orderLine.exception.EmailAlreadyExistsException;
import com.cinarcorp.orderLine.exception.OrderNotFoundException;
import com.cinarcorp.orderLine.exception.UserNotFoundException;
import com.cinarcorp.orderLine.model.Order;
import com.cinarcorp.orderLine.model.User;
import com.cinarcorp.orderLine.repository.UserRepository;
import org.springframework.stereotype.Service;

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
        var user = findUserByEmail(email);

        if (user == null) {
            // Kullanıcı bulunamadı, 404 Not Found yanıtı döndürün.
            // Örneğin, bir özel istisna sınıfı kullanabilirsiniz.
            throw new UserNotFoundException("Kullanıcı bulunamadı: " + email);
        }
        return userOrderedDtoConverter.convert(findUserByEmail(email));
    }

    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(userDtoConverter::convertToUserDto).collect(Collectors.toList());
    }

    public UserOrderedDto getUserOrderByEmailAndIsComplete(String emailId, boolean isComplete) {
        var user = userRepository.getUserByEmail(emailId);

        if (user == null) {
            // Kullanıcı bulunamadı, 404 Not Found yanıtı döndürün.
            // Örneğin, bir özel istisna sınıfı kullanabilirsiniz.
            throw new UserNotFoundException("Kullanıcı bulunamadı: " + emailId);
        }

        if (user.getOrder() == null || user.getOrder().isEmpty()) {
            // Kullanıcının siparişleri yok, 404 Not Found yanıtı döndürün.
            // Örneğin, bir özel istisna sınıfı kullanabilirsiniz.
            throw new OrderNotFoundException("Kullanıcının siparişleri bulunamadı: " + emailId);
        }

        List<Order> filteredOrders = user.getOrder().stream()
                .filter(order -> order.isComplete() == isComplete)
                .collect(Collectors.toList());

        if (filteredOrders.isEmpty()) {
            // Filtrelenmiş sipariş yok, 404 Not Found yanıtı döndürün.
            // Örneğin, bir özel istisna sınıfı kullanabilirsiniz.
            throw new OrderNotFoundException("Kullanıcının belirtilen durumda siparişleri bulunamadı: " + emailId);
        }

        User userOrdered = new User(user.getFirstName(), user.getLastName(),
                user.getAddress(), user.getEmail(), filteredOrders);

        return userOrderedDtoConverter.convert(userOrdered);
    }

    public UserOrderedDto getUserOrderByIdAndIsComplete(String id, boolean isComplete) {
        var user = userRepository.getUserById(id);
        if (user == null) {
            // Kullanıcı bulunamadı, 404 Not Found yanıtı döndürün.
            // Örneğin, bir özel istisna sınıfı kullanabilirsiniz.
            throw new UserNotFoundException("Kullanıcı bulunamadı: " + id);
        }

        if (user.getOrder() == null || user.getOrder().isEmpty()) {
            // Kullanıcının siparişleri yok, 404 Not Found yanıtı döndürün.
            // Örneğin, bir özel istisna sınıfı kullanabilirsiniz.
            throw new OrderNotFoundException("Kullanıcının siparişleri bulunamadı: " +  user.getFirstName() + user.getLastName());
        }
        List<Order> filteredOrders = user.getOrder().stream()
                .filter(order -> order.isComplete() == isComplete)
                .collect(Collectors.toList());
        if (filteredOrders.isEmpty()) {
            // Filtrelenmiş sipariş yok, 404 Not Found yanıtı döndürün.
            // Örneğin, bir özel istisna sınıfı kullanabilirsiniz.
            throw new OrderNotFoundException("Kullanıcının belirtilen durumda siparişleri bulunamadı: " + user.getFirstName() + user.getLastName());
        }

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

