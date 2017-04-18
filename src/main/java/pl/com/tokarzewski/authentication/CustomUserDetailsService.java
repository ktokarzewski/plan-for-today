package pl.com.tokarzewski.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.com.tokarzewski.api.UserService;
import pl.com.tokarzewski.converters.user.UserToUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserToUserDetails userToUserDetails;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userToUserDetails.convert(userService.findByEmail(s));
    }
}
