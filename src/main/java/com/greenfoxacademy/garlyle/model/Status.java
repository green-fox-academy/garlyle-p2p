package com.greenfoxacademy.garlyle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {
  String status;
  String message;

  public Status(String status) {
    this.status = status;
  }

  public Status(ArrayList<String> missing) {
    status = "custom error";
    message = "Missing field(s): ";
    for (int i = 0; i < missing.size(); i++) {
      message += missing.get(i);
      if (i < missing.size() - 1) {
        message += ", ";
      }
    }
  }
}
