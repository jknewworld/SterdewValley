package com.P.model.Objects;

import com.P.model.enums.BarnType;

import dev.morphia.annotations.Embedded;

@Embedded
public class ShopBarn extends ShopItem {
    private  BarnType type;

    public ShopBarn(BarnType type, int dailyLimit) {
        super(dailyLimit);
        this.type = type;
    }

    public ShopBarn() {
    }

    public BarnType getType() {
        return type;
    }
}
