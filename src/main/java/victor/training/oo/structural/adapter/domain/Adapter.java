package victor.training.oo.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.oo.structural.adapter.external.LdapUser;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class Adapter {
   private final LdapUserWebserviceClient wsClient;

   private User convert(LdapUser ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null).stream()
          .map(this::convert)
          .collect(Collectors.toList());
   }
}
