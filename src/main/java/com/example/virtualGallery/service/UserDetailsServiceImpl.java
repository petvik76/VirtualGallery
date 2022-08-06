package com.example.virtualGallery.service;

import com.example.virtualGallery.model.entity.Role;
import com.example.virtualGallery.model.entity.UserEntity;
import com.example.virtualGallery.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.
                findByUsername(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return
                User.builder().
                        username(userEntity.getUsername()).
                        password(userEntity.getPassword()).
                        authorities(userEntity.
                                getRoles().
                                stream().
                                map(this::map).
                                toList()).
                        build();
    }

    private GrantedAuthority map(Role userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.getRole().name());
    }
}
