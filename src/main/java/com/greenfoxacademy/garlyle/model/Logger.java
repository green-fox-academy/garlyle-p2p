package com.greenfoxacademy.garlyle.model;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
  private static String getDate() {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
  }

  private static String getDetails(HttpServletRequest request) {
    return request.getRequestURI() + " " + request.getMethod() + " " + request.getQueryString();
  }

  public static void info(HttpServletRequest request) {
    System.out.println(getDate() + " INFO Request " + getDetails(request));

  }
}
