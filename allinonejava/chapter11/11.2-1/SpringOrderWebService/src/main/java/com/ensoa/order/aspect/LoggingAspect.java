package com.ensoa.order.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ensoa.order.domain.Customer;
@Aspect
@Component
public class LoggingAspect {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	// getLogging 포인트컷
	@Pointcut("execution(* com.ensoa.order.*.*.get*(..))"
			+ " || execution(* com.ensoa.order.*.*.find*(..))")
	public void getLogging() {}
	// 매개변수 포인트 컷
	@Pointcut("execution(* com.ensoa.order.service.*.update*(com.ensoa.order.domain.Customer))"
			+ " && args(customer)")
	public void updateLogging(Customer customer) {}
	// before 어드바이스
	@Before("getLogging()")
	public void logBefore(JoinPoint joinpoint) {
		String message = buildJoinpoint(joinpoint);
		message += "메서드 실행 시작";
		log.info(message);
	}
	// after 어드바이스 
	@After("getLogging()")
	public void logAfter(JoinPoint joinpoint) {
		String message = buildJoinpoint(joinpoint);
		message += "메서드 실행 공통 종료";
		log.info(message);
	}
	//after-returning 어드바이스 
	@AfterReturning("getLogging()")
	public void logAfterReturning(JoinPoint joinpoint) {
		String message = buildJoinpoint(joinpoint);
		message += "메서드 실행 정상 종료";
		log.info(message);
	}
	// after-throwing 어드바이스 
	@AfterThrowing("getLogging()")
	public void logAfterThrowing(JoinPoint joinpoint) {
		String message = buildJoinpoint(joinpoint);
		message += "메서드 실행 에러";
		log.info(message);
	}
	// around 어드바이스 
	@Around("execution(* com.ensoa.order.*.*.save*(..))")
	public void logAround(ProceedingJoinPoint joinpoint) throws Throwable {
		long start = System.currentTimeMillis();
		log.info("===========  시작 ===========");
		String message = buildJoinpoint(joinpoint);
		message += "메서드 실행 시작";
		log.info(message);
		joinpoint.proceed();		// 메서드 호출 
		message = buildJoinpoint(joinpoint);
		message += "메서드 실행 종료";
		long end = System.currentTimeMillis();
		long duration = end - start;
		log.info("실행 시간 : " + duration + " 밀리초");
		log.info("===========  종료 ===========");
	}
	
	@Before("updateLogging(customer)")
	public void logBeforeUpdate(JoinPoint joinpoint, Customer customer) {
		String message = buildJoinpoint(joinpoint);
		message += "메서드 실행 시작";
		log.info(message);
		log.info("변경 정보 : " + customer.getName() + ", " + customer.getAddress() + ", " + customer.getEmail());
	}

	private String buildJoinpoint(JoinPoint joinpoint) {
		String className = joinpoint.getTarget().getClass().getName();
		String methodName = joinpoint.getSignature().getName();
		String message = className + " 클래스의 " + methodName + "( ";
		Object [] args = joinpoint.getArgs();
		for(int i = 0; i < args.length; ++i){
			Object arg  = args[i];
			message += arg.getClass().getName(); //           .getTypeName();
			if(i != args.length - 1 )
				message += ", ";
		}
		message += " ) ";
		return message;
	}
}
