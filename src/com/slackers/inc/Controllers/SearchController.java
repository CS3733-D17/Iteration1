package com.slackers.inc.Controllers;

import com.slackers.inc.Controllers.Filters.Filter;
import com.slackers.inc.database.DerbyConnection;
import com.slackers.inc.database.IEntity;
import com.slackers.inc.database.entities.Label;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by SrinuL on 4/1/17.
 */
public class SearchController {

    List<Filter> filters;
    List<String> columns;
    DerbyConnection db;

    public SearchController(){
        filters = new ArrayList<>();
        columns = new ArrayList<>();
        db = DerbyConnection.getInstance();
    }

    public void filters(){
        // NOTE I Have no Idea what this is supposed to be
    }

    public void addFilter(Filter filter){
        filters.add(filter);

    }

    public void removeFilter(){

    }

    public List<IEntity> runSearch(Label target) throws SQLException {
        /*  I know that the class diagram says that this is supposed to be void, but would be nice
         *  if it returned a list of labels so we could display those onto the screen
         */
        filters.forEach(filter -> {
            filter.preApply(target);
            columns.add(filter.getColumn());
        });

        return db.getAllEntites(target, columns.toArray(new String[columns.size()]));
    }

}
