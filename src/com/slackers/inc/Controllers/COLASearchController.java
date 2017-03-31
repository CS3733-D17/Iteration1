package com.slackers.inc.Controllers;

import com.slackers.inc.database.entities.ColaUser;

public class COLASearchController {

    ColaUser colaUser;

    public COLASearchController(){
        this.colaUser = null;
    }

    public COLASearchController(ColaUser user){
        this.colaUser = user;
    }

}
