package victor.training.oo.structural.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import victor.training.oo.structural.adapter.domain.UserService;

@Slf4j
@SpringBootApplication
public class AdapterSpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(AdapterSpringApp.class, args);
	}

	@Autowired
	private UserService userService;
	
	public void run(String... args) throws Exception {
		userService.importUserFromLdap("jdoe");
		log.debug("Found users: " + userService.searchUserInLdap("doe"));
	}
}