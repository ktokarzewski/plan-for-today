package pl.com.tokarzewski.converters.tasktype;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.com.tokarzewski.domain.TaskType;
import pl.com.tokarzewski.dto.TaskTypeDto;

@Component
public class TaskTypeDtoToEntity implements Converter<TaskTypeDto, TaskType> {
    @Override
    public TaskType convert(TaskTypeDto taskTypeDto) {
        TaskType taskType = new TaskType();
        taskType.setId(taskTypeDto.getId());
        taskType.setType(taskTypeDto.getType());
        return taskType;
    }
}
