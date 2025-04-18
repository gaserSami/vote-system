package com.example.vote.controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.vote.service.PollService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class PollRestController {
    private final PollService pollService;
    public PollRestController(PollService pollService) {
        this.pollService = pollService;
    }
    @PostMapping("/create")
    public void createPoll(@RequestBody ArrayList<String> requestData) {
        if (requestData.size()<2) return;
        String pollId = requestData.get(0);
        String[] options = (String[]) requestData.subList(1, requestData.size()).toArray();
        pollService.createPoll(pollId,options);
    }
    @GetMapping("/{pollId}")
    public String[] getPoll(@PathVariable String pollId) {
        return pollService.getPollOptions(pollId);
    }
    @GetMapping("/voteCount/{pollId}")
    public int getMostVoted(@PathVariable String pollId) {
        return pollService.getVoteCount(pollId);
    }
}
    
    
    
