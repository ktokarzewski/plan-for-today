package pl.com.tokarzewski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.com.tokarzewski.api.UserService;
import pl.com.tokarzewski.authentication.CurrentUser;
import pl.com.tokarzewski.domain.Score;
import pl.com.tokarzewski.domain.User;
import pl.com.tokarzewski.api.ScoreService;

@ControllerAdvice(basePackageClasses = {TaskController.class, AdminController.class, UserProfileController.class})
public class UserAdvice {

    private UserService userService;
    private ScoreService scoreService;

    @ModelAttribute
    public void addUserAttribute(@CurrentUser UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername());
        Score score = scoreService.getUserScore(user);
        model.addAttribute("user", user);
        model.addAttribute("dailyScore", score.getDailyScore());

    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }
}
