package az.unibank.unitech.enumeration;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum EntityStateEnum {
	DEACTIVE(0), ACTIVE(1);

	private final short value;

	EntityStateEnum(int value) {
		this.value = (short) value;
	}

	public static EntityStateEnum getEntityStateEnum(short value) {
		return Arrays.stream(EntityStateEnum.values()).filter(e -> e.value == value).findFirst()
				.orElseThrow(() -> new RuntimeException("Wrong state"));
	}

}
