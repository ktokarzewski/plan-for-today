package pl.com.tokarzewski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.tokarzewski.domain.Locale;

public interface LocaleRepository extends JpaRepository<Locale, Long> {
}
