package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface ILdapUserAdapter {
    List<User> searchByUsername(String username);
}
