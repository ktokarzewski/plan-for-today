package pl.com.tokarzewski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.tokarzewski.domain.Task;
import pl.com.tokarzewski.domain.TaskType;
import pl.com.tokarzewski.domain.User;

import java.util.Collection;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Collection<Task> findAllByOwner(User user);

    Collection<Task> findAllByOwnerAndDone(User user, boolean done);

    Collection<Task> findAllByOwnerAndExpired(User user, boolean expired);

    Collection<Task> findAllByExpired(boolean expired);

    Collection<Task> findAllByOwnerAndDoneAndExpired(User user, boolean done, boolean expired);

    Collection<Task> findAllByExpiredAndType(boolean expired, TaskType type);

    Collection<Task> findAllByType(TaskType type);

    Collection<Task> findAllByOwnerAndDoneAndType(User user, boolean done, TaskType type);

    Collection<Task> findAllByOwnerAndType(User user, TaskType cyclicTask);

    Collection<Task> findAllByOwnerAndDoneAndExpiredAndType(User user, boolean done, boolean expired, TaskType type);
}
