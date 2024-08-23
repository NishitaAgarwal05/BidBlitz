package org.example;

import org.example.entity.Member;
import org.example.service.EventService;
import org.example.service.LowestBidWinningStrategy;
import org.example.service.MemberService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ParseException {
        EventService eventService = new EventService(new LowestBidWinningStrategy());
        MemberService memberService = new MemberService();
        Member member = null;
        Scanner myObj = new Scanner(System.in);
        System.out.println("Waiting for user input!!");
        String command = "";
        while(!command.equals("STOP")) {
            command = myObj.nextLine();
            List<String> commandSplit = List.of(command.split(" "));
            String operation = commandSplit.get(0);
            switch (operation) {
                case "ADD_MEMBER":
                    memberService.addMember(Integer.parseInt(commandSplit.get(1)), commandSplit.get(2), Integer.parseInt(commandSplit.get(3)));
                    break;
                case "ADD_EVENT":
                    eventService.addEvent(Integer.parseInt(commandSplit.get(1)), commandSplit.get(2), commandSplit.get(3), commandSplit.get(4));
                    break;
                case "REGISTER_MEMBER":
                    member = memberService.getMember(Integer.parseInt(commandSplit.get(1)));
                    if(member==null){
                        System.out.println("Member is not yet created");
                        return;
                    }
                    eventService.registerMember(Integer.parseInt(commandSplit.get(2)), member);
                    break;
                case "SUBMIT_BID":
                    List<String> bids = commandSplit.subList(3, commandSplit.size() - 1);
                    List<Integer> bidsList = new ArrayList<>();
                    bids.stream().forEach(bid -> {
                        Integer bidInteger = Integer.parseInt(bid);
                        bidsList.add(bidInteger);
                    });
                    member = memberService.getMember(Integer.parseInt(commandSplit.get(1)));
                    if(member==null){
                        System.out.println("Member is not yet created");
                    } else {
                        eventService.submitBids(member, Integer.parseInt(commandSplit.get(2)), bidsList);
                    }
                    break;
                case "DECLARE_WINNER":
                    eventService.declareWinner(Integer.parseInt(commandSplit.get(1)));
                    break;
                default:
                    System.out.println("System is stopped!");
                    break;
            }
        }
        myObj.close();

    }
}