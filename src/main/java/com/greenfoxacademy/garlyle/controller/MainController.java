package com.greenfoxacademy.garlyle.controller;

import com.greenfoxacademy.garlyle.model.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
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
}
