package com.kata.catalog;

import com.kata.promotions.MultiBuyOffer;

/**
 * I have opted to apply the offer to the SKU rather than have an offer with a list of SKUs as I feel this is more realistic.
 */
public class Sku {
    private String skuId;
    private int unitPrice;
    private MultiBuyOffer offer;

    public Sku() {
    }

    public Sku(String skuId, int unitPrice) {
        this.skuId = skuId;
        this.unitPrice = unitPrice;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public MultiBuyOffer getOffer() {
        return offer;
    }

    public void setOffer(MultiBuyOffer offer) {
        this.offer = offer;
    }
    }
