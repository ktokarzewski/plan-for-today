package pl.com.tokarzewski.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.com.tokarzewski.api.DayFinalizerService;
import pl.com.tokarzewski.api.ScoreService;
import pl.com.tokarzewski.api.TaskService;
import pl.com.tokarzewski.api.TaskTypeService;
import pl.com.tokarzewski.domain.Score;
import pl.com.tokarzewski.domain.Task;
import pl.com.tokarzewski.domain.TaskType;

import javax.transaction.Transactional;
import java.util.Collection;

@Component
public class DayFinalizerServiceImpl implements DayFinalizerService {

    private Logger logger = LoggerFactory.getLogger(DayFinalizerServiceImpl.class);
    private TaskService taskService;

    private ScoreService scoreRepository;

    private TaskTypeService taskTypeService;

    @Override
    @Scheduled(cron = EVERY_DAY_AT_MIDNIGHT)
    public void finalizeDay() {
        expireAllTasks();
        renewCyclicTasks();
        resetDailyScore();
        logger.info("Day finalized");
    }

    @Transactional
    private void expireAllTasks() {
        TaskType singleTask = taskTypeService.getSingleTask();

        Collection<Task> tasks = taskService.
                getExpiredTasks(singleTask);
        tasks.forEach(task -> task.setExpired(true));
        taskService.saveAll(tasks);
    }

    @Transactional
    private void renewCyclicTasks() {
        Collection<Task> cyclicTasks = taskService.getTasksByType(taskTypeService.getCyclicTask());
        cyclicTasks.forEach(task -> task.setDone(false));
        taskService.saveAll(cyclicTasks);
    }

    @Transactional
    private void resetDailyScore() {
        Collection<Score> allScores = scoreRepository.findAll();
        allScores.forEach(score -> score.setDailyScore(0));
        scoreRepository.updateAll(allScores);
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
    @Autowired
    public void setScoreRepository(ScoreService scoreRepository) {
        this.scoreRepository = scoreRepository;
    }
    @Autowired
    public void setTaskTypeService(TaskTypeService taskTypeService) {
        this.taskTypeService = taskTypeService;
    }
}
