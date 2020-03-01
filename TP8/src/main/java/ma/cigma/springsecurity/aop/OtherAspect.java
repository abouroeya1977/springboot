package ma.cigma.springsecurity.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ma.cigma.springsecurity.service.exception.BusinessException;

@Aspect
@Component
public class OtherAspect {
	@Around("@annotation(Profile1)")
	public Object test(ProceedingJoinPoint joinPoint) throws Throwable {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		if (!userName.equals("admin1")) {
			System.out.println("Only user admin who is authorized to call this method");
			throw new BusinessException("Only user admin who is authorized to call this method");
		}
		Object proceed = joinPoint.proceed();
		return proceed;
	}

}
