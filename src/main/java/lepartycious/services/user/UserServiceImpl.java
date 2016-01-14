package lepartycious.services.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import lepartycious.daos.UserDAO;
import lepartycious.models.User;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserDAO userRepository;

    @Autowired
    public UserServiceImpl(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<lepartycious.models.User> getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        return null;
    }

    @Override
    public Optional<lepartycious.models.User> getUserByEmail(String email) {
        LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return null;
    }

    /*@Override
    public User create() {
        User user = new User();
        user.setEmail("acd");
        user.setPasswordHash(new BCryptPasswordEncoder().encode("dss"));
        user.setRole(form.getRole());
        return userRepository.save(user);
    }*/

}
