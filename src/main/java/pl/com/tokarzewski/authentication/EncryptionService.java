package pl.com.tokarzewski.authentication;

public interface EncryptionService {
    String encrypt(String pass);

    boolean checkPassword(String plain, String encrypted);
}
