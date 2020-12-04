package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;
import victor.training.oo.structural.facade.repo.SiteRepository;

import java.text.SimpleDateFormat;

@RequiredArgsConstructor
@Component
public class CustomerTransformer {
   private final SiteRepository siteRepo;

   public Customer transformToEntity(CustomerDto dto) {
      Customer customer = new Customer();
      customer.setEmail(dto.email);
      customer.setName(dto.name);
      customer.setSite(siteRepo.getReference(dto.countryId));
      return customer;
   }
}
