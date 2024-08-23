package org.flipkart.service;

import org.flipkart.entity.Event;

public interface WinningStrategy {
     void declareWinner(Event event);
}
