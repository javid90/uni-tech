package az.unibank.unitech.service;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import az.unibank.unitech.dto.request.AccountTransferRequestDto;
import az.unibank.unitech.dto.request.UserLoginRequestDto;
import az.unibank.unitech.dto.request.UserRegisterRequestDto;
import az.unibank.unitech.dto.response.AccountResponseDto;
import az.unibank.unitech.exception.GeneralException;
import az.unibank.unitech.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class AccountServiceTest {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;

	@Autowired
	UserRepository userRepo;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void createAccountTest() {
		initializeAuthentication("7EHI5DJ");

		initializeDemoAccounts();
		
		assertThatNoException();
	}

	@Test
	@Order(2)
	void getAccountsTest() {
		initializeAuthentication("9EHA5DJ");

		accountService.getAccounts();

		assertThatNoException();
	}

	@Test
	@Order(3)
	void transferTest() {
		initializeAuthentication("6PHA5DJ");

		Set<AccountResponseDto> accounts = accountService.getAccounts();
		
		AccountResponseDto[] accountsDto = accounts.toArray(new AccountResponseDto[accounts.size()]);

		accountService.transfer(AccountTransferRequestDto.builder().sourceAccountNo(accountsDto[0].getAccountNo())
				.targetAccountNo(accountsDto[1].getAccountNo()).amount(new BigDecimal("-1")).build());

		assertThatNoException();
	}
	
	@Test
	@Order(4)
	void transferSourceAccountNotExistTest() {
		initializeAuthentication("6PHA6DJ");

		Set<AccountResponseDto> accounts = accountService.getAccounts();
		
		AccountResponseDto[] accountsDto = accounts.toArray(new AccountResponseDto[accounts.size()]);

		assertThrows(GeneralException.class, () -> {
			accountService.transfer(AccountTransferRequestDto.builder().sourceAccountNo("")
					.targetAccountNo(accountsDto[1].getAccountNo()).amount(new BigDecimal("-1")).build());
	    });
		
	}
	
	@Test
	@Order(4)
	void transferTargetAccountNotExistTest() {
		initializeAuthentication("6PHA7DJ");

		Set<AccountResponseDto> accounts = accountService.getAccounts();
		
		AccountResponseDto[] accountsDto = accounts.toArray(new AccountResponseDto[accounts.size()]);

		assertThrows(GeneralException.class, () -> {
			accountService.transfer(AccountTransferRequestDto.builder().sourceAccountNo(accountsDto[0].getAccountNo())
					.targetAccountNo("").amount(new BigDecimal("-1")).build());
	    });
		
	}
	
	@Test
	@Order(4)
	void transferNotSufficientFundsTest() {
		initializeAuthentication("6PHA8DJ");

		Set<AccountResponseDto> accounts = accountService.getAccounts();
		
		AccountResponseDto[] accountsDto = accounts.toArray(new AccountResponseDto[accounts.size()]);

		assertThrows(GeneralException.class, () -> {
		accountService.transfer(AccountTransferRequestDto.builder().sourceAccountNo(accountsDto[0].getAccountNo())
				.targetAccountNo(accountsDto[1].getAccountNo()).amount(new BigDecimal("10")).build());
		});
		
	}
	
	@Test
	@Order(4)
	void transferToTheSameAccountTest() {
		initializeAuthentication("6PHA9DJ");

		Set<AccountResponseDto> accounts = accountService.getAccounts();
		
		AccountResponseDto[] accountsDto = accounts.toArray(new AccountResponseDto[accounts.size()]);

		assertThrows(GeneralException.class, () -> {
		accountService.transfer(AccountTransferRequestDto.builder().sourceAccountNo(accountsDto[0].getAccountNo())
				.targetAccountNo(accountsDto[0].getAccountNo()).amount(new BigDecimal("10")).build());
		});
		
	}

	void initializeAuthentication(String pin) {
		userService.register(UserRegisterRequestDto.builder().pin(pin).password("12345").build());
		userService.login(UserLoginRequestDto.builder().pin("7EHI5DJ").password("12345").build());
	}
	
	void initializeDemoAccounts() {
		accountService.createAccount();
		accountService.createAccount();
	}

}
