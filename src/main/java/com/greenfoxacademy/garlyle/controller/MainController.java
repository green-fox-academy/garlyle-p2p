package com.greenfoxacademy.garlyle.controller;

import com.greenfoxacademy.garlyle.model.Logger;
import com.greenfoxacademy.garlyle.model.User;
import com.greenfoxacademy.garlyle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {
  @Autowired
  UserRepository userRepository;

  @ExceptionHandler
  public void error(Exception ex) {
    Logger.error(ex.getMessage());
  }

  @ModelAttribute
  public void log(HttpServletRequest request) {
    Logger.info(request);
  }

  @RequestMapping("/")
  public String index(@RequestParam(required = false) String username) {
    if (username != null) {
      if (userRepository.findByUsername(username).isEmpty()) {
        return "redirect:/register";
      }
    }
    return "index";
  }

  @RequestMapping("/register")
  public String register() {
    return "register";
  }

  @PostMapping("/newuser")
  public String addNewUser(String username, Model model) {
    if (username.isEmpty()) {
      model.addAttribute("error", "The username field is empty");
      return "register";
    } else {
      List<User> existingUsers = userRepository.findByUsername(username);
      if (userRepository.findByUsername(username).isEmpty()) {
        User user = new User(username);
        userRepository.save(user);
      }
      return "redirect:/";
    }
  }
}
