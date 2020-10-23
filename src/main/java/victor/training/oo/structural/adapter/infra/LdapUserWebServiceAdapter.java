package victor.training.oo.structural.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.oo.structural.adapter.domain.ExternalUserService;
import victor.training.oo.structural.adapter.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

// zen pieceful nirvana
// ============= a line ======================================================
// you who enter, abandon all hope
@Component
@RequiredArgsConstructor
public class LdapUserWebServiceAdapter implements ExternalUserService {
   private final LdapUserWebserviceClient wsClient;

   @Override
   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null)
          .stream().map(this::convertUser)
          .collect(toList());
   }

   private User convertUser(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }
}
