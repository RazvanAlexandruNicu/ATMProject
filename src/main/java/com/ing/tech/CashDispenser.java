package com.ing.tech;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CashDispenser {
    private int no_bancnotes;

    public void addBancnotes(int v) {
        this.no_bancnotes += v;
    }
    public boolean withdrawal(int sum) {
        int needed = sum / 20;
        if(needed <= no_bancnotes) {
            no_bancnotes -= needed;
            return true;
        }
        return false;
    }
}
