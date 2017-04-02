package com.slackers.inc.Controllers;


import com.slackers.inc.database.entities.Label;

/**
 * Created by jestrada on 4/2/17.
 */
public class IDFilter implements Filter {

    long id;

    public IDFilter(long id){
        this.id = id;
    }

    @Override
    public Label preapply(Label aFilter) {
        aFilter.setLabelId(id);
        return aFilter;
    }

}
