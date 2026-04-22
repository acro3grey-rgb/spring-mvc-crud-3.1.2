package com.serg.controller;

import com.serg.model.User;
import com.serg.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/user";
    }

    @GetMapping("/admin")
    public String showAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", userService.getAllRoles());
        return "users";
    }

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("newUser") User user,
                          @RequestParam(value = "roleIds", required = false) List<Long> roleIds) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userService.getRolesByIds(roleIds));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String editUser(@PathVariable("id") Long id, ModelMap model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/admin";
        }
        model.addAttribute("user", user);
        model.addAttribute("roles", userService.getAllRoles());
        return "edit-user";
    }

    @PostMapping("/admin/{id}")
    public String updateUser(@PathVariable("id") Long id,
                             @ModelAttribute("user") User user,
                             @RequestParam(value = "roleIds", required = false) List<Long> roleIds) {
        User existingUser = userService.getUserById(id);
        if (existingUser == null) {
            return "redirect:/admin";
        }
        user.setId(id);
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            user.setPassword(existingUser.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(userService.getRolesByIds(roleIds));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", user);
        return "user";
    }
}
