package pl.com.tokarzewski.api;

import pl.com.tokarzewski.domain.TaskType;
import pl.com.tokarzewski.domain.TypeLabel;

import java.util.Collection;
import java.util.Locale;

public interface TaskTypeService {
    Collection<TaskType> findAll();

    Collection<TypeLabel> findLocaleLabels(Locale locale);

    void saveType(TaskType type);

    void saveLabel(TypeLabel label);

    TaskType getCyclicTask();

    TaskType getSingleTask();
}
