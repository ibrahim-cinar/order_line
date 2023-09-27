package com.cinarcorp.orderLine.service;

import com.cinarcorp.orderLine.dto.CreateUserOrderRequest;
import com.cinarcorp.orderLine.dto.OrderDto;
import com.cinarcorp.orderLine.dto.OrderUserDto;
import com.cinarcorp.orderLine.dto.converter.OrderDtoConverter;
import com.cinarcorp.orderLine.dto.converter.OrderUserDtoConverter;
import com.cinarcorp.orderLine.exception.OrderNotFoundException;
import com.cinarcorp.orderLine.model.Order;
import com.cinarcorp.orderLine.model.Product;
import com.cinarcorp.orderLine.model.User;
import com.cinarcorp.orderLine.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final  OrderUserDtoConverter orderUserDtoConverter;
    private final OrderDtoConverter orderDtoConverter;

    public OrderService(OrderRepository orderRepository,
                        ProductService productService,
                        OrderUserDtoConverter orderUserDtoConverter, OrderDtoConverter orderDtoConverter) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderUserDtoConverter = orderUserDtoConverter;
        this.orderDtoConverter = orderDtoConverter;
    }

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
    public List<OrderDto> getAllOrder(){
        return orderRepository.findAll().stream().map(orderDtoConverter::convert).collect(Collectors.toList());
    }
    protected List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    public OrderDto getOrderById(String id){
        var order = orderRepository.getOrderById(id);
        return orderDtoConverter.convert(order);
    }
    public List<OrderDto> getOrderBiggerThanTotalPaid(int totalPaid){

        return orderRepository.getOrderBiggerThanTotalPaid(totalPaid).stream()
                .map(orderDtoConverter::convert).collect(Collectors.toList());
    }
    public List<OrderDto> getAllOrderAndIsComplete(boolean isComplete){
        return orderRepository.findAll().stream().filter(x->x.isComplete()==isComplete)
                .map(orderDtoConverter::convert).collect(Collectors.toList());
    }
    public OrderDto getOrderByIdAndIsComplete(String id ,boolean isComplete){
        var order = orderRepository.getOrderById(id);
        if(order.isComplete()==isComplete){
            return orderDtoConverter.convert(order);
        }else throw new OrderNotFoundException("order not found")  ;
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
