package az.unibank.unitech.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import az.unibank.unitech.dto.request.AccountTransferRequestDto;
import az.unibank.unitech.dto.request.UserLoginRequestDto;
import az.unibank.unitech.dto.request.UserRegisterRequestDto;
import az.unibank.unitech.dto.response.AccountResponseDto;
import az.unibank.unitech.repository.UserRepository;
import az.unibank.unitech.service.AccountService;
import az.unibank.unitech.service.UserService;
import az.unibank.unitech.util.Utility;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AccountService accountService;

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	@Order(1)
	void createAccountTest() throws Exception {

		initializeAuthentication("5EPI1DV");

		mockMvc.perform(post("/accounts").with(httpBasic("5EPI1DV", "12345")).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

	}

	@Test
	@Order(2)
	void accountsTest() throws Exception {

		initializeAuthentication("5EPI1DC");

		mockMvc.perform(get("/accounts").with(httpBasic("5EPI1DC", "12345")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

	}

	@Test
	@Order(3)
	void accountTransferTest() throws Exception {

		initializeAuthentication("5EPI1BC");

		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		
		mockMvc.perform(put("/accounts/transfer").with(httpBasic("5EPI1BC", "12345"))
				.content(Utility.convertToJsonString(AccountTransferRequestDto.builder().sourceAccountNo("")
						.targetAccountNo("").amount(new BigDecimal("-1")).build()))
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());

	}

	void initializeAuthentication(String pin) throws Exception {

		mockMvc.perform(post("/users/register")
				.content(Utility
						.convertToJsonString(UserRegisterRequestDto.builder().pin(pin).password("12345").build()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

		mockMvc.perform(post("/users/login")
				.content(Utility.convertToJsonString(UserLoginRequestDto.builder().pin(pin).password("12345").build()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	void initializeDemoAccounts() {
		accountService.createAccount();
		accountService.createAccount();
	}
	
}
