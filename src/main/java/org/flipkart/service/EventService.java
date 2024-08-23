package org.flipkart.service;

import org.flipkart.entity.Event;
import org.flipkart.entity.Member;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class EventService {
    Map<Date, Event> events;
    WinningStrategy winningStrategy;
    private static final int BID_LIMIT =5;

    public EventService(WinningStrategy winningStrategy) {
        this.events = new HashMap<>();
        this.winningStrategy = winningStrategy;
    }

    public void addEvent(int eventId, String eventName, String prize, String dateString) throws ParseException {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = inFormat.parse(dateString);
        if(events.get(date)!=null){
            throw new RuntimeException("Only one event can be added in a single day");
        }
        Collection<Event> eventsList = events.values();
        for(Event e: eventsList){
            if(e.getName().equals(eventName))
                throw new RuntimeException("Event name is not unique");
        }
        Event event = new Event(eventId,eventName, prize);
        events.put(date, event);
        System.out.println(eventName+" with prize "+ prize+" added successfully");
    }
    public void submitBids(Member member, int eventId, List<Integer>bids){
        Event event = getEvent(eventId);
        if(bids.size()>BID_LIMIT){
            throw new RuntimeException("Max 5 bids can be submitted");
        }
        Integer maxBid = bids.stream().max(Integer::compare).get();
        if(member.getCoins()<maxBid){
            throw new RuntimeException("Member doesn't have enough coins to bid");
        }
        if(bids.stream().distinct().count()!=bids.size()){
            throw new RuntimeException("Bids should be distinct");
        }
        int zeroBids = bids.stream().filter(num -> num <= 0).collect(Collectors.toList()).size();
        if(zeroBids > 0){
            throw new RuntimeException("Bids should have value greater than zero");
        }
        if(!event.getMemberList().contains(member)){
            System.out.println("Member did not registered for this event");
        } else {
            event.addBid(member, bids);
            System.out.println("BIDS submitted successfully ");
        }
    }

    public void registerMember(int eventId, Member member){
        Event event = getEvent(eventId);
        event.getMemberList().add(member);
        System.out.println(member.getName()+ " registered to "+event.getName()+" event successfully");
    }
    public Event  getEvent(int eventId){
        return events.values().stream().filter(e->e.getId()==eventId).findAny().get();
    }

    public void declareWinner(int eventId){
        Event event= getEvent(eventId);
        winningStrategy.declareWinner(event);
    }
}
