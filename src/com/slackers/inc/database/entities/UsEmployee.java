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
    
    private List<LabelApplication> applications;
    private List<LabelApplication> previousApplications;

    public UsEmployee(String password, String email) {
        super(password, email);
        init();
    }

    public UsEmployee(String email) {
        super(email);
        init();
    }   
    
    private void init()
    {
        this.applications = new LinkedList<>();
        this.previousApplications = new LinkedList<>();
        super.setUserType(UserType.US_EMPLOYEE);
    }

    @Override
    public void setEntityValues(Map<String, Object> values) {
        super.setEntityValues(values);
        if (values.containsKey("applications"))
        {
            this.applications.addAll(LabelApplication.applicationListFromString((String)values.get("applications")));
        }
        if (values.containsKey("previousApplications"))
        {
            this.previousApplications.addAll(LabelApplication.applicationListFromString((String)values.get("previousApplications")));
        }
    }

    @Override
    public Map<String, Object> getUpdatableEntityValues() {
        Map<String, Object> values = super.getUpdatableEntityValues();
        values.put("applications", LabelApplication.applicationListToString(this.applications));
        values.put("previousApplications", LabelApplication.applicationListToString(this.previousApplications));
        return values;
    }

    @Override
    public Map<String, Object> getEntityValues() {
        Map<String, Object> values = super.getEntityValues();
        values.put("applications", LabelApplication.applicationListToString(this.applications));
        values.put("previousApplications", LabelApplication.applicationListToString(this.previousApplications));
        return values;
    }    
}
