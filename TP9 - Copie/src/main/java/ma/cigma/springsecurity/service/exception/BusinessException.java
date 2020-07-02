package ma.cigma.springsecurity.service.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	public BusinessException(String message) {
		this.message = message;
	}
}
