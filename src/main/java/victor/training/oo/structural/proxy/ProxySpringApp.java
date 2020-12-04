package victor.training.oo.structural.proxy;

import static java.util.Arrays.asList;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@EnableCaching
@SpringBootApplication
@RequiredArgsConstructor
public class ProxySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProxySpringApp.class, args);
	}


	private final ExpensiveOps ops;

	// TODO [1] implement decorator
	// TODO [2] apply decorator via Spring
	// TODO [3] generic java.lang.reflect.Proxy
	// TODO [4] Spring aspect
	// TODO [5] Spring cache support
	// TODO [6] Back to singleton (are you still alive?)
	public void run(String... args) throws Exception {
		log.debug(ops.getClass().getCanonicalName());
		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10_000_169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10_000_169) + "\n");

//		ops.methodInTheSameClass();
	}
	
}
@Retention(RetentionPolicy.RUNTIME)
@interface LoggedClass {

}

@Slf4j
@Component
@Aspect
class LoggingAspect {

	@Around("execution(* *(..)) && @within(victor.training.oo.structural.proxy.LoggedClass)")
	public Object method(ProceedingJoinPoint pjp) throws Throwable {
		log.info("Calling method " + pjp.getSignature().getName() + " with args " + Arrays.toString(pjp.getArgs()));
		long t0 = System.currentTimeMillis();
		Object result = pjp.proceed();
		long t1 = System.currentTimeMillis();
		log.info("Took {}", t1-t0);
		return result;
	}
}