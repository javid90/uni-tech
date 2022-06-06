package az.unibank.unitech.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import az.unibank.unitech.entity.CoreEntity;
import az.unibank.unitech.enumeration.EntityStateEnum;

public class CoreEntityListener {
	@PrePersist
	public void onPrePersist(CoreEntity coreEntity) {
		coreEntity.setState(EntityStateEnum.ACTIVE);
		coreEntity.setCreatedDate(LocalDateTime.now());
	}

	@PreUpdate
	public void onPreUpdate(CoreEntity coreEntity) {
		coreEntity.setLastModifiedDate(LocalDateTime.now());
	}

}
