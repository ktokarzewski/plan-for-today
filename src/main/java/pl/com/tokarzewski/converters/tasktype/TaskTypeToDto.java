package pl.com.tokarzewski.converters.tasktype;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.com.tokarzewski.domain.TaskType;
import pl.com.tokarzewski.dto.TaskTypeDto;

@Component
public class TaskTypeToDto implements Converter<TaskType, TaskTypeDto> {
    @Override
    public TaskTypeDto convert(TaskType taskType) {
        TaskTypeDto dto = new TaskTypeDto();
        dto.setId(taskType.getId());
        dto.setType(taskType.getType());

        return dto;
    }
}
