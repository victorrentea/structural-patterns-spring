package victor.training.oo.structural.adapter.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.oo.structural.adapter.domain.ExternalUserService;
import victor.training.oo.structural.adapter.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class LdapUserWebServiceAdapter implements ExternalUserService {
   private final LdapUserWebserviceClient wsClient;

   private User convert(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   @Override
   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null).stream()
          .map(this::convert)
          .collect(Collectors.toList());
   }
}
