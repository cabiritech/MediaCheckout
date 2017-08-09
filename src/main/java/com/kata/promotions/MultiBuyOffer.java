package com.kata.promotions;

public interface MultiBuyOffer {

    public int getQualifyingThreshold();

    public String getOfferDescription();

    public double getDiscount();

    public boolean isDeduction();

    public boolean isMultiplier();

}
