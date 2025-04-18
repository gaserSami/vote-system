package com.example.vote.service;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import com.example.vote.model.Poll;
import com.example.vote.model.PollResult;
@Service
public class PollService {
    ConcurrentHashMap<String,Poll> systemPolls=new ConcurrentHashMap<String,Poll>();
    public void createPoll(String pollId,String[] options){
        //Create a new poll with the given pollId and options
        //if the pollId already exists do nothing
        //if the options are empty or null do nothing
        if(pollId==null || pollId.isEmpty() || options==null || options.length==0){
            return;
        }
        systemPolls.putIfAbsent(pollId,new Poll(options));
    }
    public String[] getPollOptions(String pollId){
        //Return the list of options in the poll with the given pollId
        //if the pollId is not found return an empty array
        if(systemPolls.containsKey(pollId)){
            return new String[0];
        }
        return systemPolls.get(pollId).getOptions();
    }
    public int addVote(String pollId,String option){
        //Add a vote to the given option in the poll with the given pollId
        //if the pollId is not found return -1
        //if the option is not found return -1
        if(!systemPolls.containsKey(pollId)){
            return -1;
        }
        return systemPolls.get(pollId).addVote(option);
    }
    public int getVoteCount(String pollId){
        //Return the total number of votes added to the poll with the given pollId
        //if the pollId is not found return -1
        if(!systemPolls.containsKey(pollId)){
            return -1;
        }
        return systemPolls.get(pollId).getTotalVotes();
    }
    
    public PollResult getPollResult(String pollId){
        /*TODO: write getPollResult method in functional programming style
        * Calculates the vote percentage given to each option and return them as a PollResult object
        * Handle the case when the pollId is not found
        */
        return null;     
    }
    


}
