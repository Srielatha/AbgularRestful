package com.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Logging {
	@Before("execution(* com.services.UserService.*(..))")
	public void executeBeforeUserServiceCall(JoinPoint jp) {
		System.out.println("Started executing " + jp.getSignature().getName());
		Object[] args = jp.getArgs();
		Arrays.stream(args).forEach(obj -> {
			System.out.println(obj);
		});
		System.out.println("Finished executing before advice");
	}

	@AfterReturning(pointcut = "execution(* com.services.UserService.*(..))", returning = "retVal")
	public void executeAfterReturningUserServiceCall(JoinPoint jp, Object retVal) {
		System.out.println("Finished execution of " + jp.getSignature().getName() + " with ret val =");
		System.out.println(retVal);
		System.out.println("Finished executing after returning advice");
	}
}