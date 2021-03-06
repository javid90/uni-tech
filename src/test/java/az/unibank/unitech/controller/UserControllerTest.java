package az.unibank.unitech.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import az.unibank.unitech.dto.request.UserLoginRequestDto;
import az.unibank.unitech.dto.request.UserRegisterRequestDto;
import az.unibank.unitech.util.Utility;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void registerTest() throws Exception {

		mockMvc.perform(post("/users/register")
				.content(Utility
						.convertToJsonString(UserRegisterRequestDto.builder().pin("5EPI1DA").password("12345").build()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

	}

	@Test
	@Order(2)
	void loginTest() throws Exception {

		mockMvc.perform(post("/users/login")
				.content(Utility
						.convertToJsonString(UserLoginRequestDto.builder().pin("5EPI1DA").password("12345").build()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

	}

}
