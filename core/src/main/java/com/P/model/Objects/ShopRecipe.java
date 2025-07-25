package com.P.model.Objects;
import com.P.model.enums.Recipe;

import dev.morphia.annotations.Embedded;

@Embedded
public class ShopRecipe extends ShopItem {
    private final Recipe type;

    public ShopRecipe(Recipe type, int dailyLimit) {
        super(dailyLimit);
        this.type = type;
    }

    public Recipe getType() {
        return type;
    }
}
