package pl.com.tokarzewski.converters.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.com.tokarzewski.converters.tasktype.TaskTypeToDto;
import pl.com.tokarzewski.converters.user.UserToUserDto;
import pl.com.tokarzewski.domain.Task;
import pl.com.tokarzewski.dto.TaskDto;

@Component
public class TaskToDto implements Converter<Task, TaskDto> {

    private TaskTypeToDto taskTypeToDto;
    private UserToUserDto userToUserDto;

    @Override
    public TaskDto convert(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setDescription(task.getDescription());
        dto.setStartDate(task.getStartDate());
        dto.setPriority(task.getPriority());
        dto.setType(taskTypeToDto.convert(task.getType()));
        dto.setOwner(userToUserDto.convert(task.getOwner()));
        return dto;
    }
    @Autowired
    public void setTaskTypeToDto(TaskTypeToDto taskTypeToDto) {
        this.taskTypeToDto = taskTypeToDto;
    }
    @Autowired
    public void setUserToUserDto(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }
}
