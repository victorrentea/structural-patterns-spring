package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.oo.structural.facade.Facade;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;
import victor.training.oo.structural.facade.repo.CustomerRepository;
import victor.training.oo.structural.facade.service.RegisterCustomerService;
import victor.training.oo.structural.facade.service.EmailService;

@Facade
@RequiredArgsConstructor
public class CustomerFacade {
	private final CustomerRepository customerRepo;
	private final CustomerMapper customerMapper;
	private final RegisterCustomerService customerService;
	private final EmailService emailService;

	public CustomerDto findById(long customerId) {
		Customer customer = customerRepo.findById(customerId);
		return new CustomerDto(customer);
	}

	public void registerCustomer(CustomerDto dto) {
		Customer customer = customerMapper.toEntity(dto);

		// TODO extract validator Or mayby @Length
		if (customer.getName().trim().length() <= 5) {
			throw new IllegalArgumentException("Name too short");
		}
		
		if (customerRepo.customerExistsWithEmail(customer.getEmail())) {
			throw new IllegalArgumentException("Email already registered");
		}
		customerService.registerCustomer(customer);

		emailService.sendEmail(customer.getEmail(),
			"Welcome!", "You'll like it! Sincerely, Team");
	}
//1000K big problem

}
