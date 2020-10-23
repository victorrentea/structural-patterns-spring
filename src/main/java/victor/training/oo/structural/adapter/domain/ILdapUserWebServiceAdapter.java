package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface ILdapUserWebServiceAdapter {
   List<User> searchByUsername(String username);
}
