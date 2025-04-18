package com.example.vote.controller;
import com.example.vote.model.PollResult;
import com.example.vote.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


/*TODO: Implement the PollWebsocketController class that handles the websocket connections
* and messages as follows:
    * Handles adding votes sent to a specific poll
    * Sends the updated PollResult object to subscribers after each vote
Your endpoints should be named appropriately
    */

@Controller
public class PollWebsocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final PollService pollService;

    @Autowired
    public PollWebsocketController(SimpMessagingTemplate messagingTemplate, PollService pollService) {
        this.messagingTemplate = messagingTemplate;
        this.pollService = pollService;
    }

    @MessageMapping("/vote/{pollId}")
    public void handleVote(@DestinationVariable String pollId, String option) {
        System.out.println("Received vote for poll: " + pollId + ", option: " + option);
        // Handle the incoming vote
        pollService.addVote(pollId, option);
        
        // Fetch the updated poll result
        PollResult updatedPollResult = pollService.getPollResult(pollId);
        
        // Send the updated result to all subscribers
        messagingTemplate.convertAndSend("/topic/poll/" + pollId, updatedPollResult);
    }
}
