package victor.training.oo.structural.facade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.repo.CustomerRepository;

@RequiredArgsConstructor
@Service
public class CustomerService {
   private final CustomerRepository customerRepo;

   public void registerCustomer(Customer customer) {
      // Heavy logic
      // Heavy logic
      // Heavy logic
      // Heavy logic
      // Heavy logic
      // Heavy logic
      // Heavy logic
      // Heavy logic
      // Heavy logic
      // Heavy logic
      customerRepo.save(customer);
      // Heavy logic
   }
}
