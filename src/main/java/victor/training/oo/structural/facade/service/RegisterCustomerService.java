package victor.training.oo.structural.facade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.repo.CustomerRepository;

@Service
@RequiredArgsConstructor
public class RegisterCustomerService {
   private final CustomerRepository customerRepo;

   public void registerCustomer(Customer customer) {
      // Heavy logic
      // Heavy logic
      customerRepo.save(customer);
      // Heavy logic
   }
}
