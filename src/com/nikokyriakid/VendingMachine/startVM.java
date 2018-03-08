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
            System.out.println("1. Set Quantity | 2. Set Price | 3. Buy Product | 4. Add Coins\n5. Print Slots  | 6. Print Change | 7. Populate Defaults");
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
                vm.addCoins(Arrays.asList(ary).stream().map(s -> Double.parseDouble(s)).collect(toList()));
            } else if (input == 5) {
                vm.displaySlots();
            } else if (input == 6) {
                vm.displayCoins();
            } else if (input == 7) {
                startVM.populateDefaults(vm);
            } else {
                System.out.println("No valid action.");
            }

        }
        reader.close();
    }

    public static void populateDefaults(VendingMachineImpl vm) {
        vm.setQuantity(0, 2);
        vm.setQuantity(1, 3);
        vm.setQuantity(2, 4);
        vm.setQuantity(3, 5);
        vm.setQuantity(4, 6);
        vm.setQuantity(5, 2);
        vm.setQuantity(6, 3);
        vm.setQuantity(7, 2);
        vm.setQuantity(8, 2);
        vm.setQuantity(9, 0);

        vm.setPrice(1, 1.20);
        vm.setPrice(2, 2.20);
        vm.setPrice(3, 0);
        vm.setPrice(4, 1.30);
        vm.setPrice(5, 1.40);
        vm.setPrice(6, 1.50);
        vm.setPrice(7, 1.60);
        vm.setPrice(8, 2.20);
        vm.setPrice(9, 0.40);
    }
}
