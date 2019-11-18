package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.oo.structural.facade.Facade;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;
import victor.training.oo.structural.facade.repo.CustomerRepository;
import victor.training.oo.structural.facade.repo.SiteRepository;
import victor.training.oo.structural.facade.service.AlertService;

@Facade
@RequiredArgsConstructor
public class CustomerFacade {
	private final CustomerRepository customerRepo;
	private final AlertService alertService;
	private final SiteRepository siteRepo;

	public CustomerDto findById(long customerId) {
		Customer customer = customerRepo.findById(customerId);
		return new CustomerDto(customer);
	}

	public void registerCustomer(CustomerDto dto) {
		Customer customer = convertCustomerDto(dto);

		if (customer.getName().trim().length() <= 5) {
			throw new IllegalArgumentException("Name too short");
		}
		
		if (customerRepo.customerExistsWithEmail(customer.getEmail())) {
			throw new IllegalArgumentException("Email already registered");
		}
		// Heavy logic
		// Heavy logic
		customerRepo.save(customer);
		// Heavy logic

		alertService.sendRegistrationEmail(customer.getEmail(), "Welcome!", "You'll like it! Sincerely, Team");
	}

	private Customer convertCustomerDto(CustomerDto dto) {
		Customer customer = new Customer();
		customer.setEmail(dto.email);
		customer.setName(dto.name);
		customer.setSite(siteRepo.getReference(dto.countryId));
		return customer;
	}


}
