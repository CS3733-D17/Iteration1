/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.database.entities;

import com.slackers.inc.database.DerbyConnection;
import com.slackers.inc.database.IEntity;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John Stegeman <j.stegeman@labyrinth-tech.com>
 */
public class ApplicationApproval implements IEntity{
    
    private static final String TABLE = "LABEL_APPROVALS";
    
    private UsEmployee agent;
    private Date approvalDate;
    private Date experationDate;
    private LabelApplication application;
    private long approvalId;

    public ApplicationApproval(UsEmployee agent, Date approvalDate, Date experationDate, LabelApplication application) {
        this.agent = agent;
        this.approvalDate = approvalDate;
        this.experationDate = experationDate;
        this.application = application;
        this.approvalId = 0;
    }
    
    public ApplicationApproval(UsEmployee agent, Date experationDate, LabelApplication application) {
        this(agent, new Date(new java.util.Date().getTime()), experationDate, application);
    }

    public long getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(long approvalId) {
        this.approvalId = approvalId;
    }
    
    public LabelApplication getApplication() {
        return application;
    }

    public void setApplication(LabelApplication applicationId) {
        this.application = applicationId;
    }   
    
    public UsEmployee getAgent() {
        return agent;
    }

    public void setAgent(UsEmployee agent) {
        this.agent = agent;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getExperationDate() {
        return experationDate;
    }

    public void setExperationDate(Date experationDate) {
        this.experationDate = experationDate;
    }

    @Override
    public String getTableName() {
        return TABLE;
    }

    @Override
    public Map<String, Object> getEntityValues() {
        Map<String, Object> values = new HashMap<>();
        values.put("approvalDate", this.approvalDate);
        values.put("experationDate", this.experationDate);
        values.put("agent", this.agent.getPrimaryKeyValue());
        values.put("application", this.application.getPrimaryKeyValue());
        values.put("approvalId", this.approvalId);
        return values;
    }

    @Override
    public Map<String, Object> getUpdatableEntityValues() {
        Map<String, Object> values = new HashMap<>();
        values.put("approvalDate", this.approvalDate);
        values.put("experationDate", this.experationDate);
        values.put("agent", this.agent.getPrimaryKeyValue());
        values.put("application", this.application.getPrimaryKeyValue());
        return values;
    }

    @Override
    public void setEntityValues(Map<String, Object> values) {
        if (values.containsKey("approvalDate"))
        {
            this.approvalDate = (Date)values.get("approvalDate");
        }
        if (values.containsKey("experationDate"))
        {
            this.experationDate = (Date)values.get("experationDate");
        }
        if (values.containsKey("approvalId"))
        {
            this.approvalId = (long)values.get("approvalId");
        }
        if (values.containsKey("agent"))
        {
            this.agent.setPrimaryKeyValue((Serializable)values.get("agent"));
            try {
                DerbyConnection.getInstance().getEntity(this.agent, this.agent.getPrimaryKeyName());
            } catch (SQLException ex) {
                Logger.getLogger(ApplicationApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (values.containsKey("application"))
        {
            this.application.setPrimaryKeyValue((Serializable)values.get("application"));
            try {
                DerbyConnection.getInstance().getEntity(this.application, this.application.getPrimaryKeyName());
            } catch (SQLException ex) {
                Logger.getLogger(ApplicationApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Map<String, Class> getEntityNameTypePairs() {
        Map<String, Class> pairs = new HashMap<>();
        pairs.put("approvalDate", Date.class);
        pairs.put("experationDate", Date.class);
        pairs.put("agent", Serializable.class);
        pairs.put("application", Serializable.class);
        pairs.put("approvalId", Long.class);
        return pairs;
    }

    @Override
    public List<String> tableColumnCreationSettings() {
        List<String> cols = new LinkedList<>();
        cols.add("approvalId bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)");
        cols.add("approvalDate Date");
        cols.add("experationDate Date");
        cols.add("application varchar(512)");
        cols.add("agent varchar(512)");
        return cols;
    }

    @Override
    public String getPrimaryKeyName() {
        return "approvalId";
    }

    @Override
    public Serializable getPrimaryKeyValue() {
        return this.approvalId;
    }

    @Override
    public void setPrimaryKeyValue(Serializable value) {
        this.approvalId = (long) value;
    }

    @Override
    public ApplicationApproval deepCopy() {
        ApplicationApproval app = new ApplicationApproval(this.agent, this.experationDate, this.application);
        app.setEntityValues(this.getEntityValues());
        return app;
    }
    
    
    
   
}
