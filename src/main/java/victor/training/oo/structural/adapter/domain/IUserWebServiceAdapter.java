package victor.training.oo.structural.adapter.domain;

import java.util.List;

public interface IUserWebServiceAdapter {
    List<User> searchUsers(String username);
}
