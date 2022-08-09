package com.example.virtualGallery.service;

import com.example.virtualGallery.model.entity.Role;
import com.example.virtualGallery.model.entity.UserEntity;
import com.example.virtualGallery.model.enums.UserRole;
import com.example.virtualGallery.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    private UserDetailsServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new UserDetailsServiceImpl(mockUserRepository);
    }

    @Test
    void testLoadUserByUserNameIfExist() {
        UserEntity testUserEntity = new UserEntity();
        testUserEntity.setFullName("A A");
        testUserEntity.setEmail("a@example.com");
        testUserEntity.setPassword("123");
        testUserEntity.setUsername("aaa");
        testUserEntity.setActive(true);
        Role role1 = new Role();
        role1.setRole(UserRole.ADMINISTRATOR);
        Role role2 = new Role();
        role2.setRole(UserRole.MODERATOR);
        testUserEntity.setRoles(List.of(role1, role2));

        when(mockUserRepository.findByUsername(testUserEntity.getUsername())).thenReturn(Optional.of(testUserEntity));

        UserDetails userDetails = toTest.loadUserByUsername(testUserEntity.getUsername());

        Assertions.assertEquals(testUserEntity.getUsername(), userDetails.getUsername());

        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();

        Assertions.assertEquals(2, authorities.size());

        Assertions.assertEquals("ROLE_" + UserRole.ADMINISTRATOR.name(), iterator.next().getAuthority());
        Assertions.assertEquals("ROLE_" + UserRole.MODERATOR.name(), iterator.next().getAuthority());

    }

    @Test
    void testLoadUserByUserNameIfNotExist() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> toTest.loadUserByUsername("bbb"));
    }
}
