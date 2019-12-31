package com.mmn.reservation;

import com.google.gson.Gson;
import com.mmn.reservation.model.LoginDto;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ReservationServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void login_valid() throws Exception {
        final LoginDto body = LoginDto.builder().Email("test1@gmail.com").ContractNumber("7cd3db97-2f59-46f6-a91a-7812d40273a8").build();
        final Gson gson = new Gson();
        final String json = gson.toJson(body);
        final val result = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/sor/login")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("subscriptionId", "0"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void login_invalid() throws Exception {
        final LoginDto body = LoginDto.builder().Email("asdadasdasdas@gmail.com").ContractNumber("asdasdasdasdas").build();
        final Gson gson = new Gson();
        final String json = gson.toJson(body);
        final val result = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/sor/login")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("subscriptionId", "0"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
