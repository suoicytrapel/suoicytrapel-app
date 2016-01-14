package lepartycious.services.user;

import java.util.Collection;
import java.util.Optional;

import lepartycious.models.User;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

}
