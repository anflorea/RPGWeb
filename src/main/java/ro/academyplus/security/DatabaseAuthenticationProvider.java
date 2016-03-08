package ro.academyplus.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ro.academyplus.model.User;
import ro.academyplus.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ghebo on 1/6/2016.
 */
@Service
public class DatabaseAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserRepository userRepository;

    @Override
    protected void additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        boolean valid = true;

        final String password = (String) usernamePasswordAuthenticationToken.getCredentials();
        if (!StringUtils.hasText(password)) {
            this.logger.warn("Username {}: no password provided", userName);
            valid = false;
        }

        User user = userRepository.findOneByEmail(userName);

        if (user == null) {
            this.logger.warn("Username {}: user not found", userName);
            valid = false;
        } else {
            // Check password
            if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
                this.logger.warn("Username {}: bad password entered", userName);
                valid = false;
            }
        }

        if (!valid) {
            throw new BadCredentialsException("Invalid Username/Password for user " + userName);
        }
        final List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        // enabled, account not expired, credentials not expired, account not locked
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(userName, password, true, true, true, true, auths);
        return userDetails;
    }
}
