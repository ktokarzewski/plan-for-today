package pl.com.tokarzewski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.tokarzewski.domain.TypeLabel;

import java.util.Collection;

public interface TypeLabelRepository extends JpaRepository<TypeLabel, Long> {
    Collection<TypeLabel> findAllByLanguage(String language);
}
