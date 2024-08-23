package org.flipkart.entity;

public class Member extends User{
    private int id;
    private int coins;

    public int getId() {
        return id;
    }

    public Member(int id, String name, int coins) {
        super(name);
        this.id = id;
        assignCoins(coins);
    }

    public int getCoins() {
        return coins;
    }

    public void assignCoins(int coins) {
        if(coins>0)
            this.coins = coins;
        else
            throw new RuntimeException("Supercoins should be greater than 0");
    }

    public void deductCoins(int deductedCoins){
        coins-= deductedCoins;
    }
}
