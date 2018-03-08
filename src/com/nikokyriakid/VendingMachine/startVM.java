package com.nikokyriakid.VendingMachine;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Scanner;

public class startVM {
    public static void main(String[] args) {
        int slot, quantity, input, slotSize = 10;
        double price;
        boolean alive = true;
        String coins, ary[];
        VendingMachineImpl vm = new VendingMachineImpl(slotSize, Arrays.asList(0.10, 0.20, 0.50, 1.0, 2.0));

        System.out.println("================ Vending Machine by NK =================");
        Scanner reader = new Scanner(System.in);
        while(alive) {
            System.out.println("========================================================");
            System.out.println("1. Set Quantity   | 2. Set Price      | 3. Buy Product\n" +
                                "4. Add Coins      | 5. Print Slots    | 6. Print Change\n" +
                                "7. Populate Slots | 8. Populate Coins | 9. Reset | 0. Exit");
            System.out.print("Select an action: ");

            try {
                input = reader.nextInt();
            } catch (Exception e) {
                reader.nextLine();
                input = -1; // Will default to non valid action.
            }

            switch (input) {
                case 0:
                    System.out.print("App terminated.");
                    System.exit(0);
                    break;
                case 1:
                    System.out.print("Type slot (0-9): ");
                    slot = reader.nextInt();
                    System.out.print("Type quantity (0-9): ");
                    quantity = reader.nextInt();
                    vm.setQuantity(slot, quantity);
                    break;
                case 2:
                    System.out.print("Type slot (0-9): ");
                    slot = reader.nextInt();
                    System.out.print("Type Price (eg 1.2): ");
                    price = reader.nextDouble();
                    vm.setPrice(slot, price);
                    break;
                case 3:
                    System.out.print("Type slot (0-9): ");
                    slot = reader.nextInt();
                    System.out.print("Put Coins (eg. 1,1,2,0.5,0.2): ");
                    coins = reader.next();
                    ary = coins.split(",");

                    try {
                        vm.buyProduct(slot, Arrays.asList(ary).stream().map(s -> Double.parseDouble(s)).collect(toList()));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Type Coins (eg. 1,1,2,0.5,0.2): ");
                    coins = reader.next();
                    ary = coins.split(",");
                    try {
                        vm.addCoinsByList(Arrays.asList(ary).stream().map(s -> Double.parseDouble(s)).collect(toList()), true);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    vm.displaySlots();
                    break;
                case 6:
                    vm.displayCoins();
                    break;
                case 7:
                    startVM.populateDefaults(vm);
                    break;
                case 8:
                    startVM.populateCoins(vm);
                    break;
                case 9:
                    vm.reset();
                    System.out.println("Vending Machine is reset.");
                    break;
                default:
                    System.out.println("No valid action. Input must be an integer number between 0-9");
                    break;
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
