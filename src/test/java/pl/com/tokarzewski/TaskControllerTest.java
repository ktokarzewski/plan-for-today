package pl.com.tokarzewski;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.com.tokarzewski.api.PriorityService;
import pl.com.tokarzewski.api.TaskService;
import pl.com.tokarzewski.api.TaskTypeService;
import pl.com.tokarzewski.controllers.TaskController;
import pl.com.tokarzewski.domain.Task;
import pl.com.tokarzewski.utils.TaskBuilder;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {
    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;
    @Mock
    private PriorityService priorityService;
    @Mock
    private TaskTypeService taskTypeService;

    @InjectMocks
    private TaskController controller;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void shouldShowAllUncompletedTasks() throws Exception {
        Task task1 = TaskBuilder.get()
                .setDescription("Task1")
                .build();
        Task task2 = TaskBuilder.get()
                .setDescription("Task2")
                .build();

        when(taskService.getTasksToComplete(any())).thenReturn(Arrays.asList(task1, task2));
        when(priorityService.findLocaleLabels(any())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/tasks/"))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks"))
                .andExpect(model().attribute("tasks", Matchers.hasSize(2)))
                .andExpect(model().attribute("tasks", Matchers.contains(task1, task2)))
                .andExpect(model().attribute("priorities", Matchers.hasSize(0)));
    }

    @Test
    public void shouldShowAllActiveTasks() throws Exception {

        Task task1 = TaskBuilder.get()
                .setDescription("Task1")
                .build();
        Task task2 = TaskBuilder.get()
                .setDescription("Task2")
                .build();

        when(taskService.getActiveTasks(any())).thenReturn(Arrays.asList(task1, task2));
        when(priorityService.findLocaleLabels(any())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/tasks/manage"))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks-manage"))
                .andExpect(model().attribute("tasks", Matchers.hasSize(2)))
                .andExpect(model().attribute("tasks", Matchers.contains(task1, task2)))
                .andExpect(model().attribute("priorities", Matchers.hasSize(0)));
    }

    @Test
    public void shouldGetTaskToEdit() throws Exception {

        Task task1 = TaskBuilder.get()
                .setDescription("Task1")
                .build();

        when(taskService.getById(1L)).thenReturn(task1);
        when(priorityService.findLocaleLabels(any())).thenReturn(Collections.emptyList());
        when(taskTypeService.findLocaleLabels(any())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/task/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-task"))
                .andExpect(model().attribute("task", Matchers.equalTo(task1)))
                .andExpect(model().attribute("priorities", Matchers.hasSize(0)))
                .andExpect(model().attribute("types", Matchers.hasSize(0)));


    }

    @Test
    public void shouldCreateNewTask() throws Exception {


        when(priorityService.findLocaleLabels(any())).thenReturn(Collections.emptyList());
        when(taskTypeService.findLocaleLabels(any())).thenReturn(Collections.emptyList());


        mockMvc.perform(get("/task/add/"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-task"))
                .andExpect(model().attribute("task", Matchers.any(Task.class)))
                .andExpect(model().attribute("priorities", Matchers.hasSize(0)))
                .andExpect(model().attribute("types", Matchers.hasSize(0)));


    }

    @Test
    public void shouldSaveCreatedTask() throws Exception {

        when(priorityService.findLocaleLabels(any())).thenReturn(Collections.emptyList());
        when(taskTypeService.findLocaleLabels(any())).thenReturn(Collections.emptyList());

        mockMvc.perform(post("/task/save/").param("description", "task"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/tasks"))
                .andExpect(model().attribute("task", Matchers.any(Task.class)));

        mockMvc.perform(post("/task/save"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-task"))
                .andExpect(model().attribute("priorities", Matchers.hasSize(0)))
                .andExpect(model().attribute("types", Matchers.hasSize(0)));
    }

    @Test
    public void shouldShowCompletedTasks() throws Exception {


        Task task1 = TaskBuilder.get()
                .setDescription("Task1")
                .build();
        Task task2 = TaskBuilder.get()
                .setDescription("Task2")
                .build();

        when(taskService.getCompletedTasks(any())).thenReturn(Arrays.asList(task1, task2));
        when(priorityService.findLocaleLabels(any())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/tasks/completed/"))
                .andExpect(status().isOk())
                .andExpect(view().name("completed"))
                .andExpect(model().attribute("tasks", Matchers.hasSize(2)))
                .andExpect(model().attribute("tasks", Matchers.contains(task1, task2)))
                .andExpect(model().attribute("priorities", Matchers.hasSize(0)));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        mockMvc.perform(post("/task/update"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/tasks/manage"))
                .andExpect(model().attribute("task",Matchers.notNullValue()));
    }
    @Test
    public void shouldCompleteTask() throws Exception {
        mockMvc.perform(post("/task/complete"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/tasks"))
                .andExpect(model().attribute("task",Matchers.notNullValue()));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        mockMvc.perform(post("/task/delete"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/tasks"))
                .andExpect(model().attribute("task",Matchers.notNullValue()));
    }


}
