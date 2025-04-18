package com.example.pollclient;
import java.util.List;
public record PollResult(
        List<String> options,
        List<ImmutableDouble> percentages) {


        @Override
        public String toString() {
                StringBuilder result = new StringBuilder("Poll Results:\n");
                for (int i = 0; i < options.size(); i++) {
                result.append(options.get(i))
                        .append(": ")
                        .append(percentages.get(i).val())
                        .append("%");
                if (i < options.size() - 1) {
                        result.append("\n");
                }
                }
                return result.toString();
        }
}
