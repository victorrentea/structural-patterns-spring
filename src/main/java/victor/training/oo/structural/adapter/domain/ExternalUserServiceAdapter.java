package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface ExternalUserServiceAdapter {
    List<User> searchByUsername(String username);
}
