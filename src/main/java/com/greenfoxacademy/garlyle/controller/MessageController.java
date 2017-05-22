package com.greenfoxacademy.garlyle.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.greenfoxacademy.garlyle.model.*;
import com.greenfoxacademy.garlyle.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin("*")
public class MessageController {
  @Autowired
  MessageRepository repository;

  @PostMapping("/api/message/receive")
  public ResponseEntity<?> receiveMessage(@RequestBody MessageDispatch received) throws JsonParseException {
    ArrayList<String> missing = new ArrayList<>();
    if (received.getMessage() == null) {
      missing.add("message");
    } else {
      if (received.getMessage().getId() == 0) {
        missing.add("message.id");
      }
      if (received.getMessage().getUsername() == null) {
        missing.add("message.username");
      }
      if (received.getMessage().getText() == null) {
        missing.add("message.text");
      }
      if (received.getMessage().getTimestamp() == null) {
        missing.add("message.timestamp");
      }
    }
    if (received.getClient() == null) {
      missing.add("client");
    } else {
      if (received.getClient().getId() == null) {
        missing.add("client.id");
      }
    }
    if (missing.isEmpty()) {
      repository.save(received.getMessage());
      return new ResponseEntity<>(new Status("ok"), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(new Status(missing), HttpStatus.BAD_REQUEST);
    }


  }
}
