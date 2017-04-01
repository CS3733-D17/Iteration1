package com.slackers.inc.Controllers;

import com.slackers.inc.database.DerbyConnection;
import com.slackers.inc.database.entities.ColaUser;
import com.slackers.inc.database.entities.Manufacturer;
import com.slackers.inc.database.entities.UsEmployee;
import com.slackers.inc.database.entities.User;
import com.slackers.inc.database.entities.User.UserType;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Created by SrinuL on 3/30/17.
 */
public class AccountController {

    /* TODO Decide to Either Rename it To LoginController and Rename the Boundary Controller (in com.slackers.inc.Boundary.BoundaryControllers)
     * TODO Can Also Just Keep Like This
     */

    private DerbyConnection db;
    private User user;

    public AccountController(User user) throws SQLException {
        db = DerbyConnection.getInstance();
        this.user = user;
    }
    
    public AccountController() throws SQLException {
        this(new User(null, null));
    }

    // returns true if the credentials are valid, and false otherwise
    public boolean verifyCredentials(String email, String password) throws SQLException {
        
        this.user.setEmail(email);
        this.user.setPassword(null);
        try {
            db.getEntity(user, "email");
        } catch (SQLException e) {
            System.out.println("Trouble accessing database for login verification");
            throw e;
        }
        return password.equals(user.getPassword());
    }


    public User loginUser(String email, String password) throws SQLException, IllegalStateException
    {
        if (this.verifyCredentials(email, password))
        {
            if (this.user.getUserType() == UserType.COLA_USER)
            {
                this.user = new ColaUser(this.user.getEmail(), this.user.getPassword());
            }
            else if (this.user.getUserType() == UserType.MANUFACTURER)
            {
                this.user = new Manufacturer(this.user.getEmail(), this.user.getPassword());
            }
            else if (this.user.getUserType() == UserType.US_EMPLOYEE)
            {
                this.user = new UsEmployee(this.user.getEmail(), this.user.getPassword());
            }
            else
            {
               throw new IllegalStateException("User type is unknown!"); 
            }
            db.getEntity(this.user, this.user.getPrimaryKeyName());
            return this.user;
        }
        else
        {
            return null;
        }
    }
    
    public boolean createAccount(String username, String email, String password, UserType type) throws IllegalStateException
    {
        if (type == UserType.COLA_USER)
        {
            this.user = new ColaUser(email, password);
        }
        else if (type == UserType.MANUFACTURER)
        {
            this.user = new Manufacturer(email, password);
        }
        else if (type == UserType.US_EMPLOYEE)
        {
            this.user = new UsEmployee(email, password);
        }
        else
        {
           throw new IllegalStateException("User type is unknown!"); 
        }

        try {
            return db.createEntity(this.user);
        } catch (SQLException ex) {
            throw new IllegalStateException("User already exists!");
        }
    }
    
    public boolean logout() throws SQLException
    {
        return db.writeEntity(this.user);
    }
    
    public boolean deleteAccount()
    {
        try {
            return db.deleteEntity(this.user, this.user.getPrimaryKeyName());
        } catch (SQLException ex) {
            throw new IllegalStateException("User already exists!");
        }
    }
    
    public boolean editAccount() throws SQLException
    {
        return db.writeEntity(user);
    }
    
    public User getUser()
    {
        return this.user;
    }
}


