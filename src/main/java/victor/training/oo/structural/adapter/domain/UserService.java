package victor.training.oo.structural.adapter.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.external.LdapUserWebserviceClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
// Domain Service
// PEace, harmony, ZEN
public class UserService {
	private final Adapter adapter;

	public void importUserFromLdap(String username) {
		List<User> list = adapter.searchByUsername(username);
		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}
		User user = list.get(0);

		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
	}

	//200 lines below
	public List<User> searchUserInLdap(String username) {
		return adapter.searchByUsername(username);
	}

}
