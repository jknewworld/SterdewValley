package com.P.model.Maps;
import dev.morphia.annotations.Embedded;
import com.P.model.enums.CropName;

@Embedded
public class WildSeeds extends Objects{

    public WildSeeds(CropName crop) {
        super(true,"purple","seeds");
    }
}
