package az.unibank.unitech.service;

import java.math.BigDecimal;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import az.unibank.unitech.dto.response.CurrencyRateResponseDto;

@Service
public class CurrencyService {
	
	@Cacheable(cacheNames = {"currencyRateCache"}, key = "#currencyPair")
	@Scheduled(fixedDelay = 1000*60 ,  initialDelay = 0)
	public CurrencyRateResponseDto getCurrencyRate(String currencyPair) {
		
		return CurrencyRateResponseDto.builder().currencyRate(new BigDecimal("1.70")).build();
		
	}

}
