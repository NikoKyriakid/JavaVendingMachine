package com.nikokyriakid.VendingMachine;

import java.util.*;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;

public class VendingMachineImpl implements VendingMachine {

    private int slotsSize;
    private ProductSlot[] slots;
    private TreeMap<Double, Integer> availableCoins;

    public VendingMachineImpl(int slotsSize, List<Double> list) {
        this.slotsSize = slotsSize;
        this.slots = new ProductSlot[slotsSize];

        for (int i=0; i<slotsSize; i++) {
            this.slots[i] = new ProductSlot(i);
        }
        // The built-in Map is an interface. So implementing concrete class on the right side
        availableCoins = new TreeMap<Double,Integer>(Collections.reverseOrder());
        for (Double coinType : list) {
            this.availableCoins.put(coinType, 0);
        }

    }

    private ProductSlot getProductSlot(int slot) {
        if (slot < 0 || slot >= this.slotsSize) {
            // Here we could use ArrayIndexOutOfBoundsException as well
            throw new IllegalArgumentException("The slot number is bigger than the amount of slots in this particular machine.");
        }
        ProductSlot ps = this.slots[slot];
        if (ps == null) {
            throw new IllegalArgumentException("The specified product slot doesn't exist.");
        }
        return ps;
    }

    public ProductSlot[] getProductSlots() {
        return this.slots;
    }


    @Override
    public void reset() {
        for(ProductSlot pSlot: this.slots) {
            pSlot.setPrice(0);
            pSlot.setQuantity(0);
        }
        this.availableCoins.forEach((k,v) -> v = 0);
    }

    @Override
    public int getQuantity(int slot) {
        int quantity = -1;
        try {
            quantity = this.getProductSlot(slot).getQuantity();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return quantity;
    }

    @Override
    public void setQuantity(int slot, int quantity) {
        try {
            this.getProductSlot(slot).setQuantity(quantity);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public double getPrice(int slot) {
        double price = -1;
        try {
            price = this.getProductSlot(slot).getPrice();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return price;
    }

    @Override
    public void setPrice(int slot, double price) {
        try {
            this.getProductSlot(slot).setPrice(price);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getCoins(double type) {
        return this.availableCoins.get(type);
    }

    @Override
    public void setCoins(double type, int amountOfCoins) {
        if (this.availableCoins.containsKey(type)) {
            this.availableCoins.put(type, amountOfCoins);
        } else {
            throw new IllegalArgumentException("This type of coin is not accepted.");
        }
    }

    @Override
    public void addCoins(double type, int amountOfCoinsToAdd) {
        if (amountOfCoinsToAdd < 0) {
            // here we could just say return and print that nothing was changed. As there was nothing to add.
            throw new IllegalArgumentException("Coins amount cannot be a negative value.");
        }
        this.setCoins(type, amountOfCoinsToAdd + this.availableCoins.get(type));
    }

    @Override
    public void removeCoins(double type, int amountOfCoinsToRemove) {
        if (amountOfCoinsToRemove < 0) {
            throw new IllegalArgumentException("Coins amount cannot be a negative value.");
        }
        this.setCoins(type, this.availableCoins.get(type) - amountOfCoinsToRemove);
    }

    /**
     * Gets an List of Doubles eg 1,1,0.5,0.5,0.2,0.2 and adds them one by one
     * to the machine change capacity based on the type.
     * if type is not supported it will log a message stating that.
     *
     * @param coinList  list of coins to be added based on the type of each coin
     */
    public void addCoinsByList(List<Double> coinList) {
        for (Double x: coinList) {
            try {
                this.addCoins(x, 1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Get a map input which is a key value pairs of coin types and
     * the amount of them that needs to be removed from the change capacity.
     *
     * @param coinList The key value pairs of the coin type and the amount of that type
     */
    public void removeCoinsByMap(TreeMap<Double, Integer> coinList) {
        coinList.forEach((type, amount) -> {
            try {
                this.removeCoins(type, amount);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    /**
     * Same as removeCoins(TreeMap<Double, Integer>), but the input would be something like 1,1,0.5,0.5,0.2,0.2
     * ie. a sequence of all the coins not mapped by type and amount as in case of TreeMap
     * For each list item we remove a coin of that type by the amount of one.
     *
     * @param coinList list of coins to be removed based on the type of each coin
     */
    public void removeCoinsByList(List<Double> coinList) {
        for (Double type: coinList) {
            try {
                this.removeCoins(type, 1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public /*List<Double>*/ void buyProduct(int slot, List<Double> coinsIn) {
        double sum = 0.0;
        double price = this.getPrice(slot);
        int quantity = this.getQuantity(slot);

        if (price <= 0) {
            throw new IllegalStateException("The item is sold out (Price not set).");
        }

        if (quantity <= 0) {
            throw new IllegalStateException("The item is sold out.");
        }

        for (Double x : coinsIn) sum += x;

        if (sum < price) {
            throw new IllegalArgumentException("Not enough money provided.");
        }

        /*
         * Adding the coins of the user in the coins inventory, this allows the system to take into account
         * the extra coins when calculating the change that needs to be given back to the user
         */
        this.addCoinsByList(coinsIn);

        /* changeMap is the list of <Double,Integer> representing <typeOfCoin,amount>
         * The list will be populated by the bestSetOfCoins method.
         */
        TreeMap<Double, Integer> changeMap = new TreeMap<Double, Integer>();
        int success = VendingMachineImpl.bestSetOfCoins(sum-price, (Double) this.availableCoins.keySet().toArray()[0], this.availableCoins, changeMap);

        if (success == 1) {
            this.getProductSlot(slot).subtract();
            this.removeCoinsByMap(changeMap);
            System.out.println("Thank you for your purchase");
        } else {
            this.removeCoinsByList(coinsIn);
            System.out.println("We cannot provide enough change.\nPlease collect your money.");
        }
    }

    /**
     * This function is needed in order to achieve better precision when working with doubles
     * Since the internal representation of a number is in binary, certain decimal numbers can't be represented precisely.
     * As a result, some arithmetic operations will give unexpected results in the least significant digits.
     * BigDecimal could be used, but that would be too much of an overhead.
     *
     */
    public static double round(double value) {
        return Math.round(value* 100.0) / 100.0;
    }

    public static int bestSetOfCoins(double remaining, Double type, TreeMap<Double, Integer> coinsList, TreeMap<Double, Integer> map ) {
        int i, res=-1;
        int amount = coinsList.get(type);


        int times = (int) VendingMachineImpl.round(remaining/type.doubleValue());
        times = Math.min(times, amount);

        double total = remaining;
        for (i=times; i>=0; i--) {
            remaining = total - i*type;
            remaining = VendingMachineImpl.round(remaining);
            map.put(type, i);
            Double next = coinsList.higherKey(type);
            if (remaining == 0) {
                res = 1;
            }
            else if (next != null) {
                res = bestSetOfCoins(remaining, next, coinsList, map);
            }
            if (res == 1) {
                return 1;
            }
        }

        return res;
    }

    public void displaySlots() {
        final String format = "%10s%20s%20s%n";
        System.out.printf(format, "Name", "Price", "Quantity");
        System.out.printf("%50s%n","--------------------------------------------");
        for (ProductSlot slot: this.slots) {
            slot.display(format);
        }
    }

    public void displayCoins() {
        final String format = "%10s%20s%n";
        System.out.printf("%50s%n", "--------------------------------------------");
        this.availableCoins.forEach((key,value) -> {
            String strKey;
            if (key >= 1) {
                strKey = Integer.toString(key.intValue()) + '£';
            } else {
                key = key *100;
                strKey = Integer.toString(key.intValue()) + 'p';
            }
            System.out.printf(format,strKey,value);
        });
        System.out.printf("%50s%n", "--------------------------------------------");
    }

}

