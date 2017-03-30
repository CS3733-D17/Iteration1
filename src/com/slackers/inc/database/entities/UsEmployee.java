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

    private List<LabelApplication> applications;
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
        //pairs.put("applications", List<LabelApplication>); // need to modify to work
        pairs.put("templateApplication", LabelApplication.class);
        return pairs;
    }

    @Override
    public void setEntityValues(Map<String, Object> values) {
        super.setEntityValues(values);
        if (values.containsKey("applications"))
            this.applications = (List<LabelApplication>)values.get("applications");
        if (values.containsKey("templateApplication"))
            this.templateApplication = (LabelApplication)values.get("templateApplication");
    }

    @Override
    public Map<String, Object> getUpdatableEntityValues() {
        Map<String, Object> values = super.getUpdatableEntityValues();
        values.put("applications", this.applications);
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
