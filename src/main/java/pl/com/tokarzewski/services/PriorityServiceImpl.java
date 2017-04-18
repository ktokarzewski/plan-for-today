package pl.com.tokarzewski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.com.tokarzewski.api.PriorityService;
import pl.com.tokarzewski.dao.PriorityLabelRepository;
import pl.com.tokarzewski.dao.PriorityRepository;
import pl.com.tokarzewski.domain.Priority;
import pl.com.tokarzewski.domain.PriorityLabel;

import java.util.Collection;
import java.util.Locale;

@Profile("database")
@Service
public class PriorityServiceImpl implements PriorityService {

    private PriorityRepository priorityRepository;
    private PriorityLabelRepository priorityLabelRepository;

    @Override
    public Collection<Priority> findAll() {
        return priorityRepository.findAll();
    }

    @Override
    public Collection<PriorityLabel> findLocaleLabels(Locale locale) {
        return priorityLabelRepository.findAllByLanguageOrderByIdAsc(locale.getLanguage());
    }

    @Override
    public void savePriority(Priority priority) {
        priorityRepository.save(priority);
    }

    @Override
    public void saveLabel(PriorityLabel label) {
        priorityLabelRepository.save(label);
    }

    @Override
    public void save(Collection<Priority> priorities) {
        priorityRepository.save(priorities);
    }

    @Autowired
    public void setPriorityRepository(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Autowired
    public void setPriorityLabelRepository(PriorityLabelRepository priorityLabelRepository) {
        this.priorityLabelRepository = priorityLabelRepository;
    }
}
