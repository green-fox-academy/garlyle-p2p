package com.greenfoxacademy.garlyle.controller;

import com.greenfoxacademy.garlyle.model.Logger;
import com.greenfoxacademy.garlyle.model.User;
import com.greenfoxacademy.garlyle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
  public String index(@RequestParam(required = false) String username, Model model) {
    if (username != null) {
      if (userRepository.findByUsername(username).isEmpty()) {
        return "redirect:/register";
      } else {
        model.addAttribute("user", userRepository.findByUsername(username).get(0));
      }
    }
    return "index";
  }

  @RequestMapping("/register")
  public String register() {
    return "register";
  }

  @PostMapping("/newuser")
  public String addNewUser(String username, RedirectAttributes attr, Model model) {
    if (username.isEmpty()) {
      model.addAttribute("error", "The username field is empty");
      return "register";
    } else {
      if (userRepository.findByUsername(username).isEmpty()) {
        User user = new User(username);
        userRepository.save(user);
        attr.addAttribute("username", user.getUsername());
      } else {
        attr.addAttribute("username", username);
      }
      return "redirect:/";
    }
  }

  @PostMapping("/changeuser")
  public String changeUser(@ModelAttribute User user, RedirectAttributes attr, Model model) {
    if (user.getUsername().isEmpty()) {
      model.addAttribute("error", "The username field is empty");
      return "index";
    } else {
      userRepository.save(user);
      attr.addAttribute("username", user.getUsername());
      return "redirect:/";
    }
  }
}
