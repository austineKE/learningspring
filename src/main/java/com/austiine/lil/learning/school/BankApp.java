package com.austiine.lil.learning.school;

import java.util.Scanner;

import java.util.Arrays;
import java.util.Scanner;

public class BankApp {
    private String accountName;
    private String accountNumber;
    private Integer balance;

    public AppDTO depositAmount(String accountNumber, int amount){
       AppDTO appDTO=Utils.performDeposit(accountNumber, amount);
        System.out.println("Name \t"+appDTO.getAccountName()+"\nAmount \t"+appDTO.getAmount()+"\n Account Number \t"+appDTO.getAccountNumber());
       return appDTO;
    }

    public AppDTO withDrawFromAccount(String accountNumber, int amount){

        AppDTO appDTO = Utils.performWithdraw(accountNumber, amount);
        System.out.println("Name \t"+appDTO.getAccountName()+"\nAmount \t"+appDTO.getAmount()+"\n Account Number \t"+appDTO.getAccountNumber());
        return appDTO;
    }

    //this method should write to an external file balance, name, accountNumber
    public AppDTO checkAccountBalance(String accountNumber){

        AppDTO balance = Utils.getBalance(accountNumber);
        System.out.println("Name \t"+balance.getAccountName() +
                "\nAccount Number \t"+balance.getAccountNumber()+"\n Balance \t"+
                balance.getAmount()+" \nMesssage \t"+balance.getMessage());

        return balance;
    }

    public static void main(String[] args) {
        BankApp bankApp=new BankApp();
        Scanner input=new Scanner(System.in);
        Utils.insertData();

        for (;;) {
            System.out.println("Welcome to our Banking App\n");
            System.out.println("1. Deposit amount\n2.WithDraw Cash\n3.Check Balance");
            int value = input.nextInt();
            if (value == 1) {
                System.out.println("Enter your Account Number");
                String accountNumber = input.next();
                System.out.println("Enter Amount to Deposit");
                int amount = input.nextInt();
                bankApp.depositAmount(accountNumber, amount);
            } else if (value == 2) {
                System.out.println("Enter your Account Number");
                String accountNumber = input.next();
                System.out.println("Enter Amount to Withdraw");
                int amount = input.nextInt();
                bankApp.withDrawFromAccount(accountNumber, amount);
            } else if (value == 3) {
                System.out.println("Enter your Account Number");
                String accountNumber = input.next();
                AppDTO dto = bankApp.checkAccountBalance(accountNumber);
                System.out.println("Do you want to convert currency? (Y/N)");
                String response = input.next();
                if (response.equalsIgnoreCase("Y")) {
                    String[] arr={"1. KES 114 = 1 US Dollar \n2. Ush 3544 = 1 US Dollar \n3. Tsh 2321 = 1 US Dollar"};
                    System.out.println(Arrays.asList(arr));
                    int val = input.nextInt();
                    Utils.convertCurrency(dto, val);
                } else if (response.equalsIgnoreCase("N")) {
                    continue;
                }
            }
             else {
                System.out.println("Sorry! You entered an invalid input");
            }
            System.out.println("Do you want yo continue with our services.(Y/N)");
            String string=input.next();
            if (string.equals("Y")){
            }
            else if (string.equalsIgnoreCase("N")){
                System.out.println("Thank you for using our services");
                break;
            }
        }

    }

}
