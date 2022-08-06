package com.example.virtualGallery.web;

import com.example.virtualGallery.model.dto.view.ViewUserDTO;
import com.example.virtualGallery.model.entity.UserEntity;
import com.example.virtualGallery.model.mapper.UserMapper;
import com.example.virtualGallery.service.OrderService;
import com.example.virtualGallery.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserProfileController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final OrderService orderService;

    public UserProfileController(UserService userService, UserMapper userMapper, OrderService orderService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.orderService = orderService;
    }

    @GetMapping("/users/profile")
    public String userProfile(Principal principal, Model model) {
        String username = principal.getName();
        UserEntity user = this.userService.getUserbyUsername(username);
        ViewUserDTO viewUserDTO = this.userMapper.userToUserDTO(user);
        model.addAttribute("userModel", viewUserDTO);
        model.addAttribute("orders", this.orderService.getOrdersByUserId(user.getId()));
        return "profile";
    }
}
