package az.unibank.unitech.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyServiceTest {

	@Autowired
	private CurrencyService currencyService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void currencyRateTest() {

		currencyService.getCurrencyRate("USD/AZN");

	}

}
