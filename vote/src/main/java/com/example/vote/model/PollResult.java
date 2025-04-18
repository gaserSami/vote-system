package com.example.vote.model;
import java.util.List;
public record PollResult(
        List<String> options,
        List<ImmutableDouble> percentages) {
}
