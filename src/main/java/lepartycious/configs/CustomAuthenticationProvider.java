/*package lepartycious.configs;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import lepartycious.models.User;
import lepartycious.services.implementations.SecurityUserDetailsService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
 
    @Autowired
    private SecurityUserDetailsService userService;
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
          String username = authentication.getName();
          String password = (String) authentication.getCredentials();
     
            User user = userService.loadUserByUsername(username);
     
            if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
                throw new BadCredentialsException("Username not found.");
            }
     
            if (!password.equals(user.getPassword())) {
                throw new BadCredentialsException("Wrong password.");
            }
     
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
     
            return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }
 
    public boolean supports(Class<?> arg0) {
        return true;
    }
 
}
*/