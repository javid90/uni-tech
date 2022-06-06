package az.unibank.unitech.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import az.unibank.unitech.enumeration.EntityStateEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "accounts")
public class AccountEntity extends CoreEntity {

	@Column(name = "account_no")
	private String accountNo;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column
	private BigDecimal balance;
	
	@Column
	private EntityStateEnum status;

}
