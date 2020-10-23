package victor.training.oo.structural.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import victor.training.oo.structural.facade.facade.CustomerFacade;

@SpringBootApplication
public class FacadeSpringApp implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(FacadeSpringApp.class);
	}
	@Autowired
	private CustomerFacade customerFacade;
	
	public void run(String... args) throws Exception {
		// pretend you are in a @RestController here
		customerFacade.registerCustomer(new victor.training.patterns.structural.facade.facade.dto.CustomerDto("John Doe", "john.doe@pentagon.us"));
	}
}
