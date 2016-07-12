package lepartycious.services.implementations;

import java.util.regex.Pattern;

import lepartycious.models.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private static final Long DEFAULT_MAX_CAL_PER_DAY = 2000L;

    private static final Pattern PASSWORD_REGEX = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}");

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    @Autowired
    private lepartycious.daos.implementations.UserRepository userRepository;

    /**
     *
     * updates the maximum calories of a given user
     *
     * @param username - the currently logged in user
     * @param newMaxCalories - the new max daily calories for the user
     */
   /* @Transactional
    public void updateUserMaxCaloriesPerDay(String username, Long newMaxCalories) {
        User user = userRepository.findUserByUsername(username);

        if (user != null) {
            user.setMaxCaloriesPerDay(newMaxCalories);
        } else {
            LOGGER.info("User with username " + username + " could not have the max calories updated.");
        }
    }*/

    /**
     *
     * creates a new user in the database
     *
     * @param username - the username of the new user
     * @param email - the user email
     * @param password - the user plain text password
     */

    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}
