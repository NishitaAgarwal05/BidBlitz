package org.example.service;

import org.example.entity.Event;

public interface WinningStrategy {
     void declareWinner(Event event);
}
