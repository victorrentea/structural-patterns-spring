package victor.training.oo.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
	private final LdapServiceAdapter ldapServiceAdapter;

	public void importUserFromLdap(String username) {
		List<User> list = ldapServiceAdapter.searchByUsername(username);
		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}
		User user = list.get(0);

		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
	}

	public List<User> searchUserInLdap(String username) {
		return ldapServiceAdapter.searchByUsername(username);
	}
	// ZEN GARDEN. PEACE. YING and YANG
	/// ----------------line-------------------------------------------------------------
	// Beware. You that enter, give up your pride.

}
