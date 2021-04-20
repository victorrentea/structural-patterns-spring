package victor.training.oo.structural.adapter.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.adapter.infra.LdapUser;
import victor.training.oo.structural.adapter.infra.LdapUserWebserviceClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {
	@Autowired
	private LdapUserWebserviceClient wsClient;

	public void importUserFromLdap(String username) {
		List<LdapUser> list = wsClient.search(username.toUpperCase(), null, null);
		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}
		LdapUser ldapUser = list.get(0);
		String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
		User user = new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
		
		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
	}
	
	public List<User> searchUserInLdap(String username) {
		List<LdapUser> list = wsClient.search(username.toUpperCase(), null, null);
		List<User> results = new ArrayList<>();
		for (LdapUser ldapUser : list) {
			String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
			User user = new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
			results.add(user);
		}
		return results;
	}
	
}
