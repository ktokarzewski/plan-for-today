package pl.com.tokarzewski.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.com.tokarzewski.api.ScoreService;
import pl.com.tokarzewski.api.TaskService;
import pl.com.tokarzewski.api.TaskTypeService;
import pl.com.tokarzewski.dao.TaskRepository;
import pl.com.tokarzewski.domain.Score;
import pl.com.tokarzewski.domain.Task;
import pl.com.tokarzewski.domain.TaskType;
import pl.com.tokarzewski.domain.User;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Profile("database")
public class TaskServiceImpl implements TaskService {
    private Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    private TaskRepository taskRepository;
    private ScoreService scoreService;
    private TaskTypeService taskTypeService;

    @Override
    public Collection<Task> getUserTasks(User user) {
        return taskRepository.findAllByOwner(user);
    }

    @Override
    public Collection<Task> getTasksToComplete(User user) {
        return taskRepository.findAllByOwnerAndDoneAndExpired(user, false, false);
    }

    @Override
    public Collection<Task> getTasksByType(TaskType taskType) {
        return taskRepository.findAllByType(taskType);
    }

    @Override
    public Task create(Task task) {
        scoreService.increaseMaxForToday(task.getOwner(), task.getPriority().getPoints());
        return taskRepository.save(task);
    }

    @Transactional
    @Override
    public void completeTask(long id) {
        Task task = taskRepository.findOne(id);
        task.setDone(true);
        int points = task.getPriority().getPoints();
        scoreService.updateUserScore(task.getOwner(), points);
        taskRepository.save(task);
    }

    @Override
    public Collection<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getById(long id) {
        return taskRepository.findOne(id);
    }

    @Override
    public void delete(long id) {
        Task task = taskRepository.findOne(id);
        if(!task.isDone()){
            scoreService.decreaseMaxForToday(task.getOwner(),task.getPriority().getPoints());
        }
        taskRepository.delete(task);
    }

    @Override
    public Task update(Task task) {
        Task t = taskRepository.findOne(task.getId());
        t.setPriority(task.getPriority());
        t.setType(task.getType());
        t.setDescription(task.getDescription());
        return taskRepository.save(t);
    }

    @Transactional
    @Override
    public Collection<Task> getActiveTasks(User user) {
        Collection<Task> cyclicalTasks =
                taskRepository.findAllByOwnerAndType(user, taskTypeService.getCyclicTask());
        Collection<Task> singleTasks
                = taskRepository.findAllByOwnerAndDoneAndExpiredAndType(user, false, false, taskTypeService.getSingleTask());
        cyclicalTasks.addAll(singleTasks);
        return cyclicalTasks;
    }

    @Override
    public void saveAll(Collection<Task> tasks) {
        taskRepository.save(tasks);
    }

    @Override
    public Collection<Task> getExpiredTasks(TaskType singleTask) {
        return taskRepository.findAllByExpiredAndType(false,singleTask);
    }

    @Transactional
    @Override
    public void deleteAllUserTasks(User user) {
        taskRepository.delete(getUserTasks(user));
    }

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Autowired
    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }
    @Autowired
    public void setTaskTypeService(TaskTypeService taskTypeService) {
        this.taskTypeService = taskTypeService;
    }
}

