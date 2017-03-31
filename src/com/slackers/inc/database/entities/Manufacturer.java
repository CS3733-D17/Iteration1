/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.database.entities;

import com.slackers.inc.database.DerbyConnection;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author John Stegeman <j.stegeman@labyrinth-tech.com>
 */
public class Manufacturer extends User{

    private static final String TABLE = "MANUFACTURERS";

    private List<LabelApplication> applications;
    private LabelApplication templateApplication;
    
    public Manufacturer(String username, String password, String email) {
        super(username, password, email);
        init();
    }

    public Manufacturer(String username, String password) {
        super(username, password);
        init();
    }

    public Manufacturer(String username) {
        super(username);
        init();
    }    
    
    private void init()
    {
        applications = new LinkedList<>();
        templateApplication = new LabelApplication();
    }

    public List<LabelApplication> getApplications() {
        return applications;
    }

    public boolean removeApplications(LabelApplication application) {
        return this.applications.remove(application);
    }
    
    public boolean addApplications(LabelApplication application) {
        return this.applications.add(application);
    }

    public LabelApplication getTemplateApplication() {
        return templateApplication;
    }

    public void setTemplateApplication(LabelApplication templateApplication) {
        this.templateApplication = templateApplication;
    }

    @Override
    public Map<String, Class> getEntityNameTypePairs() {
        Map<String, Class> pairs = super.getEntityNameTypePairs();
        pairs.put("applications", String.class);
        pairs.put("templateApplication", Serializable.class);
        return pairs;
    }

    @Override
    public void setEntityValues(Map<String, Object> values) {
        super.setEntityValues(values);
        if (values.containsKey("applications"))
        {
            this.applications.addAll(LabelApplication.applicationListFromString((String)values.get("applications")));
        }
        if (values.containsKey("templateApplication"))
        {       
            this.templateApplication.setPrimaryKeyValue((Serializable) values.get("templateApplication"));
            try {                
                DerbyConnection.getInstance().getEntity(this.templateApplication, this.templateApplication.getPrimaryKeyName());
            } catch (Exception ex) {
                Logger.getLogger(Manufacturer.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
            
    }

    @Override
    public Map<String, Object> getUpdatableEntityValues() {
        Map<String, Object> values = super.getUpdatableEntityValues();
        values.put("applications", LabelApplication.applicationListToString(this.applications));
        values.put("templateApplication", this.templateApplication.getPrimaryKeyValue());
        return values;
    }

    @Override
    public Map<String, Object> getEntityValues() {
        Map<String, Object> values = super.getEntityValues();
        values.put("applications", LabelApplication.applicationListToString(this.applications));
        values.put("templateApplication", this.templateApplication.getPrimaryKeyValue());
        return values;
    }
    
    @Override
    public String getTableName() {
        return TABLE;
    }

    
    @Override
    public List<String> tableColumnCreationSettings() {
        List<String> cols = super.tableColumnCreationSettings();
        cols.add("applications varchar(max)");
        cols.add("templateApplication varchar(8192)");
        return cols;
    }       
}
