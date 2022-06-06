package az.unibank.unitech.dto.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyRateResponseDto {

	private BigDecimal currencyRate;
	
}
