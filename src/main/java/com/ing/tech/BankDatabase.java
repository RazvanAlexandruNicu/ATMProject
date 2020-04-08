package com.ing.tech;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BankDatabase {
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account ac) {
        if(accounts.contains(ac)) {
            System.out.println("Account already existent");
        } else {
            this.accounts.add(ac);
            //System.out.println("Account "+ac.getAccountNumber()+"("+ac.getPIN()+") added!");
        }
    }

    public Account getAccount(String accName, String PIN) {
        for(Account x : accounts) {
            if(x.getAccountNumber().compareTo(accName) == 0) {
                if(x.getPIN().compareTo(PIN) == 0) {
                    System.out.println("Successful login");
                    return x;
                } else {
                    System.out.println("Incorrect PIN");
                    return null;
                }
            }
        }
        System.out.println("Account number not found!");
        return null;
    }
}
