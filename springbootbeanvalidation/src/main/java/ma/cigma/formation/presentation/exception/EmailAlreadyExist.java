package ma.cigma.formation.presentation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExist extends RuntimeException{

	 public EmailAlreadyExist(String exception) {
	        super(exception);
	    }
}
