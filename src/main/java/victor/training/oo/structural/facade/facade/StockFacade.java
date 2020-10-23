package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.oo.structural.facade.Facade;
import victor.training.oo.structural.facade.service.EmailService;

@Facade
@RequiredArgsConstructor
public class StockFacade {
	private final EmailService emailService;

	public void checkStock() {
		// 3 lines of domain Logic

		// TODO same as in CustomerFacade but with other subject/body
		emailService.sendEmail("a@b.com",
			"Good new!", "It's in stock");
	}
}
