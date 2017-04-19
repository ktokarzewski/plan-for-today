package pl.com.tokarzewski.api;

import java.util.Locale;

public interface LocaleService {
    boolean isSupported(Locale locale);

    void save(pl.com.tokarzewski.domain.Locale locale);

    void save(Locale locale);
}
