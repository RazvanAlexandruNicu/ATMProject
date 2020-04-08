package com.ing.tech;

import sun.reflect.generics.tree.Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ATM {
    private BankDatabase db;
    private Account currentAccount = null;
    private CashDispenser dispenser;
    private Scanner sc = new Scanner(System.in);

    public void initDB() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./curs-2/src/main/resources/inputDispenser.txt"));
        int no_bancnotes = scanner.nextInt();
        this.dispenser = new CashDispenser(no_bancnotes);
    }

    public void initCD() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./curs-2/src/main/resources/inputDatabaseEntries.txt"));
        BankDatabase database = new BankDatabase();
        while(scanner.hasNextLine()) {
            String[] entry = scanner.nextLine().split(" ");
            database.addAccount(new Account(entry[0],entry[1], Integer.parseInt(entry[2])));
        }
        this.db = database;
    }


    public Account auth(String accNo, String PIN) {
        Account x = db.getAccount(accNo, PIN);
        if(x != null) {
            return x;
        }
        return null;
    }

    public void start() {
        while(true) {
            if(currentAccount == null) {
                System.out.println("Enter your credentials (ACC PIN)");
                String accountNumber = sc.next();
                String myPIN = sc.next();
                this.currentAccount = this.auth(accountNumber, myPIN);
            } else {
                showMenu();
                chooseOption();
            }
        }
    }

    public void showMenu() {
        System.out.println("1 - View Ballance");
        System.out.println("2 - Deposit");
        System.out.println("3 - Withdrawal");
        System.out.println("4 - Exit");
    }

    public void chooseOption() {
        System.out.println("Insert your option");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Your ballance is "+ currentAccount.getBalance());
                break;
            case 2:
                System.out.println("Enter the sum you want to deposit!");
                float val = sc.nextFloat();
                currentAccount.deposit(val);
                System.out.println("Your ballance has been updated");
                break;
            case 3:
                System.out.println("Enter the sum you want to withdrawal!");
                System.out.println("Choices = 20$, 40$, 60$, 100$, 200$");
                int withdr = sc.nextInt();
                if(withdr != 20 && withdr != 40 && withdr != 60 && withdr != 100 && withdr != 200) {
                    System.out.println("Invalid amount. Try again");
                    break;
                }
                if(dispenser.withdrawal(withdr)) {
                    if(currentAccount.withdrawal(withdr)) {
                        System.out.println("Your balance has been updated");
                    } else {
                        System.out.println("You don't have enough money");
                    }
                } else
                    System.out.println("Not enough money in the dispenser");
                break;
            case 4:
                System.out.println("Loging out of the system...");
                currentAccount = null;
                break;
            default:
                System.out.println("Invalid option. Try again!");
        }
    }
}
