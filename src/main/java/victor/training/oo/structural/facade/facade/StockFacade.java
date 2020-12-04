package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.oo.structural.facade.Facade;
import victor.training.oo.structural.facade.service.NotificationService;

@Facade
@RequiredArgsConstructor
public class StockFacade {
	private final NotificationService notificationService;

	public void checkStock() {
		// 3 lines of domain Logic

		// TODO same as in CustomerFacade but with other subject/body

		notificationService.sendRegistrationEmail("a@b.com", "Product is in Stock!", "Hooray!");
	}
}
