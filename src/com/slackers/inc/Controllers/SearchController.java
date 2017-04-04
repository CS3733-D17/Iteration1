package com.slackers.inc.Controllers;

import com.slackers.inc.Controllers.Filters.Filter;
import com.slackers.inc.database.DerbyConnection;
import com.slackers.inc.database.IEntity;
import com.slackers.inc.database.entities.Label;
import com.slackers.inc.database.entities.User;

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


    public void addFilter(Filter filter){
        filters.add(filter);

    }

    public void removeFilter(Filter filter){
        filters.remove(filter);
    }

    public List<Label> runSearch(Label target) throws SQLException {
        filters.forEach(filter -> {
            filter.preApply(target);
            columns.add(filter.getColumn());
        });

        return db.getAllEntites_Typed(target, columns.toArray(new String[columns.size()]));
    }

}
