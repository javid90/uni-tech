package az.unibank.unitech.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import az.unibank.unitech.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByPin(String pin);
	
}
