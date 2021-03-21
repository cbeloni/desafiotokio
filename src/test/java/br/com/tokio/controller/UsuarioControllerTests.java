package br.com.tokio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void buscaPorIdComSucesso(){
        try {
            mockMvc.perform(get("/usuario/1")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void buscaPorIdNotfound(){
        try {
            mockMvc.perform(get("/usuario/0")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
