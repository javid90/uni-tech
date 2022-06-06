package az.unibank.unitech.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import az.unibank.unitech.entity.AccountEntity;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

	AccountEntity findByAccountNo(String accountNo);
	
}
