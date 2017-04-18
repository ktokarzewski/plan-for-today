package pl.com.tokarzewski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.tokarzewski.domain.PriorityLabel;

import java.util.Collection;

public interface PriorityLabelRepository extends JpaRepository<PriorityLabel, Long> {
    Collection<PriorityLabel> findAllByLanguage(String language);

    Collection<PriorityLabel> findAllByLanguageOrderByIdAsc(String language);
}
