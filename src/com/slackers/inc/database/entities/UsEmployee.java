/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.database.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author John Stegeman <j.stegeman@labyrinth-tech.com>
 */
public class UsEmployee extends User{

    private static final String TABLE = "EMPLOYEES";
    
    private List<LabelApplication> workingApplications;
    private List<LabelApplication> previousApplications;

    public UsEmployee(String username, String password, String email) {
        super(username, password, email);init();
        
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
        this.workingApplications = new LinkedList<>();
        this.previousApplications = new LinkedList<>();
    }

    @Override
    public Map<String, Class> getEntityNameTypePairs() {
        Map<String, Class> pairs = super.getEntityNameTypePairs();
        pairs.put("workingApplications", String.class);
        pairs.put("workingApplications", String.class);
        return pairs;
    }

    @Override
    public void setEntityValues(Map<String, Object> values) {
        super.setEntityValues(values);
        if (values.containsKey("workingApplications"))
        {
            this.workingApplications.addAll(LabelApplication.applicationListFromString((String)values.get("workingApplications")));
        }
        if (values.containsKey("previousApplications"))
        {
            this.previousApplications.addAll(LabelApplication.applicationListFromString((String)values.get("previousApplications")));
        }
    }

    @Override
    public Map<String, Object> getUpdatableEntityValues() {
        Map<String, Object> values = super.getUpdatableEntityValues();
        values.put("workingApplications", LabelApplication.applicationListToString(this.workingApplications));
        values.put("previousApplications", LabelApplication.applicationListToString(this.previousApplications));
        return values;
    }

    @Override
    public Map<String, Object> getEntityValues() {
        Map<String, Object> values = super.getEntityValues();
        values.put("workingApplications", LabelApplication.applicationListToString(this.workingApplications));
        values.put("previousApplications", LabelApplication.applicationListToString(this.previousApplications));
        return values;
    }
    
    @Override
    public String getTableName() {
        return TABLE;
    }

    @Override
    public List<String> tableColumnCreationSettings() {
        List<String> cols = super.tableColumnCreationSettings();
        cols.add("workingApplications varchar(max)");
        cols.add("previousApplications varchar(max)");
        return cols;
    }
    
}
