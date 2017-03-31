package com.slackers.inc.Controllers;

import com.slackers.inc.database.DerbyConnection;
import com.slackers.inc.database.entities.Manufacturer;
import com.slackers.inc.database.entities.UsEmployee;
import com.slackers.inc.database.entities.User;

import java.sql.SQLException;

/**
 * @author Created by SrinuL on 3/30/17.
 */
public class AccountController {

    /* TODO Decide to Either Rename it To LoginController and Rename the Boundary Controller (in com.slackers.inc.Boundary.BoundaryControllers)
     * TODO Can Also Just Keep Like This
     */

    private DerbyConnection db;

    public AccountController() throws SQLException {
        db = DerbyConnection.getInstance();
    }

    // returns true if the credentials are valid, and false otherwise
    public boolean verifyCredentials(String email, String password) throws SQLException {

        // TODO Issue: Need a Regular User (Not an Employee or Manufacturer, but a Customer) but User class is Abstract

        User user = new User("", email, password);
        try {
            db.getEntity(user, "EmailAddress");
        } catch (SQLException e) {
            System.out.println("Trouble accessing database for login verification");
            throw e;
        }

        return password.equals(user.getPassword());
    }


}


