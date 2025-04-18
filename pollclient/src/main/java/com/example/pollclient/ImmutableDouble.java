package com.example.pollclient;
public record ImmutableDouble(Double val) {
    public ImmutableDouble {
        if (val == null || val< 0.0) {
            val = 0.0;
        }
    }
}
