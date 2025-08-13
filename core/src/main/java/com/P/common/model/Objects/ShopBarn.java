package com.P.common.model.Objects;

import com.P.common.model.enums.BarnType;

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
