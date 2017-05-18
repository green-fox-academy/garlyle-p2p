package com.greenfoxacademy.garlyle.repository;

import com.greenfoxacademy.garlyle.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
  List<Message> findAllByOrderByPostedAsc();
}
