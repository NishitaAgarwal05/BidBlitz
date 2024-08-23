package org.example.entity;

import java.util.Date;

public class Bid {
    private int amount;
    private Date submittedDate;

    public Bid(int amount, Date submittedDate) {
        this.amount = amount;
        this.submittedDate = submittedDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }
}
