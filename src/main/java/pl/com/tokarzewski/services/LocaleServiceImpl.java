package pl.com.tokarzewski.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.tokarzewski.api.LocaleService;
import pl.com.tokarzewski.dao.LocaleRepository;

import java.util.Locale;
import java.util.spi.LocaleServiceProvider;
import java.util.stream.Collectors;


@Service
public class LocaleServiceImpl implements LocaleService {

    private LocaleRepository localeRepository;

    @Override
    public boolean isSupported(Locale locale) {
        return localeRepository
                .findAll()
                .stream()
                .map(pl.com.tokarzewski.domain.Locale::getCode)
                .collect(Collectors.toList())
                .contains(locale.getLanguage());
    }

    @Override
    public void save(pl.com.tokarzewski.domain.Locale locale) {
        localeRepository.save(locale);
    }

    @Override
    public void save(Locale locale){
        pl.com.tokarzewski.domain.Locale l = new pl.com.tokarzewski.domain.Locale();
        l.setCode(locale.getLanguage());
        l.setLanguage(locale.getDisplayLanguage());
        localeRepository.save(l);
    }

    @Autowired
    public void setLocaleRepository(LocaleRepository localeRepository) {
        this.localeRepository = localeRepository;
    }
}
