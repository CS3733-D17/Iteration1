package com.slackers.inc.Controllers;

import com.slackers.inc.database.entities.ColaUser;

public class COLASearchController {

    ColaUser colaUser;
    SearchController searchControl;

    /**
     * @author Edited by Fabio Borges on 4/2/17
     */
    public COLASearchController()
    {
        this.colaUser = null;
        this.searchControl = new SearchController();
    }

    /**
     * @author Edited by Fabio Borges on 4/2/17
     * @param user
     * @param searchControl
     */
    public COLASearchController(ColaUser user, SearchController searchControl)
    {
        this.colaUser = user;
        this.searchControl = searchControl;
    }

    /**
     * @author Created by Fabio Borges on 4/2/17
     * @param colaUser
     */
    public void setColaUser(ColaUser colaUser)
    { this.colaUser = colaUser; }

    /**
     * @author Created by Fabio Borges on 4/2/17
     * @return
     */
    public ColaUser getColaUser();
    { return colaUser; }

    /**
     * @return
     * @author Created by Michael Steidel on 4/2/17
     */
    public void setSearchControl(SearchController searchControl) {
        this.searchControl = searchControl;
    }

    /**
     * @return
     * @author Created by Michael Steidel on 4/2/17
     */
    public SearchController getSearchControl() {
        return searchControl;
    }

    public List<Entity> search(Label target) throws SQLException
    {
        searchControl.runSearch(target);
    }

}
