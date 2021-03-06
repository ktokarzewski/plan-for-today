package pl.com.tokarzewski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.com.tokarzewski.api.UserService;
import pl.com.tokarzewski.authentication.EmailExistException;
import pl.com.tokarzewski.converters.user.UserDtoToUser;
import pl.com.tokarzewski.converters.user.UserToUserDto;
import pl.com.tokarzewski.domain.User;
import pl.com.tokarzewski.dto.UserDto;

import javax.validation.Valid;

@Controller
public class AdminController {

    private UserService userService;
    private UserDtoToUser userDtoToUser;
    private UserToUserDto userToUserDto;

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @RequestMapping(value = "/admin/user/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam(value = "id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("newUser", userToUserDto.convert(user));
        model.addAttribute("type", "update");
        return "admin/user-form";
    }

    @RequestMapping(value = "/admin/user/update")
    public String updateUser(
            @ModelAttribute("newUser") UserDto user) {
        userService.update(userDtoToUser.convert(user));
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String saveUser(
            @ModelAttribute("newUser") @Valid UserDto newUser,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("type","create");
            return "admin/user-form";
        }
        try {
            userService.createUserAccount(userDtoToUser.convert(newUser));
            return "redirect:/admin/users";
        } catch (EmailExistException e) {
            return "redirect:/admin/users?error";
        }

    }

    @RequestMapping(value = "/admin/user/add", method = RequestMethod.GET)
    public String addNewUser(Model model) {
        model.addAttribute("newUser", new UserDto());
        model.addAttribute("type", "create");
        return "admin/user-form";
    }


    @RequestMapping(value = "/admin/user/delete", method = RequestMethod.POST)
    public String confirmUserDelete(@ModelAttribute User user, Model model) {
        model.addAttribute("userToDelete", userService.getById(user.getId()));
        return "admin/delete-user";
    }

    @RequestMapping(value = "/admin/user/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@ModelAttribute(value = "userToDelete") User user) {
        userService.delete(user.getId());
        return "redirect:/admin/users";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminHome(Model model) {
        return "redirect:/admin/users";
    }

    @Autowired
    public void setUserService(UserService service) {
        this.userService = service;
    }
    @Autowired
    public void setUserDtoToUser(UserDtoToUser userDtoToUser) {
        this.userDtoToUser = userDtoToUser;
    }
    @Autowired
    public void setUserToUserDto(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }
}
