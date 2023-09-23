package com.cinarcorp.productSupply.service;

import com.cinarcorp.productSupply.dto.CreateUserOrderRequest;
import com.cinarcorp.productSupply.dto.OrderUserDto;
import com.cinarcorp.productSupply.dto.converter.OrderDtoConverter;
import com.cinarcorp.productSupply.dto.converter.OrderUserDtoConverter;
import com.cinarcorp.productSupply.model.Order;
import com.cinarcorp.productSupply.model.Product;
import com.cinarcorp.productSupply.model.User;
import com.cinarcorp.productSupply.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDtoConverter orderDtoConverter;
    private final UserService userService;
    private final ProductService productService;
    private final  OrderUserDtoConverter orderUserDtoConverter;

    public OrderService(OrderRepository orderRepository,
                        OrderDtoConverter orderDtoConverter,
                        UserService userService, ProductService productService,
                        OrderUserDtoConverter orderUserDtoConverter) {
        this.orderRepository = orderRepository;
        this.orderDtoConverter =  orderDtoConverter;
        this.userService = userService;
        this.productService = productService;
        this.orderUserDtoConverter = orderUserDtoConverter;
    }


    /*public OrderUserDto createNewOrder(CreateUserOrderRequest createUserOrderRequest) {
        User user = User.builder()
                .firstName(createUserOrderRequest.getFirstName())
                .lastName(createUserOrderRequest.getLastName())
                .address(createUserOrderRequest.getAddress())
                .email(createUserOrderRequest.getEmail())
                .build();

        Order order = new Order(
                user
                ,createUserOrderRequest.getOrder().getTotalPaid()
                ,createUserOrderRequest.getOrder().getPiece()
                ,LocalDateTime.now()
                ,createUserOrderRequest.getOrder().isComplete()

                );
        List<Product> products = createUserOrderRequest.getOrder().getProduct()
                .stream()
                .map(productRequest -> {
                    Product product = Product.builder()
                            .productName(productRequest.getProductName())
                            .description(productRequest.getDescription())
                            .price(productRequest.getPrice())
                            .build();
                    product.setOrder(order);
                    return product;
                })
                .collect(Collectors.toList());

        return orderUserDtoConverter.convert(orderRepository.save(order));
    }*/
    public OrderUserDto createNewOrder(CreateUserOrderRequest createUserOrderRequest) {
        // Kullanıcıyı oluşturun
        User user = createUserFromRequest(createUserOrderRequest);

        // Siparişi oluşturun
        Order order = createOrderFromRequest(createUserOrderRequest, user);

        // Ürünleri ekleyin
        List<Product> products = createProductsFromRequest(createUserOrderRequest, order);

        // Siparişi kaydedin ve dönüştürün
        return orderUserDtoConverter.convert(orderRepository.save(order));
    }

    private User createUserFromRequest(CreateUserOrderRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .address(request.getAddress())
                .email(request.getEmail())
                .build();
    }

    private Order createOrderFromRequest(CreateUserOrderRequest request, User user) {
        return new Order(
                user,
                request.getOrder().getTotalPaid(),
                request.getOrder().getPiece(),
                LocalDateTime.now(),
                request.getOrder().isComplete()
        );
    }

    private List<Product> createProductsFromRequest(CreateUserOrderRequest request, Order order) {
        return request.getOrder().getProduct()
                .stream()
                .map(productRequest -> {
                    Product product = Product.builder()
                            .productName(productRequest.getProductName())
                            .description(productRequest.getDescription())
                            .price(productRequest.getPrice())
                            .order(order)
                            .build();
                    order.addProduct(product);
                    return product;
                })
                .collect(Collectors.toList());
    }

    public void deleteOrder(String id) {
        var order = orderRepository.getOrderById(id);

        if (order != null) {
            if (order.getProduct() != null) {
                for (Product product : order.getProduct()) {
                    productService.deleteProduct(product.getId());
                }
            }
            orderRepository.deleteById(order.getId());
        }
    }

}
