package com.slackers.inc.Controllers.Filters;

import com.slackers.inc.database.entities.Label;

/**
 * Created by Matt on 4/2/2017.
 */
public class VintageFilter implements Filter {

    int vintage;

    public VintageFilter(int vintage){
        this.vintage = vintage;
    }

    public Label preApply(Label label){
        label.setVintage(vintage);
        return label;
    }

    @Override
    public String getColumn() {
        return "vintage";
    }

}
