package ma.cigma.formation.domaine;

import java.io.File;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyFileValidator implements ConstraintValidator<MyFileRegle,List<File>>{

	@Override
	public boolean isValid(List<File> files, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
