package com.austiine.lil.learning.school;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    private static final Map<String, String> bank=new HashMap<>();

    public static AppDTO getBalance(String accountNumber){
        AppDTO dto=new AppDTO();
        String details=bank.get(accountNumber);
        if (details!=null) {
            String[] mapNameAndAcc = details.split(",");
            dto.setAccountName(mapNameAndAcc[0]);
            dto.setAmount(Integer.parseInt(mapNameAndAcc[1]));
            dto.setAccountNumber(accountNumber);

            try {
                FileWriter myWriter = new FileWriter("C:/Users/awamalw/Downloads/filename.txt");
                myWriter.write("Name\t"+mapNameAndAcc[0]+"\nAmount\t"+Integer.parseInt(mapNameAndAcc[1])+"\nAccount Number\t"+accountNumber);
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }
        else {
            dto.setMessage("Account Number not found in the database. Check and try again");
        }
        return dto;
    }
    public static AppDTO performDeposit(String accountNumber, int amount){
        AppDTO dto=new AppDTO();
        String details=bank.get(accountNumber);
        if (details !=null) {
            String[] mapNameAndAcc = details.toString().split(",");
            dto.setAccountName(mapNameAndAcc[0]);
            int totalAmount = Integer.parseInt(mapNameAndAcc[1]) + amount;
            dto.setAmount(amount);
            String toMap = mapNameAndAcc[0] + "," + totalAmount;
            bank.remove(accountNumber);
            bank.put(accountNumber, toMap);
            dto.setAccountNumber(accountNumber);
            dto.setSuccess(true);
            return dto;
        }
        else {
            dto.setSuccess(false);
            dto.setMessage("Account Number not found in the system");
            return dto;
        }
    }

    public static AppDTO performWithdraw(String accountNumber, int amount){
        AppDTO dto=new AppDTO();
        String details=bank.get(accountNumber);
        if (details !=null) {
            String[] mapNameAndAcc = details.split(",");
            String name = mapNameAndAcc[0];
            dto.setAccountName(name);
            int availableAmount = Integer.parseInt(mapNameAndAcc[1]);
            if (availableAmount >= 1000 && availableAmount > amount) {
                int newAmount = availableAmount - amount;
                bank.remove(accountNumber);
                String toMap = name + "," + newAmount;
                bank.put(accountNumber, toMap);
                dto.setAmount(amount);
                dto.setAccountNumber(accountNumber);
                dto.setSuccess(true);
            } else {
                dto.setAmount(availableAmount);
                dto.setMessage("Insufficient amount in your account to withdraw " + amount);
                dto.setSuccess(false);
            }
            return dto;
        }
        else return new AppDTO();
    }
    public static void insertData(){
        bank.put("12345", "Benjamin,2000");
        bank.put("53253", "Boaz,1200");
        bank.put("52555", "Caro,4135");
        bank.put("62642", "Chelsea,120");
        bank.put("24354", "Josh,6362");
        bank.put("46626", "James,2135");
        bank.put("67458", "Joe,2553");
        bank.put("85254", "Erick,6842");
        bank.put("82966", "Peter,2471");
        bank.put("25255", "Nandy,2582");
        bank.put("56363", "Brance,8325");
        bank.put("67612", "Jack,3861");
        bank.put("77511", "Nancy,4715");
        bank.put("52852", "Cate,8626");
        bank.put("95264", "Patrose,8264");
        bank.put("66464", "Jean,6942");
        bank.put("42965", "Doroh,2000");
        bank.put("79622", "Benjamin,5251");
    }

    public static void convertCurrency(AppDTO dto, int val) {
        if (val == 1) {
            int USD = dto.getAmount() / 114;
            System.out.println("KES to USD = " + USD);
        } else if (val == 2) {
            int USD = dto.getAmount() / 3544;
            System.out.println("Ush to USD = " + USD);

        } else if (val == 3) {
            int USD = dto.getAmount() / 2321;
            System.out.println("Tsh to USD = " + USD);

        } else {
            System.out.println("Invalid selection. Try again");
        }
    }
}
