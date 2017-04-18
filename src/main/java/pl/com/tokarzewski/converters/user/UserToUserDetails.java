package pl.com.tokarzewski.converters.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.com.tokarzewski.authentication.CustomUserDetails;
import pl.com.tokarzewski.domain.User;

import java.util.stream.Collectors;

@Component
public class UserToUserDetails implements Converter<User, UserDetails> {
    @Override
    public UserDetails convert(User user) {
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setPassword(user.getEncryptedPassword());
        userDetails.setUsername(user.getEmail());
        userDetails.setAuthorities(user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList()));

        return userDetails;
    }
}
