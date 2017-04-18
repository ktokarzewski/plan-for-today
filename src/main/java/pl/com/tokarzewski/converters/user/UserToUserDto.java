package pl.com.tokarzewski.converters.user;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.com.tokarzewski.domain.User;
import pl.com.tokarzewski.dto.UserDto;

@Component
public class UserToUserDto implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }
}
