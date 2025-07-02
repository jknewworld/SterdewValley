package com.P.model.Naturals;

import com.P.model.enums.DairyTypes;
import dev.morphia.annotations.Embedded;

@Embedded
public class Dairy {
    private DairyTypes dairyTypes;

    public Dairy() {
    }
}
