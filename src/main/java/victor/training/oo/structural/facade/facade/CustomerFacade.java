package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.facade.Facade;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;
import victor.training.oo.structural.facade.repo.CustomerRepository;
import victor.training.oo.structural.facade.service.AlertService;

@Facade
@RequiredArgsConstructor
public class CustomerFacade {
    private final CustomerRepository customerRepo;
    private final AlertService alertService;
    private final CustomerConverter customerConverter;
    private final CustomerRegistrationService registrationService;

    public CustomerDto findById(long customerId) {
        Customer customer = customerRepo.findById(customerId);
        return new CustomerDto(customer);
    }

    public void registerCustomer(CustomerDto dto) {
        Customer customer = customerConverter.fromDto(dto);

        if (customer.getName().trim().length() <= 5) {
            throw new IllegalArgumentException("Name too short");
        }

        if (customerRepo.customerExistsWithEmail(customer.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
		registrationService.registerCustomer(customer);

        alertService.sendRegistrationEmail(customer.getEmail(), "Welcome!", "You'll like it! Sincerely, Team");
    }


    @Service
    @RequiredArgsConstructor
    public static class CustomerRegistrationService {
        private final CustomerRepository customerRepo;

        public void registerCustomer(Customer customer) {
            // Heavy logic
            // Heavy logic
            customerRepo.save(customer);
            // Heavy logic
        }
    }


}
