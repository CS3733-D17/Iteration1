/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.database.entities;

import com.slackers.inc.database.IEntity;
import java.io.Serializable;
import java.util.*;

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
    
    private String password;
    private String email;
    private UserType userType;

    public User(String email, String password) {
        this.password = password;
        this.email = email;
        this.userType = UserType.UNKNOWN;
    }
    
    public User(String email) {
        this(email, "");
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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
        temp.put("password", this.password);
        temp.put("email", this.email);
        temp.put("userType", this.userType);
        return temp;
    }

    @Override
    public Map<String, Object> getUpdatableEntityValues() {
        Map<String,Object> temp = new HashMap<>();
        temp.put("password", this.password);
        temp.put("userType", this.userType);
        return temp;
    }

    @Override
    public void setEntityValues(Map<String, Object> values) {
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
        pairs.put("password", String.class);
        pairs.put("email", String.class);
        pairs.put("applications", String.class);
        pairs.put("previousApplications", String.class);
        pairs.put("templateApplication", Integer.class);
        pairs.put("userType", Serializable.class);
        return pairs;
    }

    @Override
    public String getPrimaryKeyName() {
        return "email";
    }

    @Override
    public Serializable getPrimaryKeyValue() {
        return this.email;
    }

    @Override
    public void setPrimaryKeyValue(Serializable value) {
        this.email = (String) value;
    }

    
    @Override
    public List<String> tableColumnCreationSettings() {
        List<String> cols = new LinkedList<>();
        cols.add("password varchar(256)");
        cols.add("email varchar(256) PRIMARY KEY");
        cols.add("applications varchar(32672)");
        cols.add("previousApplications varchar(32672)");
        cols.add("templateApplication varchar(8192)");
        cols.add("userType varchar(1024)");
        return cols;
    }

    @Override
    public String getTableName() {
        return TABLE;
    }
    
    @Override
    public User deepCopy() {
        User usr = new User("");
        usr.setEntityValues(this.getEntityValues());
        return usr;
    }
    
}
