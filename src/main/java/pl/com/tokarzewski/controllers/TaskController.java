package pl.com.tokarzewski.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.com.tokarzewski.api.PriorityService;
import pl.com.tokarzewski.api.TaskService;
import pl.com.tokarzewski.api.TaskTypeService;
import pl.com.tokarzewski.domain.Task;
import pl.com.tokarzewski.domain.User;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Locale;

@Controller
public class TaskController {
    private Logger logger = LoggerFactory.getLogger(TaskController.class);
    private TaskService taskService;
    private TaskTypeService taskTypeService;
    private PriorityService priorityService;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String tasks(@ModelAttribute User user, Model model, Locale locale) {
        Collection<Task> tasks =
                taskService
                        .getTasksToComplete(user);

        model.addAttribute("tasks", tasks);
        model.addAttribute("priorities", priorityService.findLocaleLabels(locale));
        return "tasks";
    }

    @RequestMapping(value = "/tasks/manage", method = RequestMethod.GET)
    public String manageTasks(@ModelAttribute User user, Model model, Locale locale) {
        Collection<Task> tasks =
                taskService
                        .getActiveTasks(user);
        model.addAttribute("tasks", tasks);
        model.addAttribute("priorities", priorityService.findLocaleLabels(locale));
        return "tasks-manage";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editTask(@PathVariable("id") long id, Model model, Locale locale) {
        model.addAttribute("task", taskService.getById(id));
        model.addAttribute("types", taskTypeService.findLocaleLabels(locale));
        model.addAttribute("priorities", priorityService.findLocaleLabels(locale));

        return "edit-task";
    }

    @RequestMapping(value = "/task/delete")
    public String deleteTask(@ModelAttribute("task") Task task) {
        taskService.delete(task.getId());
        return "redirect:/tasks";
    }


    @RequestMapping(value = "/add")
    public String addNewTask(Model model, Locale locale) {
        model.addAttribute("task", new Task());
        model.addAttribute("types", taskTypeService.findLocaleLabels(locale));
        model.addAttribute("priorities", priorityService.findLocaleLabels(locale));

        return "add-task";
    }

    @RequestMapping(value = "/task/save")
    public String addNewTask(
            @ModelAttribute("task") @Valid Task task,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("types", taskTypeService.findAll());
            model.addAttribute("priorities", priorityService.findAll());
            return "add-task";
        }

        taskService.create(task);
        return "redirect:/tasks";
    }

    @RequestMapping(value = "/tasks/completed")
    public String showCompletedTasks(Model model, @ModelAttribute User user, Locale locale){
        model.addAttribute("tasks",taskService.getCompletedTasks(user));
        model.addAttribute("types", taskTypeService.findLocaleLabels(locale));
        model.addAttribute("priorities", priorityService.findLocaleLabels(locale));
        return "completed";
    }

    @RequestMapping(value = "/task/update")
    public String updateTask(
            @ModelAttribute("task") Task task) {

        taskService.update(task);
        return "redirect:/tasks/manage";
    }

    @RequestMapping(value = "/complete")
    public String completeTask(@ModelAttribute("task") Task task) {
        taskService.completeTask(task.getId());
        return "redirect:/tasks";
    }


    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
    @Autowired
    public void setTaskTypeService(TaskTypeService taskTypeService) {
        this.taskTypeService = taskTypeService;
    }
    @Autowired
    public void setPriorityService(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

}
