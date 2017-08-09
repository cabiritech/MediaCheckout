package com.kata.checkout;

import com.kata.catalog.Sku;

public class LineItem {

    private Sku sku;
    private int qty;
    private double lineItemTotal;

    public LineItem() {
    }

    public LineItem(Sku sku, int qty) {
        this.sku = sku;
        this.qty = qty;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getLineItemTotal() {
        return lineItemTotal;
    }

    public void setLineItemTotal(double lineItemTotal) {
        this.lineItemTotal = lineItemTotal;
    }
}
