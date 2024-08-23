package org.flipkart.service;

import org.flipkart.entity.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberService {
    List<Member> members;

    public MemberService() {
        this.members = new ArrayList<>();
    }

    public void addMember(int memberId, String name, int coins ){
        Member member = new Member(memberId, name, coins);
        members.add(member);
        System.out.println(member.getName()+" added Successfully");
    }
    public Member getMember(int memberId){
        Optional<Member>m = members.stream().filter(member -> member.getId()==memberId).findAny();
        if(m.isEmpty()){
            return null;
        }
        return m.get();
    }
}
