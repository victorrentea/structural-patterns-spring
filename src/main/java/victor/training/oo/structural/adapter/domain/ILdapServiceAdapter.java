package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface ILdapServiceAdapter {
    List<User> searchByUsername(String username);
}
