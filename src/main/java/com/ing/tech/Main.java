package com.ing.tech;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ATM atm = new ATM();
        atm.initCD();
        atm.initDB();
        atm.start();
    }

}
