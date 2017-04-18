package pl.com.tokarzewski.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public String encrypt(String pass) {
        return encoder.encode(pass);
    }

    @Override
    public boolean checkPassword(String plain, String encrypted) {
        return encoder.encode(plain).equals(encrypted);
    }
}
