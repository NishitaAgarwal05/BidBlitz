package org.example.service;

import org.example.entity.Bid;
import org.example.entity.Event;
import org.example.entity.Member;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class LowestBidWinningStrategy implements WinningStrategy{

    @Override
    public void declareWinner(Event event) {
        int minBid=Integer.MAX_VALUE;
        Date minBidDate = null;
        Member winner =null;
        for(Map.Entry<Member, List<Bid>>entry: event.getBids().entrySet()){
            for(Bid bid: entry.getValue())
            {
                if(minBid>bid.getAmount() || (minBid==bid.getAmount() && minBidDate!=null && minBidDate.after(bid.getSubmittedDate()))){
                    minBid = bid.getAmount();
                    winner = entry.getKey();
                    minBidDate = bid.getSubmittedDate();
                }
            }
        }
        winner.deductCoins(minBid);
        System.out.println(winner.getName()+" wins the "+event.getPrize()+" with lowest bid "+minBid);
    }
}
