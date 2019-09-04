/*
package com.bawag.cms.aspect;

import com.byteslounge.model.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class AccountLoggingAspect {


	@Before(value = "com.byteslounge.spring.aop.PointcutDefinition.serviceLayer() && "
			+ "args(account,..)")
	public void beforeAccountMethodExecution(Account account) {
		System.out.println("Logging account access. Account: "
				+ account.getAccountNumber());
	}

	@Before(value = "com.byteslounge.spring.aop.PointcutDefinition.serviceLayer() && "
			+ "args(account,..)")
	public void beforeAccountMethodExecution(JoinPoint jp, Account account) {

		System.out.println("Before method: " + jp.getSignature().getName()
				+ ". Class: " + jp.getTarget().getClass().getSimpleName());

		System.out.println("Logging account access. Account: "
				+ account.getAccountNumber());
	}

	@Before(value = "com.byteslounge.spring.aop.PointcutDefinition.serviceLayer() && "
			+ "args(account,amount)")
	public void beforeAccountMethodExecution(JoinPoint jp, Account account,
                                             Long amount) {

		System.out.println("Logging account access. Account: "
				+ account.getAccountNumber() + ", Amount: " + amount);
	}

	@Before(value = "com.byteslounge.spring.aop.PointcutDefinition.serviceLayer() && "
			+ "args(account,..) && @annotation(auditable)")
	public void audit(Account account, Auditable auditable) {
		System.out.println("Audit account access: "
				+ account.getAccountNumber() + ". Audit destination: "
				+ auditable.value());
	}

	@Before(value = "com.byteslounge.spring.aop.PointcutDefinition.serviceLayer() && "
			+ "execution(java.util.List<com.byteslounge.model.Account> find*(..)) && "
			+ "args(customerId,..)")
	public void beforeAcountFinder(Long customerId) {
		System.out.println("Logging access to account finder. CustomerID: "
				+ customerId);
	}

	@Around(value = "com.byteslounge.spring.aop.PointcutDefinition.serviceLayer() && "
			+ "execution(* com.byteslounge.service.ExampleService.updateAccountDescription(..)) && "
			+ "args(account)")
	public void beforeUpdateAccountDescription(ProceedingJoinPoint pjp,
                                               Account account) throws Throwable {
		account.setAccountDescription(account.getAccountDescription()
				.toUpperCase());
		System.out
				.println("Logging access to updateAccountDescription. Prepared account description: "
						+ account.getAccountDescription());

		pjp.proceed(new Object[] { account });
	}

	@Before(value = "com.byteslounge.spring.aop.PointcutDefinition.serviceLayer() && "
			+ "execution(* com.byteslounge.service.ExampleService.methodUsingGenerics(..)) && "
			+ "args(parameter)")
	public void beforeGenericMethod(Account parameter) {
		System.out.println("Logging access to generic method. Account: "
				+ parameter.getAccountNumber());
	}


	/// other

	*/
/**
	 * Pointcut that matches all repositories, services and Web REST endpoints.
	 *//*

	@Pointcut("within(@org.springframework.stereotype.Repository *)" +
			" || within(@org.springframework.stereotype.Service *)" +
			" || within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the advices.
	}

	*/
/**
	 * Pointcut that matches all Spring beans in the application's main packages.
	 *//*

	@Pointcut("within(net.guides.springboot2.springboot2jpacrudexample..*)"+
			" || within(net.guides.springboot2.springboot2jpacrudexample.service..*)"+
			" || within(net.guides.springboot2.springboot2jpacrudexample.controller..*)")
	public void applicationPackagePointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the advices.
	}

	*/
/**
	 * Advice that logs methods throwing exceptions.
	 *
	 * @param joinPoint join point for advice
	 * @param e exception
	 *//*

	@AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), e.getCause() != null? e.getCause() : "NULL");
	}

	*/
/**
	 * Advice that logs when a method is entered and exited.
	 *
	 * @param joinPoint join point for advice
	 * @return result
	 * @throws Throwable throws IllegalArgumentException
	 *//*

	@Around("applicationPackagePointcut() && springBeanPointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		if (log.isDebugEnabled()) {
			log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		}
		try {
			Object result = joinPoint.proceed();
			if (log.isDebugEnabled()) {
				log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), result);
			}
			return result;
		} catch (IllegalArgumentException e) {
			log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			throw e;
		}
	}

	//other


}
*/
