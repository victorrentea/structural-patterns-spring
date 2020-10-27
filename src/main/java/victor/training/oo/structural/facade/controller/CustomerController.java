package victor.training.oo.structural.facade.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import victor.training.oo.structural.facade.facade.CustomerFacade;

@Controller //faking it
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerFacade customerFacade;

    // @GetMapping
    public victor.training.patterns.structural.facade.facade.dto.CustomerDto getById(long customerId) {
        return customerFacade.findById(customerId);
    }

    // @PostMapping
    public void register(victor.training.patterns.structural.facade.facade.dto.CustomerDto customerDto) {
        customerFacade.registerCustomer(customerDto);
    }
}
