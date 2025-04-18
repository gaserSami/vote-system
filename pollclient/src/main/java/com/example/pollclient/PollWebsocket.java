package com.example.pollclient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
public class PollWebsocket {
    StompSession stompSession;

    public void connectToWebSocket() {
        try {
        /*  TODO: Complete the code to create a WebSocket client
        */
            List<Transport> transports = Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient()));
            SockJsClient sockJsClient = new SockJsClient(transports);
            WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
            stompClient.setMessageConverter(new MappingJackson2MessageConverter());

            StompSessionHandler sessionHandler = new MyStompSessionHandler(this);
            String url = "ws://localhost:8080/ws";

            stompSession = stompClient.connectAsync(url, sessionHandler).get();
		
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendVote(String option,String pollId) {
        //TODO: write the code for sending the vote
        System.out.println("Sending vote: " + option + " for poll: " + pollId);
        stompSession.send("/app/vote/"+pollId, option);
    }

    public void subscribeToPoll(String pollId){
        // while(!isConnected) {
        // }
        //TODO: Subscribe to the poll topic. Print PollResult updates in the format shown in document
            stompSession.subscribe("/topic/poll/"+pollId, new StompFrameHandler(){
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return PollResult.class;
            }
            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                PollResult pollResult = (PollResult) payload;
                System.out.println("Poll Result: " + pollResult);
            }
        });
    }

    public void close(){
        this.stompSession.disconnect();
    }
    }
    

