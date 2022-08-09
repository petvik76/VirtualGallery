package com.example.virtualGallery.service;

import com.example.virtualGallery.exception.UserNotFoundException;
import com.example.virtualGallery.model.dto.RegisterDTO;
import com.example.virtualGallery.model.dto.view.ViewPaintingDTO;
import com.example.virtualGallery.model.entity.Order;
import com.example.virtualGallery.model.entity.Painting;
import com.example.virtualGallery.model.entity.UserEntity;
import com.example.virtualGallery.model.mapper.UserMapper;
import com.example.virtualGallery.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailsService;
    private final OrderService orderService;
    private final PaintingService paintingService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, UserDetailsService userDetailsService, OrderService orderService, PaintingService paintingService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
        this.orderService = orderService;
        this.paintingService = paintingService;
    }

    public void registerAndLogin(RegisterDTO registerDTO) {

        UserEntity newUser = userMapper.userDtoToUserEntity(registerDTO);
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        this.userRepository.save(newUser);
        login(newUser);

    }


    private void login(UserEntity userEntity) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userEntity.getUsername());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }


    public UserEntity getUserbyUsername(String username) {
       return this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }
}
