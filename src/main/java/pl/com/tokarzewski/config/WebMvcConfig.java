package pl.com.tokarzewski.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        CustomLocaleResolver localeResolver = new CustomLocaleResolver(resolver);
        localeResolver.setDefaultLocale(Locale.ENGLISH);

        return localeResolver;
    }

}
