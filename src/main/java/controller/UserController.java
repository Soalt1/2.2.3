package controller;

import model.User;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "allUsers";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("lastname") String lastName,
                          @RequestParam("age") Integer age) {
        userService.createUser(name, lastName, age);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("lastname") String lastName,
                             @RequestParam("age") Integer age) {
        userService.updateUser(id, name, lastName, age);
        return "redirect:/";
    }
}