package victor.training.oo.structural.facade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.facade.entity.Email;
import victor.training.oo.structural.facade.infra.EmailClient;
import victor.training.oo.structural.facade.repo.EmailRepository;

@RequiredArgsConstructor
@Service
public class EmailService {
   private final EmailClient emailClient;
   private final EmailRepository emailRepo;

   public void sendEmail(String emailAddress, String subject, String body) {
      System.out.println("Sending activation link via email to " + emailAddress);
      Email email = new Email();
      email.setFrom("noreply");
      email.setTo(emailAddress);
      email.setSubject(subject);
      email.setBody(body);

      if (!emailRepo.emailWasSentBefore(email.hashCode())) {
         emailClient.sendEmail(email.getFrom(), email.getTo(), email.getSubject(), email.getBody());
         emailRepo.saveSentEmail(email);
      }
   }
}
