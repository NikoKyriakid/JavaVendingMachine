package com.nikokyriakid.VendingMachine;

import java.util.List;

public interface VendingMachine {
    public void setQuantity(int i, int quantity);
    public int getQuantity(int i);

    public double getPrice(int slot);
    public void setPrice(int slot, double price);

    public int getCoins(double type);
    public void setCoins(double type, int amountOfCoins);

    public void addCoins(double type, int amountOfCoinsToAdd);
    public void removeCoins(double type, int amountOfCoinsToRemove);

    public void reset();
}