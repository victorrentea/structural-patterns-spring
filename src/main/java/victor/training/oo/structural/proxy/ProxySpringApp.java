package victor.training.oo.structural.proxy;

import static java.util.Arrays.asList;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
	public void run(String... args) throws Exception {
		log.debug("To who am I sending my method call ? " + ops.getClass());

		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");

		ops.anotherMethod();
		
//		log.debug("---- I/O Intensive ~ \"There are only two things hard in programming...\"");
//		log.debug("Folder MD5: ");
//		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");
//		log.debug("Folder MD5: ");
//		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");
	}
}

@Service
@Retention(RetentionPolicy.RUNTIME)
@interface Facade{

}
@Retention(RetentionPolicy.RUNTIME)
@interface LoggedClass {}

@Aspect
@Component
class LoggingInterceptor {
	private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

//	@Around("execution(* victor..*.*(..))") // too magic. People need to know that **THAT** package is special
//	@Around("execution(* victor..*.*(..)) && @within(victor.training.oo.structural.proxy.LoggedClass)")
	@Around("execution(* victor..*.*(..)) && @within(victor.training.oo.structural.proxy.Facade)")
	public Object logExecution(ProceedingJoinPoint pjp) throws Throwable {
		log.info("Calling method {} with args: {}", pjp.getSignature().getName(), Arrays.asList(pjp.getArgs()));
		long t0 = System.currentTimeMillis();
		// call the real method
		Object result = pjp.proceed();
		long t1 = System.currentTimeMillis();
		log.info("Execution took: {}", t1 - t0);
		return result;
	}
}