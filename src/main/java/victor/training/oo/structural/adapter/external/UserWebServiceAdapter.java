package victor.training.oo.structural.adapter.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.domain.IUserWebServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;
import victor.training.oo.structural.adapter.external.LdapUser;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

// ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN ZEN
// ----------------------------  -----------------------------
// Infra Infra Infra Infra Infra Infra Infra Infra Infra Infra Infra Infra Infra
@Service
@RequiredArgsConstructor
public class UserWebServiceAdapter implements IUserWebServiceAdapter {
    private final LdapUserWebserviceClient wsClient;

    private User createUser(LdapUser ldapUser) {
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }

    @Override
    public List<User> searchUsers(String username) {
        return wsClient.search(username.toUpperCase(), null, null)
                .stream().map(this::createUser).collect(toList());
    }
}
