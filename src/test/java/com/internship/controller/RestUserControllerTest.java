package com.internship.controller;

import com.internship.controller.rest.RestUserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:hibernate.cfg.xml")
@WebMvcTest(RestUserController.class)
public class RestUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void view() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/api/users")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void delete() {
    }

    @Test
    public void edit() {
    }

    @Test
    public void save() {
    }
}