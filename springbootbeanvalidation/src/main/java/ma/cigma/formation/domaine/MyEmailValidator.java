package ma.cigma.formation.domaine;

import java.util.Scanner;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyEmailValidator implements ConstraintValidator<EmailCriteria, String> {

	@Override
	public boolean isValid(String mail, ConstraintValidatorContext arg1) {
		Scanner scan = new Scanner(mail);
		if (mail == null || !mail.contains("@"))
			return false;
		scan.useDelimiter("@");
		scan.next();
		String suffix=scan.next();
		if (suffix != null && suffix.equals("gmail.com"))
			return true;
		return false;
	}
	
	
//	public static void main(String[] args) {
//		String mail="abbbou";
//		Scanner scann=new Scanner(mail);
//		scann.useDelimiter("@");
//		String s1=scann.next();
//		System.out.println("s1= " + s1);
//	}

}
