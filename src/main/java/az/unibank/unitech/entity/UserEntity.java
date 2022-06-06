package az.unibank.unitech.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import az.unibank.unitech.enumeration.EntityStateEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class UserEntity extends CoreEntity {

	@Column
	private String pin;

	@Column
	private String password;

	@Column
	private String firstname;

	@Column
	private String lastname;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Set<AccountEntity> accounts;
	
	@Column
	private EntityStateEnum status;

}
