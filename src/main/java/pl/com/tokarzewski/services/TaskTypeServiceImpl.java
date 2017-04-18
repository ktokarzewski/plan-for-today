package pl.com.tokarzewski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.com.tokarzewski.api.TaskTypeService;
import pl.com.tokarzewski.dao.TaskTypeRepository;
import pl.com.tokarzewski.dao.TypeLabelRepository;
import pl.com.tokarzewski.domain.TaskType;
import pl.com.tokarzewski.domain.TypeLabel;

import java.util.Collection;
import java.util.Locale;

@Service
@Profile("database")
public class TaskTypeServiceImpl implements TaskTypeService {
    
    private TaskTypeRepository taskTypeRepository;
    private TypeLabelRepository typeLabelRepository;

    @Override
    public Collection<TaskType> findAll() {
        return taskTypeRepository.findAll();
    }

    @Override
    public Collection<TypeLabel> findLocaleLabels(Locale locale) {
        return typeLabelRepository.findAllByLanguage(locale.getLanguage());
    }

    @Override
    public void saveType(TaskType type) {
        taskTypeRepository.save(type);
    }

    @Override
    public void saveLabel(TypeLabel label) {
        typeLabelRepository.save(label);
    }

    @Override
    public TaskType getCyclicTask() {
        return taskTypeRepository.findByType("Cyclic");
    }

    @Override
    public TaskType getSingleTask() {
        return taskTypeRepository.findByType("Single");
    }

    @Autowired
    public void setTaskTypeRepository(TaskTypeRepository taskTypeRepository) {
        this.taskTypeRepository = taskTypeRepository;
    }

    @Autowired
    public void setTypeLabelRepository(TypeLabelRepository typeLabelRepository) {
        this.typeLabelRepository = typeLabelRepository;
    }
}
