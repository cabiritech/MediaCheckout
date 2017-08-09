package com.kata.checkout;


import com.kata.catalog.Sku;
import com.kata.promotions.MultiBuyDiscount;
import com.kata.promotions.MultiBuyOffer;
import com.kata.promotions.MultiBuyPercentageReduction;

public class CheckoutSimulation {

    public static void main(String[] args) {
        Sku skuA = new Sku("A", 50);
        Sku skuB = new Sku("B", 30);
        Sku skuC = new Sku("C", 20);
        Sku skuD = new Sku("D", 15);

        MultiBuyOffer buyThreeGetTwentyOff = new MultiBuyDiscount(3, "Buy 3 Save 20p", 20);
        MultiBuyOffer buyTwoGetAQuarterOff = new MultiBuyPercentageReduction(2, "Buy one, get the second half price", 0.25);

        skuA.setOffer(buyThreeGetTwentyOff);
        skuB.setOffer(buyTwoGetAQuarterOff);

        Basket basket = new Basket();
        /*
         * Following on from the brief the underlying tests for the Basket and the PriceCalculator show that the calculations
         * all work in a generic manner. To map to the example in the brief this method will run through the following.
         */

        System.out.println("\nAdd 2 of A");
        basket.addItem(skuA, 2);
        reportBasket(basket);

        System.out.println("\nAdd 1 of B");
        basket.addItem(skuB, 1);
        reportBasket(basket);

        System.out.println("\nAdd 1 of A");
        basket.addItem(skuA, 1);
        reportBasket(basket);

        System.out.println("\nAdd 2 of C");
        basket.addItem(skuC, 2);
        reportBasket(basket);

        System.out.println("\nAdd 1 of B");
        basket.addItem(skuB, 1);
        reportBasket(basket);

        System.out.println("\nAdd 1 of D");
        basket.addItem(skuD, 1);
        reportBasket(basket);
    }

    private static void reportBasket(Basket basket) {
        System.out.println("=================================");
        System.out.println("| ID | Price/p | QTY | Total/£  |");
        System.out.println("=================================");
        for (LineItem lineItem : basket.getLineItems()) {
            System.out.println(String.format("| %s  |  %d     |  %d   |  %.2f   |", lineItem.getSku().getSkuId(), lineItem.getSku().getUnitPrice(), lineItem.getQty(), lineItem.getLineItemTotal()));
        }
        System.out.println("=================================");
    }
}
