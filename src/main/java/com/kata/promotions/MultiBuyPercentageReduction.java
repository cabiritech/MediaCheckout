package com.kata.promotions;

public class MultiBuyPercentageReduction implements MultiBuyOffer {

    private static final String MULTI_BUY_PERCENTAGE_REDUCTION = "MultiBuyPercentageReduction";

    private int qualifyingThreshold;
    private String offerDescription = MULTI_BUY_PERCENTAGE_REDUCTION;
    private double discount;

    public MultiBuyPercentageReduction(int qualifyingThreshold, double percentageDiscount) {
        this.qualifyingThreshold = qualifyingThreshold;
        this.discount = percentageDiscount;
    }

    public MultiBuyPercentageReduction(int qualifyingThreshold, String offerDescription, double percentageDiscount) {
        this.qualifyingThreshold = qualifyingThreshold;
        this.offerDescription = offerDescription;
        this.discount = percentageDiscount;
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
        return false;
    }

    @Override
    public boolean isMultiplier() {
        return true;
    }
}
