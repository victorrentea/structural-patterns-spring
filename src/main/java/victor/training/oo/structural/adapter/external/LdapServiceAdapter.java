package victor.training.oo.structural.adapter.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.training.oo.structural.adapter.domain.ILdapServiceAdapter;
import victor.training.oo.structural.adapter.domain.User;
import victor.training.oo.structural.adapter.external.LdapUser;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LdapServiceAdapter implements ILdapServiceAdapter {
    private final LdapUserWebserviceClient wsClient;

    // ---------------------------------- LINE -------------------------------------------
    //	You that enter, abandon all hope.
    @Override
    public List<User> searchByUsername(String username) {
        return wsClient.search(username.toUpperCase(), null, null)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private User convert(LdapUser ldapUser) {
        String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
        return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
    }
}
