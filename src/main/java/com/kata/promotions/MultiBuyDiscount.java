package com.kata.promotions;

public class MultiBuyDiscount implements MultiBuyOffer {

    private static final String MULTI_BUY_DISCOUNT = "MultiBuyDiscount";

    private int qualifyingThreshold;
    private String offerDescription = MULTI_BUY_DISCOUNT;
    private double discount;

    public MultiBuyDiscount(int qualifyingThreshold, int discount) {
        this.qualifyingThreshold = qualifyingThreshold;
        this.discount = discount;
    }

    public MultiBuyDiscount(int qualifyingThreshold, String offerDescription, int discount) {
        this.qualifyingThreshold = qualifyingThreshold;
        this.offerDescription = offerDescription;
        this.discount = discount;
    }

    @Override
    public int getQualifyingThreshold() {
        return qualifyingThreshold;
    }

    @Override
    public String getOfferDescription() {
        return offerDescription;
    }

    @Override
    public double getDiscount() {
        return discount;
    }

    @Override
    public boolean isDeduction() {
        return true;
    }

    @Override
    public boolean isMultiplier() {
        return false;
    }
}
