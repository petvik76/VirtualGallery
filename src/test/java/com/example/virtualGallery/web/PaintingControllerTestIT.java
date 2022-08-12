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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PaintingControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtil testDataUtil;

    private Artist testArtist;
    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        testArtist = testDataUtil.createTestArtist();


    }




    @Test
    void testAllCategoriesShown() throws Exception {
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("categories"));
    }

    @Test
    @WithMockUser(roles = "ADMINISTRATOR")
    void testAddPainting() throws Exception {
        mockMvc.perform(post("/paintings/add")
                        .param("title", "aaa")
                        .param("description", "aaa")
                        .param("url", "url")
                        .param("artistId", "1")
                        .param("categoryName", "MODERN")
                        .param("price", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/categories"));
    }

}
