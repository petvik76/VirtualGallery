package com.example.virtualGallery.service;


import com.example.virtualGallery.exception.OrderNotFoundException;
import com.example.virtualGallery.exception.PaintingAlreadyOrderedException;
import com.example.virtualGallery.model.dto.view.ViewOrderDTO;
import com.example.virtualGallery.model.entity.Order;
import com.example.virtualGallery.model.entity.Painting;
import com.example.virtualGallery.model.entity.UserEntity;
import com.example.virtualGallery.model.enums.OrderStatus;
import com.example.virtualGallery.model.mapper.OrderMapper;
import com.example.virtualGallery.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaintingService paintingService;
    private final OrderMapper orderMapper;



    public OrderService(OrderRepository orderRepository, PaintingService paintingService, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.paintingService = paintingService;
        this.orderMapper = orderMapper;

    }


    public void addNewOrder(long id, UserEntity user) {
        Optional<Order> orderByPaintingId = this.orderRepository.findByPaintingId(id);
        if (orderByPaintingId.isPresent()) {
            throw new PaintingAlreadyOrderedException();
        }
        Order order = new Order();
        order.setDateOfOrder(LocalDate.now());
        Painting paintingById = this.paintingService.getOrderPaintingById(id);
        order.setPainting(paintingById);
        order.setBuyer(user);
        order.setStatus(OrderStatus.IN_PREPARATION);
        this.orderRepository.save(order);


    }

    public List<ViewOrderDTO> getOrdersByUserId(long id) {
        return this.orderRepository.findByBuyerId(id).stream().map(orderMapper::orderToOrderDTO).collect(Collectors.toList());
    }

    public ViewOrderDTO getOrderById(long id) {
        return orderMapper.orderToOrderDTO(this.orderRepository.findById(id).orElseThrow(OrderNotFoundException::new));
    }

    @Transactional
    public void deleteOrder(long id) {
        Painting painting = this.orderRepository.findById(id).orElseThrow(OrderNotFoundException::new).getPainting();
        this.orderRepository.deleteById(id);
        this.paintingService.removePainting(painting);


    }

    public List<ViewOrderDTO> getAllOrders() {

        return this.orderRepository.findAll().stream().map(orderMapper::orderToOrderDTO).collect(Collectors.toList());
    }

    public long countOrders() {
        return this.orderRepository.count();
    }
}
