package com.slackers.inc.Controllers;


import com.slackers.inc.database.entities.Label;

import java.lang.reflect.Type;

/**
 * Created by jestrada on 4/2/17.
 */
public class TypeFilter implements Filter {



    Label.BeverageType value;

    public TypeFilter(Label.BeverageType value){
        this.value = value;
    }

    @Override
    public Label preApply(Label aFilter) {
        aFilter.setProductType(value);
        return aFilter;
    }

}