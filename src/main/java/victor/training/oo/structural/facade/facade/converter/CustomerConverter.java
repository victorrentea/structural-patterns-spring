package victor.training.oo.structural.facade.facade.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;
import victor.training.oo.structural.facade.repo.SiteRepository;

@Component
@RequiredArgsConstructor
public class CustomerConverter {
    private final SiteRepository siteRepo;
    public Customer fromDto(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setEmail(dto.email);
        customer.setName(dto.name);
        customer.setSite(siteRepo.getReference(dto.countryId));
        return customer;
    }
}
