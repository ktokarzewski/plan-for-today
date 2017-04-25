package pl.com.tokarzewski.api;

import pl.com.tokarzewski.domain.Task;
import pl.com.tokarzewski.domain.TaskType;
import pl.com.tokarzewski.domain.User;

import java.util.Collection;

public interface TaskService extends CRUDService<Task> {
    Collection<Task> getUserTasks(User user);

    Collection<Task> getTasksToComplete(User user);

    Collection<Task> getTasksByType(TaskType taskType);

    Task create(Task task);

    void completeTask(long taskId);

    Collection<Task> getActiveTasks(User user);

    void saveAll(Collection<Task> tasks);

    Collection<Task> getExpiredTasks(TaskType type);

    void deleteAllUserTasks(User user);

    Collection<Task> getCompletedTasks(User user);
}
