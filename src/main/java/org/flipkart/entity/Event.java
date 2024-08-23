package org.example.entity;

import java.util.*;

public class Event {
    private int id;
    private String name;
    private List<Member> memberList;
    private Map<Member, List<Bid>> bids;
    private String prize;

    public Event(int id, String name, String prize) {
        this.id = id;
        this.name = name;
        this.prize = prize;
        memberList = new ArrayList<>();
        bids = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public Map<Member, List<Bid>> getBids() {
        return bids;
    }

    public String getPrize() {
        return prize;
    }

    public void addBid(Member member, List<Integer>bidsList){
        List<Bid> biddings = new ArrayList<>();
        for(Integer bid: bidsList) {
            biddings.add(new Bid(bid, new Date()));
        }
        bids.put(member,biddings);
    }

}
