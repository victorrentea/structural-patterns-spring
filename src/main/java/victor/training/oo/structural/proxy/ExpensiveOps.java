package victor.training.oo.structural.proxy;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FileUtils;
import org.jooq.lambda.Unchecked;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Facade
//@LoggedClass
public class ExpensiveOps {
	
	private static final BigDecimal TWO = new BigDecimal("2");

	// OOME

	@LoggedMethod
	@Cacheable("primes")
//	@Transactional(propagation = REQUIERS_NEW)
	public Boolean isPrime(int n) {
//		new RuntimeException("on purpose").printStackTrace();
		log.debug("Computing isPrime({})", n);
		BigDecimal number = new BigDecimal(n);
		if (number.compareTo(TWO) <= 0) {
			return true;
		}
		if (number.remainder(TWO).equals(BigDecimal.ZERO)) {
			return false;
		}
		for (BigDecimal divisor = new BigDecimal("3"); 
			divisor.compareTo(number.divide(TWO)) < 0;
			divisor = divisor.add(TWO)) {
			if (number.remainder(divisor).equals(BigDecimal.ZERO)) {
				return false;
			}
		}
		return true;
	}

	@Autowired
	ExpensiveOps myself;

	@Cacheable("folders")
	@SneakyThrows
	public String hashAllFiles(File folder) {

		log.debug("10000169 is prime ? ");
		log.debug("Got: " + myself.isPrime(10_000_169) + "\n");
		ExpensiveOps myself2 = (ExpensiveOps) AopContext.currentProxy();
		log.debug("Got: " + myself2.isPrime(10_000_169) + "\n");


		log.debug("Computing hashAllFiles({})", folder);
		MessageDigest md = MessageDigest.getInstance("MD5");
		for (int i = 0; i < 3; i++) { // pretend there is much more work to do here
			Files.walk(folder.toPath())
				.map(Path::toFile)
				.filter(File::isFile)
				.map(Unchecked.function(FileUtils::readFileToString))
				.forEach(s -> md.update(s.getBytes()));
		}
		byte[] digest = md.digest();
	    return DatatypeConverter.printHexBinary(digest).toUpperCase();
	}

	@CacheEvict("folders")
	public void killTheCache(File folder) {
		// EMPTY. Do not delete. Let the magic happen...
	}
}
