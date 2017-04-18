package pl.com.tokarzewski.api;


import pl.com.tokarzewski.domain.Priority;
import pl.com.tokarzewski.domain.PriorityLabel;

import java.util.Collection;
import java.util.Locale;

public interface PriorityService {
    Collection<Priority> findAll();

    Collection<PriorityLabel> findLocaleLabels(Locale locale);

    void savePriority(Priority priority);

    void saveLabel(PriorityLabel label);

    void save(Collection<Priority> priorities);
}
