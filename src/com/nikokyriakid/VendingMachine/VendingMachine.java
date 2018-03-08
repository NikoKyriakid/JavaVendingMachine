package com.nikokyriakid.VendingMachine;

import java.util.List;

public interface VendingMachine {
    public void setQuantity(int i, int quantity);
    public int getQuantity(int i);

    public double getPrice(int slot);
    public void setPrice(int slot, double price);

    public int getCoins(double type);
    public void setCoins(double type, int numberOfCoins);

    public void addCoin(double type);
    public void addCoins(List<Double> coinList);

    public List<Double> refund();
    public void reset();
}