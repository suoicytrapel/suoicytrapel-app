package lepartycious.daos.implementations;


import lepartycious.models.User;

import org.springframework.stereotype.Repository;

/**
 *
 * Repository class for the User entity
 *
 */
@Repository
public class UserRepository {


    /**
     * finds a user given its username
     *
     * @param username - the username of the searched user
     * @return  a matching user, or null if no user found.
     */
    public User findUserByUsername(String username) {
    	return null;

        /*List<User> users = em.createNamedQuery(User.FIND_BY_USERNAME, User.class)
                .setParameter("username", username)
                .getResultList();

        return users.size() == 1 ? users.get(0) : null;*/
    }

    /**
     *
     * find the total calories that a given user has consumed so far in ongoing day
     *
     * @param username
     * @return the total number of calories for the user for today
     */

    /**
     * checks if a username is still available in the database
     *
     * @param username - the username to be checked for availability
     * @return true if the username is still available
     */
    public boolean isUsernameAvailable(String username) {

       /* List<User> users = em.createNamedQuery(User.FIND_BY_USERNAME, User.class)
                .setParameter("username", username)
                .getResultList();

        return users.isEmpty();*/
    	return false;
    }
}
