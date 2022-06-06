package az.unibank.unitech.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransferRequestDto {

	private String sourceAccountNo;
	private String targetAccountNo;
	private BigDecimal amount;
	
}
