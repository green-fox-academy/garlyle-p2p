package com.greenfoxacademy.garlyle.controller;

import com.greenfoxacademy.garlyle.model.*;
import com.greenfoxacademy.garlyle.repository.MessageRepository;
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

  @Autowired
  MessageRepository messageRepository;

  @ExceptionHandler
  public void error(Exception ex) {
    Logger.error(ex.getMessage());
  }

  @ModelAttribute
  public void log(HttpServletRequest request) {
    Logger.info(request);
  }

  @GetMapping("/")
  public String index(Model model) {
    List<User> userList = userRepository.findAll();
    if (userList.isEmpty()) {
      return "redirect:/enter";
    }
    model.addAttribute("user", userList.get(0));

    List<Message> messageList = messageRepository.findAllByOrderByTimestampAsc();
    if (messageList.isEmpty()) {
      Message msg = new Message("App", "Hi there! Submit your message using the send button!");
      messageRepository.save(msg);
      messageList.add(msg);
    }
    model.addAttribute("messages", messageList);

    return "index";
  }

  @PostMapping("/")
  public String changeUser(@ModelAttribute User user, Model model) {
    if (user.getUsername().isEmpty()) {
      model.addAttribute("error", "The username field is empty");
    } else {
      userRepository.save(user);
    }
    return "index";
  }

  @PostMapping("/postMessage")
  public String sendMessage(String username, String message) {
    if (!message.isEmpty()) {
      Message msg = new Message(username, message);
      messageRepository.save(msg);
      MessageDispatch.post(new MessageDispatch(msg, new Client(System.getenv("CHAT_APP_UNIQUE_ID"))));
    }
    return "redirect:/";
  }

  @GetMapping("/enter")
  public String register() {
    return "enter";
  }

  @PostMapping("/enter")
  public String addNewUser(String username, Model model) {
    if (username.isEmpty()) {
      model.addAttribute("error", "The username field is empty");
      return "enter";
    } else {
      userRepository.save(new User(username));
      return "redirect:/";
    }
  }
}
