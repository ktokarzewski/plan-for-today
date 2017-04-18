package pl.com.tokarzewski.converters.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.com.tokarzewski.domain.User;
import pl.com.tokarzewski.dto.UserDto;

@Component
public class UserDtoToUser implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setPassword(dto.getPassword());
        dto.setPassword(null);
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }
}
