package com.example.RainforestRetail.services;import com.example.RainforestRetail.models.Order;import com.example.RainforestRetail.models.Product;import com.example.RainforestRetail.models.ProductOrder;import com.example.RainforestRetail.repositories.OrderRepository;import com.example.RainforestRetail.repositories.ProductOrderRepository;import com.example.RainforestRetail.repositories.ProductRepository;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.HashMap;import java.util.Map;@Servicepublic class ProductOrderService {    @Autowired    ProductOrderRepository productOrderRepository;    @Autowired    OrderRepository orderRepository;    @Autowired    ProductRepository productRepository;    @Autowired    OrderService orderService;    //create a Product order    public Order createOrderWithProducts(long userId, HashMap<Long, Integer> basket) {        Order order = orderService.createOrder(userId);        for (Map.Entry<Long, Integer> entry : basket.entrySet()) {            Product product = productRepository.findById(entry.getKey()).get();            if (product.getStock() > 0) {                product.setStock(product.getStock()-entry.getValue());                productRepository.save(product);                ProductOrder productOrder = new ProductOrder(entry.getValue(), product, order);                order.addToProductOrdersList(productOrder);                productOrderRepository.save(productOrder);                orderRepository.save(order);                return order;            } else return null;        }        return null;    }    // find product    // reduce stock by quantity in order    //save updated product to reposit}