package com.P.model.Naturals;

import com.P.model.enums.Ingredients;
import com.P.model.enums.Recipe;
import dev.morphia.annotations.Embedded;

@Embedded
public class Food {
    private Recipe recipe;
    private Ingredients type;

    public Food() {
    }
}
