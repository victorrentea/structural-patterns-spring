package victor.training.oo.structural.proxy;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;
import victor.training.oo.stuff.ThreadUtils;

@Slf4j
@EnableCaching
@SpringBootApplication
public class ProxySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProxySpringApp.class, args);
	}

	@Autowired
	ExpensiveOps ops;
//	@Bean
//	public ExpensiveOps ops() {
//		return new ExpensiveOps();
//	}

	// TODO [1] implement decorator
	// TODO [2] apply decorator via Spring
	// TODO [3] generic java.lang.reflect.Proxy
	// TODO [4] Spring aspect
	// TODO [5] Spring cache support
	// TODO [6] Back to singleton (are you still alive?)
	public void run(String... args) throws Exception {

		System.out.println(ops.getClass().getName());
		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
//		ThreadUtils.sleep(2000);
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		
		log.debug("---- I/O Intensive ~ \"There are only two things hard in programming...\"");
		log.debug("Folder MD5: ");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");

		log.debug("I detect somehow that a file was changed in the folder");
		ops.killFolderCache(new File("."));


		log.debug("Folder MD5: ");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");
	}
	
}