package com.slackers.inc.Controllers;

import com.slackers.inc.database.entities.ColaUser;
import com.slackers.inc.database.entities.Label;

import java.sql.SQLException;
import java.util.List;

public class COLASearchController {

    ColaUser colaUser;
    SearchController searchControl;

    /**
     * Edited by Fabio Borges on 4/2/17
     */
    public COLASearchController()
    {
        this.colaUser = null;
        this.searchControl = new SearchController();
    }

    /**
     * Edited by Fabio Borges on 4/2/17
     */
    public COLASearchController(ColaUser user, SearchController searchControl)
    {
        this.colaUser = user;
        this.searchControl = searchControl;
    }

    /**
     * Created by Fabio Borges on 4/2/17
     * Sets the COLA user associated with this controller
     */
    public void setColaUser(ColaUser colaUser)
    { this.colaUser = colaUser; }

    /**
     * Created by Fabio Borges on 4/2/17
     * Returns the COLA user associated with this controller
     */
    public ColaUser getColaUser()
    { return colaUser; }

    /**
     * Created by Michael Steidel on 4/2/17
     * Sets the search controller associated with this controller
     */
    public void setSearchControl(SearchController searchControl) {
        this.searchControl = searchControl;
    }

    /**
     * Created by Michael Steidel on 4/2/17
     * Returns the search controller associated with this controller
     */
    public SearchController getSearchControl() {
        return searchControl;
    }

    /**
     * Created by Michael Steidel on 4/2/17
     * Searches the database for a specified label and returns the list of search results
     */
    public List search(Label target) throws SQLException
    {
        return searchControl.runSearch(target);
    }

}
