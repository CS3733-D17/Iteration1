/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.database.entities;

import com.slackers.inc.database.DerbyConnection;
import java.io.IOException;
import java.io.Serializable;
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
public class UsEmployee extends User{

    private static final String TABLE = "EMPLOYEES";

    private Map<Long, LabelApplication> applications;
    private LabelApplication templateApplication;
    
    public UsEmployee(String username, String password, String email) {
        super(username, password, email);
        init();
    }

    public UsEmployee(String username, String password) {
        super(username, password);
        init();
    }

    public UsEmployee(String username) {
        super(username);
        init();
    }    
    
    private void init()
    {
        applications = new HashMap<>();
        templateApplication = new LabelApplication();
    }

    public Map<Long, LabelApplication> getApplications() {
        return applications;
    }

    public void removeApplications(LabelApplication application) {
        this.applications.remove(application.getApplicationId());
    }
    
    public void addApplications(LabelApplication application) {
        this.applications.put(application.getApplicationId(), application);
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
        pairs.put("templateApplication", LabelApplication.class);
        return pairs;
    }

    @Override
    public void setEntityValues(Map<String, Object> values) {
        super.setEntityValues(values);
        if (values.containsKey("applications"))
        {
            try {
                List<String> appStrings = DerbyConnection.collectionFromString((String)values.get("applications"));
                for (String s : appStrings)
                {
                    LabelApplication temp = new LabelApplication();
                    temp.setPrimaryKeyValue(DerbyConnection.objectFromString(s));
                    DerbyConnection.getInstance().getEntity(temp, temp.getPrimaryKeyName());
                    this.applications.put(temp.getApplicationId(), temp);
                }
            } catch (Exception ex) {
                Logger.getLogger(UsEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (values.containsKey("templateApplication"))
            this.templateApplication = (LabelApplication)values.get("templateApplication");
    }

    @Override
    public Map<String, Object> getUpdatableEntityValues() {
        Map<String, Object> values = super.getUpdatableEntityValues();
        List<String> appIds = new LinkedList<>();
        for (Entry<Long, LabelApplication> e : this.applications.entrySet())
        {
            try {
                appIds.add(DerbyConnection.objectToString((Serializable) e.getValue().getPrimaryKeyValue()));
            } catch (IOException ex) {
                Logger.getLogger(UsEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        values.put("applications", DerbyConnection.collectionToString(appIds));
        values.put("templateApplication", this.templateApplication);
        return values;
    }

    @Override
    public Map<String, Object> getEntityValues() {
        Map<String, Object> values = super.getEntityValues();
        values.put("applications", this.applications);
        values.put("templateApplication", this.templateApplication);
        return values;
    }
    
    @Override
    public String getTableName() {
        return TABLE;
    }

    
    @Override
    public List<String> tableColumnCreationSettings() {
        List<String> cols = new LinkedList<>();
        cols.add("username varchar(256) PRIMARY KEY");
        cols.add("password varchar(256)");
        cols.add("email varchar(256)");
        cols.add("applications varchar(max)");
        cols.add("templateApplication varchar(8192)");
        return cols;
    }       
}
