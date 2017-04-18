package pl.com.tokarzewski.converters.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.com.tokarzewski.converters.tasktype.TaskTypeDtoToEntity;
import pl.com.tokarzewski.converters.user.UserDtoToUser;
import pl.com.tokarzewski.domain.Task;
import pl.com.tokarzewski.dto.TaskDto;

@Component
public class TaskDtoToEntity implements Converter<TaskDto, Task> {

    private TaskTypeDtoToEntity taskTypeDtoToEntity;
    private UserDtoToUser userDtoToUser;

    @Override
    public Task convert(TaskDto dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setType(taskTypeDtoToEntity.convert(dto.getType()));
        task.setStartDate(dto.getStartDate());
        task.setPriority(dto.getPriority());
        task.setOwner(userDtoToUser.convert(dto.getOwner()));

        return task;
    }
    @Autowired
    public void setTaskTypeDtoToEntity(TaskTypeDtoToEntity taskTypeDtoToEntity) {
        this.taskTypeDtoToEntity = taskTypeDtoToEntity;
    }
    @Autowired
    public void setUserDtoToUser(UserDtoToUser userDtoToUser) {
        this.userDtoToUser = userDtoToUser;
    }
}
