package az.unibank.unitech.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class GeneralException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GeneralException(String message) {

		super(message);

	}

}
