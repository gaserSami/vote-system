package com.example.pollclient;
import java.util.List;
public record PollResult(
        List<String> options,
        List<ImmutableDouble> percentages) {
}
