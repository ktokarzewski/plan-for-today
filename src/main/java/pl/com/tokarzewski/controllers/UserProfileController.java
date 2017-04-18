package pl.com.tokarzewski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tokarzewski.api.TaskService;
import pl.com.tokarzewski.api.UserService;
import pl.com.tokarzewski.converters.user.UserDtoToUser;
import pl.com.tokarzewski.converters.user.UserToUserDto;
import pl.com.tokarzewski.domain.User;
import pl.com.tokarzewski.dto.UserDto;
import pl.com.tokarzewski.api.ScoreService;

@Controller
@RequestMapping("/profile")
public class UserProfileController {

    private ScoreService scoreService;
    private TaskService taskService;
    private UserToUserDto userToUserDto;
    private UserDtoToUser dtoToUser;
    private UserService userService;

    @RequestMapping
    public String getProfileDetails(@ModelAttribute User user, Model model) {
        model.addAttribute("score", scoreService.getUserScore(user));
        model.addAttribute("progress", taskService.getTodayProgress(user));
        model.addAttribute("userToEdit", userToUserDto.convert(user));
        return "profile";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("userToEdit") UserDto userDto) {
        userService.updateUser(dtoToUser.convert(userDto));
        return "redirect:";
    }

    @RequestMapping(value = "/password/change")
    public String changePassword(@ModelAttribute User user,
                                 @RequestParam(value = "error", required = false) String error,
                                 Model model) {
        model.addAttribute("error", error);
        model.addAttribute("userToEdit", userToUserDto.convert(user));
        return "password-change";
    }

    @RequestMapping(value = "/password/update", method = RequestMethod.POST)
    public String updatePassword(@ModelAttribute("userToEdit") UserDto user) {
        if (user.getPassword().equals(user.getPasswordConfirmation())) {
            userService.updateUser(dtoToUser.convert(user));
            return "redirect:/profile";
        } else {
            return "redirect:/profile/password/change?error";
        }

    }
    @Autowired
    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }
    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
    @Autowired
    public void setUserToUserDto(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }
    @Autowired
    public void setDtoToUser(UserDtoToUser dtoToUser) {
        this.dtoToUser = dtoToUser;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
