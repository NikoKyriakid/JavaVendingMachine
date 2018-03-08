package com.nikokyriakid.VendingMachine;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Scanner;

public class startVM {
    public static void main(String[] args) {
        int slot, quantity, input;
        double price;
        String coins, ary[];
        VendingMachineImpl vm = new VendingMachineImpl(10, Arrays.asList(0.10, 0.20, 0.50, 1.0, 2.0));

        System.out.println("=============== Vending Machine by NK =================");
        Scanner reader = new Scanner(System.in);
        while(true) {
            System.out.println("=======================================================");
            System.out.println("1. Set Quantity   | 2. Set Price      | 3. Buy Product\n" +
                                "4. Add Coins      | 5. Print Slots    | 6. Print Change\n" +
                                "7. Populate Slots | 8. Populate Coins | 9. Reset");
            System.out.print("Select an action: ");

            input = reader.nextInt();
            if (input == 0) {
                System.out.print("App terminated.");
                break;
            }
            else if (input == 1) {
                System.out.print("Type slot: ");
                slot = reader.nextInt();
                System.out.print("Type quantity: ");
                quantity = reader.nextInt();
                vm.setQuantity(slot, quantity);
            } else if (input == 2) {
                System.out.print("Type slot: ");
                slot = reader.nextInt();
                System.out.print("Type Price: ");
                price = reader.nextDouble();
                vm.setPrice(slot, price);
            } else if (input == 3) {
                System.out.print("Type slot: ");
                slot = reader.nextInt();
                System.out.print("Put Coins: ");
                coins = reader.next();
                ary = coins.split(",");

                try {
                    vm.buyProduct(slot, Arrays.asList(ary).stream().map(s -> Double.parseDouble(s)).collect(toList()));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (input == 4) {
                System.out.print("Type Coins: ");
                coins = reader.next();
                ary = coins.split(",");
                try {
                    vm.addCoinsByList(Arrays.asList(ary).stream().map(s -> Double.parseDouble(s)).collect(toList()), true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (input == 5) {
                vm.displaySlots();
            } else if (input == 6) {
                vm.displayCoins();
            } else if (input == 7) {
                startVM.populateDefaults(vm);
            } else if (input == 8) {
                startVM.populateCoins(vm);
            } else if (input == 9) {
                vm.reset();
                System.out.println("Vending Machine is reset.");
            } else {
                System.out.println("No valid action.");
            }

        }
        reader.close();
    }

    public static void populateDefaults(VendingMachineImpl vm) {
        for (ProductSlot pSlot: vm.getProductSlots()){
            try {
                int q = (int) (Math.random() * 10) + 1; //At least one quantity
                pSlot.setQuantity(q);
                double p = (Math.random() * (5.0 - 0.20)) + 0.20;
                p = Math.floor(p*10)/10;
                pSlot.setPrice(p);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void populateCoins(VendingMachineImpl vm) {
        String coins = "2,2,2,2,2,2,1,1,1,1,1,1,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.2,0.2,0.2,0.2,0.2,0.2,0.2,0.2,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1";
        String[] arrayOfCoins = coins.split(",");

        try {
            vm.addCoinsByList(Arrays.asList(arrayOfCoins).stream().map(s -> Double.parseDouble(s)).collect(toList()), false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
