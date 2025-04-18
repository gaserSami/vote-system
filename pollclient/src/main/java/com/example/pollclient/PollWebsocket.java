package com.example.pollclient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.messaging.converter.MessageConverter;
public class PollWebsocket {
    StompSession stompSession;
    public void connectToWebSocket() {
        try {
        /*  TODO: Complete the code to create a WebSocket client
        */
            List<Transport> transports = Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient()));
            SockJsClient sockJsClient = null;
            List<MessageConverter> converters = new ArrayList<>();   
		
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendVote(String option,String pollId) {
        //TODO: write the code for sending the vote
    }
    public void subscribeToPoll(String pollId){
        //TODO: Subscribe to the poll topic. Print PollResult updates in the format shown in document

    }
    public void close(){
        this.stompSession.disconnect();
    }
    }
    

