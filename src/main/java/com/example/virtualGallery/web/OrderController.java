package com.example.virtualGallery.web;

import com.example.virtualGallery.model.entity.UserEntity;
import com.example.virtualGallery.service.OrderService;

import com.example.virtualGallery.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;



    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;

    }


    @PostMapping("/add-order/{id}")
    public String addOrder(@PathVariable("id") long id, Principal principal) {
        String username = principal.getName();
        UserEntity user = this.userService.getUserbyUsername(username);
        this.orderService.addNewOrder(id, user);
        return "redirect:/";
    }

    @GetMapping("/orders/{id}")
    public String orderDetails(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order-details";
    }

    @DeleteMapping("/delete-order/{id}")
    public String deleteOrder(@PathVariable("id") long id) {
        this.orderService.deleteOrder(id);
        return "redirect:/orders";
    }


    @GetMapping("/orders")
    public String getOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }



}
