package com.greenfoxacademy.garlyle.controller;

import com.greenfoxacademy.garlyle.model.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
  @ExceptionHandler
  public void error(Exception ex) {
    Logger.error(ex.getMessage());
  }

  @ModelAttribute
  public void log(HttpServletRequest request) {
    Logger.info(request);
  }

  @RequestMapping("/")
  public String index() {

    return "index";
  }

  @RequestMapping("/daily/{id}")
  public String logTest(@PathVariable int id, @RequestParam String test) {
    return "index";
  }

  @RequestMapping("/register")
  public String register() {
    return "register";
  }
}
