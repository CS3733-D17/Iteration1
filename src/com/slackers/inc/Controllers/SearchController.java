package com.slackers.inc.Controllers;

import com.slackers.inc.database.entities.Label;

import java.util.List;

/**
 * @author Created by SrinuL on 4/1/17.
 */
public class SearchController {

    String keywords;
    List<String> filters;

    public SearchController(){

    }

    public void filters(){
        // NOTE I Have no Idea what this is supposed to be
    }

    public void addFilter(){

    }

    public void removeFilter(){

    }

    public List<Label> runSearch(){
        /*  I know that the class diagram says that this is supposed to be void, but would be nice
         *  if it returned a list of labels so we could display those onto the screen
         */
        return null;
    }

}
