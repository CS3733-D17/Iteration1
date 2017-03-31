/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.database.entities;

import com.slackers.inc.database.IEntity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author John Stegeman <j.stegeman@labyrinth-tech.com>
 */
public class User implements IEntity{
    
    private static final String TABLE = "USERS";
    
    public static enum UserType
    {
        UNKNOWN,
        MANUFACTURER,
        US_EMPLOYEE,
        COLA_USER;
    }
    
    private String username;
    private String password;
    private String email;
    private UserType userType;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = UserType.UNKNOWN;
    }
    
    public User(String username, String password) {
        this(username, password, "");
    }
    
    public User(String username) {
        this(username, "", "");
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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
        temp.put("userType", this.userType);
        return temp;
    }

    @Override
    public Map<String, Object> getUpdatableEntityValues() {
        Map<String,Object> temp = new HashMap<>();
        temp.put("username", this.username);
        temp.put("password", this.password);
        temp.put("email", this.email);
        temp.put("userType", this.userType);
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
        if (values.containsKey("userType"))
            this.userType = (UserType)values.get("userType");
    }

    @Override
    public Map<String, Class> getEntityNameTypePairs() {
        Map<String,Class> pairs = new HashMap<>();
        pairs.put("username", String.class);
        pairs.put("password", String.class);
        pairs.put("email", String.class);
        pairs.put("applications", String.class);
        pairs.put("workingApplications", String.class);
        pairs.put("templateApplication", Serializable.class);
        pairs.put("userType", Serializable.class);
        return pairs;
    }

    @Override
    public String getPrimaryKeyName() {
        return "username";
    }

    @Override
    public Serializable getPrimaryKeyValue() {
        return this.username;
    }

    @Override
    public void setPrimaryKeyValue(Serializable value) {
        this.username = (String) value;
    }

    
    @Override
    public List<String> tableColumnCreationSettings() {
        List<String> cols = new LinkedList<>();
        cols.add("username varchar(256) PRIMARY KEY");
        cols.add("password varchar(256)");
        cols.add("email varchar(256)");
        cols.add("applications varchar(max)");
        cols.add("previousApplications varchar(max)");
        cols.add("templateApplication varchar(8192)");
        cols.add("userType varchar(1024)");
        return cols;
    }

    @Override
    public String getTableName() {
        return TABLE;
    }
    
}
