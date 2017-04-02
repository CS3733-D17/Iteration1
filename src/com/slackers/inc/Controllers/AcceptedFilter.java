package com.slackers.inc.Controllers;


import com.slackers.inc.database.entities.Label;

/**
 * Created by jestrada on 4/2/17.
 */
public class AcceptedFilter implements Filter {

    boolean value;

    public AcceptedFilter(boolean value){
        this.value = value;
    }

    @Override
    public Label preapply(Label aFilter) {
        aFilter.setIsAccepted(value);
        return aFilter;
    }

}