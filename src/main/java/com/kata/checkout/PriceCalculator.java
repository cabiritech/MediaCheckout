package com.kata.checkout;

import com.kata.catalog.Sku;

import java.math.BigDecimal;

public class PriceCalculator {

    public PriceCalculator() {
    }

    public void priceBasket(Basket basket) {
        double runningTotal = 0.0;
        for (LineItem item : basket.getLineItems()) {
            priceLine(item);
            runningTotal += item.getLineItemTotal();
        }
        basket.setBasketTotal(runningTotal);
    }

    private void priceLine(LineItem item) {
        Sku itemSku = item.getSku();
        double price;

        if (itemSku.getOffer() != null) {
            price = applyOffer(itemSku, item.getQty());
        } else {
            price = item.getSku().getUnitPrice() * item.getQty();
        }
        BigDecimal total = new BigDecimal(price);
        item.setLineItemTotal(total.divide(new BigDecimal(100.0), 2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
    }

    private double applyOffer(Sku sku, int quantity) {
        int threshold = sku.getOffer().getQualifyingThreshold();

        if (quantity < threshold) {
            return sku.getUnitPrice() * quantity;
        } else {
            double price = 0.0;
            if (sku.getOffer().isDeduction()) {
                price = sku.getUnitPrice() * threshold - sku.getOffer().getDiscount();
            } else if (sku.getOffer().isMultiplier()) {
                double basePrice = sku.getUnitPrice() * threshold;
                price = basePrice - (basePrice * sku.getOffer().getDiscount());
            }
            quantity -= threshold;
            return price + applyOffer(sku, quantity);
        }
    }

}
