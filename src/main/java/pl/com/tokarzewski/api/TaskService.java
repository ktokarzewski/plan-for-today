package pl.com.tokarzewski.api;

import pl.com.tokarzewski.domain.Task;
import pl.com.tokarzewski.domain.TaskType;
import pl.com.tokarzewski.domain.User;

import java.util.Collection;

public interface TaskService {
    Collection<Task> getUserTasks(User user);

    Collection<Task> getTasksToComplete(User user);

    Collection<Task> getTasksByType(TaskType taskType);

    void create(Task task);

    void completeTask(long taskId);

    Task getTaskById(long id);

    void deleteTask(long id);

    void updateTask(Task task);

    Collection<Task> getActiveTasks(User user);

    void saveAll(Collection<Task> tasks);

    Collection<Task> getExpiredTasks(TaskType type);

    void deleteAllUserTasks(User user);
}
