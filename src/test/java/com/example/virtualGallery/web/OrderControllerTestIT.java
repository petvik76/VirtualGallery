package com.example.virtualGallery.web;

import com.example.virtualGallery.model.entity.Artist;
import com.example.virtualGallery.model.entity.Order;
import com.example.virtualGallery.model.entity.Painting;
import com.example.virtualGallery.model.entity.UserEntity;
import com.example.virtualGallery.util.TestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtil testDataUtil;

    private UserEntity testUser;
    private Artist testArtist;
    private Painting testPainting, addNewPainting;
    private Order testOrder;

    @BeforeEach
    void setUp() {
        testUser = testDataUtil.createTestUser("aaa");
        testArtist = testDataUtil.createTestArtist();
        testPainting = testDataUtil.createTestPainting(testArtist);
        testOrder = testDataUtil.createTestOrder(testPainting, testUser);
        addNewPainting = testDataUtil.createTestPainting1(testArtist);

    }

    @AfterEach
    void tearDown() {
        testDataUtil.cleanUpDatabase();
    }

    @Test
    @WithMockUser(roles = "ADMINISTRATOR")
               void testDeleteOrderByAdmin() throws Exception {
        mockMvc.perform(delete("/delete-order/{id}", testOrder.getId()).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/orders"));
    }

    @Test
    @WithMockUser(username = "aaa", roles = "USER")
    void testAddOrder() throws Exception {

        mockMvc.perform(post("/add-order/{id}", addNewPainting.getId(), testUser).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = "ADMINISTRATOR")
    void testAllOrdersShown() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("orders"));
    }

    @Test
    @WithMockUser(roles = "ADMINISTRATOR")
    void testOrderShown() throws Exception {
        mockMvc.perform(get("/orders/{id}", testOrder.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("order-details"));
    }

}
