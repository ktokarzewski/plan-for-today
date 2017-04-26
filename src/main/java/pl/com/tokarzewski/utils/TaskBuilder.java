package pl.com.tokarzewski.utils;

import pl.com.tokarzewski.domain.Priority;
import pl.com.tokarzewski.domain.Task;
import pl.com.tokarzewski.domain.TaskType;
import pl.com.tokarzewski.domain.User;

import java.util.Date;

public class TaskBuilder {
    private User owner;
    private String description;
    private TaskType type;
    private Date startDate;
    private boolean done;
    private boolean expired;
    private Priority priority;

    public TaskBuilder setOwner(User user){
        this.owner = user;
        return this;
    }
    public TaskBuilder setDescription(String description){
        this.description = description;
        return this;
    }
    public TaskBuilder setType(TaskType taskType){
        this.type = taskType;
        return this;
    }
    public TaskBuilder setStartDate(Date date){
        this.startDate = date;
        return this;
    }
    public TaskBuilder setDone(boolean done){
        this.done = done;
        return this;
    }

    public TaskBuilder setExpired(boolean expired){
        this.expired = expired;
        return this;
    }
    public TaskBuilder setPriority(Priority priority){
        this.priority = priority;
        return this;
    }

    public Task build(){
        Task task = new Task();
        task.setOwner(owner);
        task.setPriority(priority);
        task.setType(type);
        task.setDescription(description);
        task.setExpired(expired);
        task.setDone(done);
        task.setStartDate(startDate);
        return task;
    }
    private TaskBuilder(){}
    public static TaskBuilder get(){
        return new TaskBuilder();
    }

}
