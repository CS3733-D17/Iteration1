/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.database.entities;

import com.slackers.inc.database.IEntity;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author John Stegeman <j.stegeman@labyrinth-tech.com>
 */
public abstract class User implements IEntity{
    
    protected static final String TABLE = "USERS";
    
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public User(String username, String password) {
        this(username, password, "");
    }
    
    public User(String username) {
        this(username, "", "");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    @Override
    public Map<String, Object> getEntityValues() {
        Map<String,Object> temp = new HashMap<>();
        temp.put("username", this.username);
        temp.put("password", this.password);
        temp.put("email", this.email);
        return temp;
    }

    @Override
    public Map<String, Object> getUpdatableEntityValues() {
        Map<String,Object> temp = new HashMap<>();
        temp.put("username", this.username);
        temp.put("password", this.password);
        temp.put("email", this.email);
        return temp;
    }

    @Override
    public void setEntityValues(Map<String, Object> values) {
        if (values.containsKey("username"))
            this.username = (String)values.get("username");
        if (values.containsKey("password"))
            this.password = (String)values.get("password");
        if (values.containsKey("email"))
            this.email = (String)values.get("email");
    }

    @Override
    public Map<String, Class> getEntityNameTypePairs() {
        Map<String,Class> temp = new HashMap<>();
        temp.put("username", String.class);
        temp.put("password", String.class);
        temp.put("email", String.class);
        return temp;
    }
}
