package lepartycious.services.implementations;


import lepartycious.daos.UserDAO;
import lepartycious.models.User;
import lepartycious.services.SecurityUserDetailsService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * UserDetails service that reads the user credentials from the database, using a JPA repository.
 *
 */
@Service
public class SecurityUserDetailsServiceImpl implements SecurityUserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(SecurityUserDetailsServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user;
        try{
        	user = userDAO.loadUserByUsername(username);
        }
        catch(Exception exception){
        	LOGGER.error("User not found" + username, exception);
        	throw new UsernameNotFoundException("User not found" + username, exception);
        }
        return user;
    }
}
