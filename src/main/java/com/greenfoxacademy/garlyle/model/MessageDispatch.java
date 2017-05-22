package com.greenfoxacademy.garlyle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Getter
public class MessageDispatch {
  public static Status post(MessageDispatch msg) {
    RestTemplate template = new RestTemplate();
    String url = System.getenv("CHAT_APP_PEER_ADDRESS") + "api/message/receive";
    Status status = template.postForObject(url, msg, Status.class);

    return status;
  }

  Message message;
  Client client;
}
