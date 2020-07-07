package victor.training.oo.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.infra.LdapUser;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LdapServiceAdapter implements ILdapServiceAdapter {
   private final LdapUserWebserviceClient wsClient;

   private User convert(LdapUser ldapUser) {
      String fullName = extractFullName(ldapUser);
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }

   private String extractFullName(LdapUser ldapUser) {
      return ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
   }

   @Override
   public List<User> searchByUsername(String username) {
      return wsClient.search(username.toUpperCase(), null, null).stream()
         .map(this::convert)
         .collect(Collectors.toList());
   }
}
