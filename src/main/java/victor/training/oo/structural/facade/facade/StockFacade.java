package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.oo.structural.facade.Facade;
import victor.training.oo.structural.facade.service.AlertService;

@Facade
@RequiredArgsConstructor
public class StockFacade {
	private final AlertService alertService;

	public void checkStock() {
		// Domain Logic

		// TODO same as in CustomerFacade but with other subject/body
		alertService.sendRegistrationEmail("customer.email@b.com", "Checked stock", "Stock is OK");
		
	}
}
