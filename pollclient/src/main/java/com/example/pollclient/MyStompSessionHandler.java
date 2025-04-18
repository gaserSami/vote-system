package com.example.pollclient;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {
  private PollWebsocket pollWebsocket;

  public MyStompSessionHandler(PollWebsocket pollWebsocket) {
    this.pollWebsocket = pollWebsocket;
  }

   @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
      System.out.println("Client Connected");
      }
    
}
