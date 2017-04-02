package com.slackers.inc.Controllers;


import com.slackers.inc.database.entities.Label;

/**
 * Created by jestrada on 4/2/17.
 */
public class AlcoholFilter implements Filter {

    double percent;

    public AlcoholFilter(double percent){
        this.percent = percent;
    }

    @Override
    public Label preapply(Label aFilter) {
        aFilter.setAlchoholContent(percent);
        return aFilter;
    }

}
