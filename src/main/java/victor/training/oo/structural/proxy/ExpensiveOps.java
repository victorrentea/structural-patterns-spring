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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@LoggedClass
public class ExpensiveOps {

	
	private static final BigDecimal TWO = new BigDecimal("2");

//	@Transactional
	@Cacheable("primes") // imagine that somewhere, in a cave, there;s a map
	// Map<Integer, Boolean> primes = new HashMap<>();
	public Boolean isPrime(int n) {
		log.debug("Computing isPrime({})", n);

		new RuntimeException().printStackTrace();

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

	public void methodInTheSameClass() {
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + isPrime(10_000_169) + "\n");
	}

}
