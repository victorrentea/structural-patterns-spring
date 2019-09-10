package victor.training.oo.structural.proxy;

import static java.util.Arrays.asList;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@EnableCaching
@SpringBootApplication
public class ProxySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProxySpringApp.class, args);
	}



	@Autowired
	private ExpensiveOps ops;
	// TODO [1] implement decorator 
	// TODO [2] apply decorator via Spring
	// TODO [3] generic java.lang.reflect.Proxy 
	// TODO [4] Spring aspect 
	// TODO [5] Spring cache support
	// TODO [6] Back to singleton (are you still alive?)
	public void run(String... args) {
		log.info("\n");
		log.info("---- CPU Intensive ~ memoization?");
		log.info("10000169 is prime ? ");
		log.info("Got: " + ops.isPrime(10000169) + "\n");
		log.info("10000169 is prime ? ");
		log.info("Got: " + ops.isPrime(10000169) + "\n");
		
		log.info("---- I/O Intensive ~ \"There are only two things hard in programming...\"");
		log.info("Folder MD5: ");
		log.info("Got: " + ops.hashAllFiles(new File(".")) + "\n");
		log.info("Got: " + ops.hashAllFiles(new File(".")) + "\n");
		log.info("A file changes ...");
		ops.killCache(new File("."));
		log.info("Folder MD5: ");
		log.info("Got: " + ops.hashAllFiles(new File(".")) + "\n");
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface LoggedMethod{}
@Retention(RetentionPolicy.RUNTIME)
@interface LoggedClass{}

@Slf4j
@Aspect
@Component
class LoggingAspect {
	//	@Around("execution(* victor.training..*.*(..))")
//	@Around("execution(* *(..)) && @annotation(victor.training.oo.structural.proxy.LoggedMethod)")
	@Around("execution(* *(..)) && @within(victor.training.oo.structural.proxy.LoggedClass)")
	public Object logAround(ProceedingJoinPoint point) throws Throwable {
		log.debug("Calling method {} with params {}",
				point.getSignature().getName(),
				point.getArgs());
		return point.proceed();
	}
}

