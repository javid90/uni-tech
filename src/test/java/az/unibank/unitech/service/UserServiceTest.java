package az.unibank.unitech.service;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import az.unibank.unitech.dto.request.UserLoginRequestDto;
import az.unibank.unitech.dto.request.UserRegisterRequestDto;
import az.unibank.unitech.dto.response.UserRegisterResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class UserServiceTest {

	@Autowired
    private UserService userService;
	
	@Test
	@Order(1)
    public void registerTest() {

        UserRegisterResponseDto response = userService.register(UserRegisterRequestDto.builder().pin("7ELI5DJ").password("12345").build());

        assertNotNull(response);

    }
	
	@Test
	@Order(2)
    public void loginTest() {

        userService.login(UserLoginRequestDto.builder().pin("7ELI5DJ").password("12345").build());

        assertThatNoException();

    }

}
