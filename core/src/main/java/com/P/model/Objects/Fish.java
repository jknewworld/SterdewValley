package com.P.model.Objects;

import dev.morphia.annotations.Embedded;

import com.P.model.enums.Ingredients;

@Embedded
public class Fish {
    private final Ingredients type;

    public Fish(Ingredients type) {
        this.type = type;
    }

    public Ingredients getType() {
        return type;
    }
}
