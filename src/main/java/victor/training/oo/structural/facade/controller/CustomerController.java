package victor.training.oo.structural.facade.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import victor.training.oo.structural.facade.facade.CustomerFacade;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;

@Controller //faking it
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerFacade customerFacade;

    // @GetMapping
    public CustomerDto getById(long customerId) {
        return customerFacade.findById(customerId);
    }

    // @PostMapping
    public void register(CustomerDto customerDto) {
        customerFacade.registerCustomer(customerDto);
    }
}
