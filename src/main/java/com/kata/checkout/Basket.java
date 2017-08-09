package com.kata.checkout;

import com.kata.catalog.Sku;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private List<LineItem> lineItems = new ArrayList<>();
    private PriceCalculator priceCalculator = new PriceCalculator();

    private double basketTotal;

    public void addItem(Sku sku, int quantity) {
        if (isSkuInBasket(sku)) {
            incrementLineItem(sku, quantity);
        } else {
            getLineItems().add(new LineItem(sku, quantity));
        }
        getPriceCalculator().priceBasket(this);
    }

    private boolean isSkuInBasket(Sku sku) {
        for (LineItem lineItem : getLineItems()) {
            if (lineItem.getSku().equals(sku)) {
                return true;
            }
        }
        return false;
    }

    private void incrementLineItem(Sku sku, int quantity) {
        for (LineItem lineItem : getLineItems()) {
            if (lineItem.getSku().equals(sku)) {
                lineItem.setQty(lineItem.getQty() + quantity);
                break;
            }
        }
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public PriceCalculator getPriceCalculator() {
        return priceCalculator;
    }

    public void setPriceCalculator(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    public double getBasketTotal() {
        return basketTotal;
    }

    public void setBasketTotal(double basketTotal) {
        this.basketTotal = basketTotal;
    }
}
