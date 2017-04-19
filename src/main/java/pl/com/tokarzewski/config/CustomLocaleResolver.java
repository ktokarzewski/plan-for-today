package pl.com.tokarzewski.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import pl.com.tokarzewski.api.LocaleService;
import pl.com.tokarzewski.controllers.TaskController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class CustomLocaleResolver implements LocaleResolver{
    private Logger logger = LoggerFactory.getLogger(CustomLocaleResolver.class);

    private LocaleService localeService;
    private LocaleResolver resolver;
    private Locale defaultLocale = Locale.getDefault();

    public CustomLocaleResolver(LocaleResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        Locale locale = resolver.resolveLocale(httpServletRequest);
        if (!localeService.isSupported(locale)) {
            locale = defaultLocale;
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
        resolver.setLocale(httpServletRequest, httpServletResponse, locale);
    }

    @Autowired
    public void setLocaleService(LocaleService localeService) {
        this.localeService = localeService;
    }

    public void setDefaultLocale(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }
}
