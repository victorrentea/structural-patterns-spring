package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.oo.structural.facade.Facade;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.entity.Email;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;
import victor.training.oo.structural.facade.infra.EmailClient;
import victor.training.oo.structural.facade.repo.CustomerRepository;
import victor.training.oo.structural.facade.repo.EmailRepository;
import victor.training.oo.structural.facade.service.RegisterCustomerService;

@Facade
@RequiredArgsConstructor
public class CustomerFacade {
	private final CustomerRepository customerRepo;
	private final EmailClient emailClient;
	private final EmailRepository emailRepo;
	private final CustomerMapper customerMapper;
	private final RegisterCustomerService registerCustomerService;

	public CustomerDto findById(long customerId) {
		Customer customer = customerRepo.findById(customerId);
		return new CustomerDto(customer);
	}

	public void registerCustomer(CustomerDto dto) {
		Customer customer = customerMapper.toEntity(dto);

		/// TODO extract a validator
		if (customer.getName().trim().length() <= 5) {
			throw new IllegalArgumentException("Name too short");
		}
		if (customerRepo.customerExistsWithEmail(customer.getEmail())) {
			throw new IllegalArgumentException("Email already registered");
		}

		registerCustomerService.registerCustomer(customer);

		sendRegistrationEmail(customer.getEmail());
	}



	private void sendRegistrationEmail(String emailAddress) {
		System.out.println("Sending activation link via email to "+ emailAddress);
		Email email = new Email();
		email.setFrom("noreply");
		email.setTo(emailAddress);
		email.setSubject("Welcome!");
		email.setBody("You'll like it! Sincerely, Team");
		
		if (!emailRepo.emailWasSentBefore(email.hashCode())) {
			emailClient.sendEmail(email.getFrom(), email.getTo(), email.getSubject(), email.getBody());
			emailRepo.saveSentEmail(email);
		}
	}
}
