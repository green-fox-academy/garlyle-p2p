package com.greenfoxacademy.garlyle.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Message {
  final static long ID_LOWEST = 1000000;
  final static long ID_HIGHEST = 9999999;

  @Id
  long id;
  String username;
  String message;
  Date posted;

  public Message(String username, String message) {
    id = (long)(Math.random() * (ID_HIGHEST - ID_LOWEST)) + ID_LOWEST;
    this.username = username;
    this.message = message;
    posted = new Date();
  }

}
