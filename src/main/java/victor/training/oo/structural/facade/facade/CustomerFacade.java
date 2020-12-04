package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.oo.structural.facade.Facade;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;
import victor.training.oo.structural.facade.repo.CustomerRepository;
import victor.training.oo.structural.facade.service.CustomerService;
import victor.training.oo.structural.facade.service.NotificationService;

@Facade
@RequiredArgsConstructor
public class CustomerFacade {
	private final NotificationService notificationService;
	private final CustomerRepository customerRepo;
	private final CustomerTransformer customerTransformer;
	private final CustomerService customerService;

	public CustomerDto findById(long customerId) {
		Customer customer = customerRepo.findById(customerId);
		return new CustomerDto(customer);
	}


	public void registerCustomer(CustomerDto dto) {
		Customer customer = customerTransformer.transformToEntity(dto);

		validateCustomer(customer);
		customerService.registerCustomer(customer);

		notificationService.sendRegistrationEmail(customer.getEmail(), "Welcome!", "You'll like it! Sincerely, Team");
	}


	private void validateCustomer(Customer customer) {
		if (customer.getName().trim().length() <= 5) {
			throw new IllegalArgumentException("Name too short");
		}

		if (customerRepo.customerExistsWithEmail(customer.getEmail())) {
			throw new IllegalArgumentException("Email already registered");
		}
	}


}
