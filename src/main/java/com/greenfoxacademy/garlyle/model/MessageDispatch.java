package com.greenfoxacademy.garlyle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageDispatch {
  Message message;
  Client client;
}
