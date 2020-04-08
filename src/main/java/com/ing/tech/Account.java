package com.ing.tech;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private String accountNumber;
    private String PIN;
    private float balance;

    public void deposit(float sum) {
        this.balance += sum;
    }

    public boolean withdrawal(float sum) {
        if(this.balance >= sum) {
            this.balance -= sum;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Account) {
            Account ac = (Account)obj;
            return this.accountNumber.equals(ac.getAccountNumber());
        }
        return false;
    }
}
