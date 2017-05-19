package BankManagement;

import java.util.Scanner;

public class BankManagement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank dsk = new Bank();
        String command;
        do {
            command = sc.nextLine();
            String[] split = command.split(" ");
           try{
            switch (split[0].trim()) {
                case "OPEN":
                   
                        dsk.openAccount(split[1].trim(), split[2].trim(), split[3].trim(), split[4].trim());
                    
                    break;
                case "CLOSE":
                    
                        dsk.closeAccount(split[1].trim(), split[2].trim());
                    
                    
                    break;

                case "DEPOSIT":
                   
                        dsk.deposit(split[1].trim(), Double.parseDouble(split[2].trim()));
                    
                    break;
                    
                case "WITHDRAW":
                    dsk.withdraw(split[1].trim(), split[2].trim(), Double.parseDouble(split[3].trim()));
                    break;
                    
                case "TRANSFER":
                    dsk.transfer(split[1].trim(), split[2].trim(), Double.parseDouble(split[3].trim()), split[4].trim());
                    break;
                    
                case "END":
                    for (Account acc : dsk.accs) {
                        System.out.println(acc.toString());
                        System.out.println();
                    }
                    System.out.println(Bank.getAssets());
                    return;
            }
           }catch(IllegalAccessError ex){
                        System.out.println(ex.getMessage());
                    }
           
           catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println(ex.getMessage());
                    }
        } while (command.equals("END") == false);

    }
}
