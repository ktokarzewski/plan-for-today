package pl.com.tokarzewski.dto;


import pl.com.tokarzewski.domain.Priority;

import java.util.Date;

public class TaskDto {
    private long id;
    private String description;
    private Priority priority;
    private TaskTypeDto type;
    private Date startDate;
    private UserDto owner;

    public UserDto getOwner() {

        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TaskTypeDto getType() {
        return type;
    }

    public void setType(TaskTypeDto type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
