package com.slackers.inc.Controllers;


import com.slackers.inc.database.entities.Label;

/**
 * Created by jestrada on 4/2/17.
 */
public interface Filter {
    Label preApply(Label aFilter);

}
