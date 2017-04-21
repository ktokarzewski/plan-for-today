package pl.com.tokarzewski.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.com.tokarzewski.api.*;
import pl.com.tokarzewski.authentication.EmailExistException;
import pl.com.tokarzewski.domain.*;
import pl.com.tokarzewski.services.LocaleServiceImpl;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

@ConfigurationProperties
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${admin.password}")
    private String adminPassword;
    @Value("${admin.login}")
    private String adminLogin;
    @Value("${bootstrap.init.database}")
    private boolean initialize;

    private RoleService roleService;
    private UserService userService;
    private TaskTypeService taskTypeService;
    private PriorityService priorityService;
    private LocaleServiceImpl localeService;

    private TaskService taskService;

    private Role adminRole;
    private Role userRole;
    private User adminUser;
    private User commonUser;

    private Priority low;
    private Priority medium;
    private Priority high;
    private TaskType oneTime;
    private TaskType cyclicTask;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (initialize) {
            setupUsers();
            createPriorities();
            createPriorityLabels();
            createTaskTypes();
            createTaskLabels();
            createAdminTask();
            createUserTask();
            createLocale();
        }
    }

    private void setupUsers() {
        createRoles();

        createAdminUser();
        createCommonUser();

        saveUsers();
    }

    private void createRoles() {
        adminRole = roleService.createIfNotExist("ROLE_ADMIN");
        userRole = roleService.createIfNotExist("ROLE_USER");
    }

    private void createAdminUser() {
        User user = new User();
        user.setEmail(adminLogin);
        user.setFirstName("Adam");
        user.setLastName("Admin");
        user.setPassword(adminPassword);
        user.setRoles(Arrays.asList(adminRole, userRole));
        adminUser = user;
    }

    private void createCommonUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@doe.com");
        user.getRoles().add(userRole);
        user.setPassword("password");
        commonUser = user;
    }

    private void saveUsers() {
        try {
            userService.create(adminUser);
            userService.createUserAccount(commonUser);
        } catch (EmailExistException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    private void createPriorities() {
        low = new Priority();
        low.setPoints(1);
        low.setPriority("Low");

        medium = new Priority();
        medium.setPoints(3);
        medium.setPriority("Medium");

        high = new Priority();
        high.setPoints(5);
        high.setPriority("High");

        priorityService.save(Arrays.asList(low, medium, high));
    }

    private void createPriorityLabels() {
        PriorityLabel lowPl = new PriorityLabel();
        lowPl.setLabel("Niski");
        lowPl.setLanguage("pl");
        lowPl.setPriority(low);
        priorityService.saveLabel(lowPl);

        PriorityLabel lowEn = new PriorityLabel();
        lowEn.setLabel("Low");
        lowEn.setLanguage("en");
        lowEn.setPriority(low);
        priorityService.saveLabel(lowEn);

        PriorityLabel mediumPl = new PriorityLabel();
        mediumPl.setLabel("Średni");
        mediumPl.setLanguage("pl");
        mediumPl.setPriority(medium);
        priorityService.saveLabel(mediumPl);

        PriorityLabel mediumEn = new PriorityLabel();
        mediumEn.setLabel("Medium");
        mediumEn.setLanguage("en");
        mediumEn.setPriority(medium);
        priorityService.saveLabel(mediumEn);

        PriorityLabel highPl = new PriorityLabel();
        highPl.setLabel("Wysoki");
        highPl.setLanguage("pl");
        highPl.setPriority(high);
        priorityService.saveLabel(highPl);

        PriorityLabel highEn = new PriorityLabel();
        highEn.setLabel("High");
        highEn.setLanguage("en");
        highEn.setPriority(high);
        priorityService.saveLabel(highEn);
    }

    @Transactional
    private void createTaskTypes() {
        oneTime = new TaskType();
        oneTime.setType("Single");
        taskTypeService.saveType(oneTime);

        cyclicTask = new TaskType();
        cyclicTask.setType("Cyclic");
        taskTypeService.saveType(cyclicTask);
    }

    private void createTaskLabels() {

        TypeLabel cyclicLabelPl = new TypeLabel();
        cyclicLabelPl.setLabel("Cykliczne");
        cyclicLabelPl.setLanguage("pl");
        cyclicLabelPl.setType(cyclicTask);
        taskTypeService.saveLabel(cyclicLabelPl);

        TypeLabel cyclicLabelEn = new TypeLabel();
        cyclicLabelEn.setLabel("Cyclical");
        cyclicLabelEn.setLanguage("en");
        cyclicLabelEn.setType(cyclicTask);
        taskTypeService.saveLabel(cyclicLabelEn);

        TypeLabel oneTimeLabelPl = new TypeLabel();
        oneTimeLabelPl.setLabel("Jednorazowe");
        oneTimeLabelPl.setLanguage("pl");
        oneTimeLabelPl.setType(oneTime);
        taskTypeService.saveLabel(oneTimeLabelPl);

        TypeLabel oneTimeLabelEn = new TypeLabel();
        oneTimeLabelEn.setLabel("Single");
        oneTimeLabelEn.setLanguage("en");
        oneTimeLabelEn.setType(oneTime);
        taskTypeService.saveLabel(oneTimeLabelEn);
    }

    private void createAdminTask() {
        Task task = new Task();
        task.setOwner(adminUser);
        task.setDescription("Sample task");
        task.setPriority(medium);
        task.setType(oneTime);
        task.setDone(false);
        task.setExpired(false);
        task.setStartDate(new Date());

        taskService.create(task);
    }

    private void createUserTask() {
        Task task = new Task();
        task.setOwner(commonUser);
        task.setDescription("Pierwsze zadanie");
        task.setPriority(medium);
        task.setType(oneTime);
        task.setDone(false);
        task.setExpired(false);
        task.setStartDate(new Date());

        taskService.create(task);
    }

    private void createLocale() {
        localeService.save(Locale.ENGLISH);
        localeService.save(new Locale("pl"));
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTaskTypeService(TaskTypeService taskTypeService) {
        this.taskTypeService = taskTypeService;
    }

    @Autowired
    public void setPriorityService(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setLocaleService(LocaleServiceImpl localeService) {
        this.localeService = localeService;
    }
}
