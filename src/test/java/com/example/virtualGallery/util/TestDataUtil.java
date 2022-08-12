package com.example.virtualGallery.util;

import com.example.virtualGallery.model.entity.*;
import com.example.virtualGallery.model.enums.ArtCategory;
import com.example.virtualGallery.model.enums.OrderStatus;
import com.example.virtualGallery.model.enums.UserRole;
import com.example.virtualGallery.repository.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestDataUtil {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final CategoryRepository categoryRepository;
    private final PaintingRepository paintingRepository;
    private final OrderRepository orderRepository;

    public TestDataUtil(RoleRepository roleRepository, UserRepository userRepository, ArtistRepository artistRepository, CategoryRepository categoryRepository, PaintingRepository paintingRepository, OrderRepository orderRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
        this.categoryRepository = categoryRepository;
        this.paintingRepository = paintingRepository;
        this.orderRepository = orderRepository;
    }

    private void initRoles() {
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setRole(UserRole.ADMINISTRATOR);
            Role userRole = new Role();
            userRole.setRole(UserRole.USER);

            roleRepository.save(adminRole);
            roleRepository.save(userRole);
        }
    }

    private void initCategories() {
        if (categoryRepository.count() == 0) {
            Category modCategory = new Category();
            modCategory.setCategoryName(ArtCategory.MODERN);
            Category minCategory = new Category();
            minCategory.setCategoryName(ArtCategory.MINIMALISM);
            Category impCategory = new Category();
            impCategory.setCategoryName(ArtCategory.IMPRESSIONISM);

            categoryRepository.save(modCategory);
            categoryRepository.save(minCategory);
            categoryRepository.save(impCategory);
        }
    }

    public UserEntity createTestUser(String username) {

        initRoles();

        UserEntity user = new UserEntity();
        user.setFullName("AAA");
        user.setUsername(username);
        user.setEmail("a@a");
        user.setActive(true);
        user.setPassword("123");
        user.setRoles(roleRepository.
                findAll().stream().
                filter(r -> r.getRole() != UserRole.ADMINISTRATOR).
                toList());

        return userRepository.save(user);
    }

    public Artist createTestArtist() {
        Artist artist = new Artist();
        artist.setName("AA");
        artist.setDescription("aaa");

        return artistRepository.save(artist);
    }

    public Painting createTestPainting(Artist artist) {
        initCategories();
        Painting painting = new Painting();
        painting.setTitle("AAA");
        painting.setCategory(categoryRepository.findByCategoryName(ArtCategory.MODERN));
        painting.setArtist(artist);
        painting.setDescription("aaa");
        painting.setPrice(1);
        painting.setUrl("url");

        return paintingRepository.save(painting);
    }

    public Painting createTestPainting1(Artist artist) {
        initCategories();
        Painting painting = new Painting();
        painting.setTitle("BBB");
        painting.setCategory(categoryRepository.findByCategoryName(ArtCategory.MODERN));
        painting.setArtist(artist);
        painting.setDescription("bbb");
        painting.setPrice(1);
        painting.setUrl("url1");

        return paintingRepository.save(painting);
    }

    public Order createTestOrder(Painting painting, UserEntity buyer) {
        Order order = new Order();
        order.setPainting(painting);
        order.setStatus(OrderStatus.IN_PREPARATION);
        order.setBuyer(buyer);
        order.setDateOfOrder(LocalDate.of(2000, 02, 20));
        return orderRepository.save(order);

    }

    public void cleanUpDatabase() {
        orderRepository.deleteAll();
        paintingRepository.deleteAll();
        artistRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
        categoryRepository.deleteAll();
    }


}
