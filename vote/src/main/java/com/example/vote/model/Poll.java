package com.example.vote.model;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Poll {
    private HashMap<String,AtomicInteger> pollResult= new HashMap<>();

    public Poll(String[] options){
        for(String option:options){
            pollResult.put(option,new AtomicInteger(0));
        }
    }
    public int addVote(String option){
        if(!pollResult.containsKey(option)){
            return -1;
        }
        pollResult.get(option).incrementAndGet();
        return 0;
    }
    public String[] getOptions(){
        return pollResult.keySet().toArray(new String[0]);
    }
    public int getTotalVotes(){
        /*TODO: write the getTotalVotes method that calculates the total number 
        of votes added to the poll in functional programming style
        */
        return 0;
    }
    public List<Integer> getPollResult(){
        /*TODO: write getPollResult method that gets a list
        with the votes given to each option in functional programming style*/
        return null;
    }
    

}
