package com.slackers.inc.Controllers;


import com.slackers.inc.database.entities.Label;

/**
 * Created by jestrada on 4/2/17.
 */
public class PHFilter implements Filter {

    double id;

    public PHFilter(double id){
        this.id = id;
    }

    @Override
    public Label preapply(Label aFilter) {
        aFilter.setPhLevel(id);
        return aFilter;
    }

}