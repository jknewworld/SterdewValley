package com.P.model.Maps;
import dev.morphia.annotations.Embedded;
import com.P.model.enums.Ingredients;

@Embedded
public class Stone extends Objects{
    public Stone(Ingredients type, String color, String name){
        super(false,"cyan","#");
    }
}
