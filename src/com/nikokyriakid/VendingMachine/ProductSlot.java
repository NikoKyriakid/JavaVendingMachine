package com.nikokyriakid.VendingMachine;

public class ProductSlot {
    private int name;
    private double price;
    private int quantity;

    public ProductSlot(int name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductSlot(int name) {
        this.name = name;
        this.price = 0.0;
        this.quantity = 0;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be a negative value.");
        }

        if (price > 0 && this.quantity == 0) {
            throw new IllegalStateException("No quantity in this slot to set a price for.");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Non valid integer to set the quantity of a slot.");
        }
        this.quantity = quantity;
    }

    public void subtract() {
        if (this.quantity < 1) {
            throw new IllegalStateException("Not enough items in the slot.");
        }
        this.quantity -= 1;
    }

    public void display(String format) {
        if (format.isEmpty()) {
            System.out.println(Integer.toString(this.name) + " : " + this.price + " : " + this.quantity);
        } else {
            System.out.printf(format, this.name, this.price, this.quantity);
        }
    }
}
