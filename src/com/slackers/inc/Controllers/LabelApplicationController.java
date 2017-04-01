/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.Controllers;

import com.slackers.inc.database.DerbyConnection;
import com.slackers.inc.database.entities.ApplicationApproval;
import com.slackers.inc.database.entities.LabelApplication;
import com.slackers.inc.database.entities.LabelApplication.RevisionType;
import com.slackers.inc.database.entities.LabelComment;
import com.slackers.inc.database.entities.Manufacturer;
import com.slackers.inc.database.entities.UsEmployee;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;

/**
 *
 * @author John Stegeman <j.stegeman@labyrinth-tech.com>
 */
public class LabelApplicationController {
    
    private DerbyConnection db;
    private LabelApplication application;

    public LabelApplicationController(LabelApplication application) throws SQLException {
        db = DerbyConnection.getInstance();
        this.application = application;
    }
    
    public LabelApplicationController() throws SQLException {
        this(new LabelApplication());
    }
    
    public LabelApplication getLabelApplication()
    {
        return this.application;
    }
    
    public boolean setNewReviewer(UsEmployee employee, LabelComment comment) throws SQLException
    {
        this.application.setSubmitter(this.application.getReviewer());
        this.application.setReviewer(employee);
        this.application.getComments().add(comment);       
        this.application.setStatus(LabelApplication.ApplicationStatus.SUBMITTED_FOR_REVIEW);
        return db.writeEntity(this.application, this.application.getPrimaryKeyName());
    }
    
    public boolean attachComment(LabelComment coment) throws SQLException
    {
        this.application.getComments().add(coment);
        return db.writeEntity(this.application, this.application.getPrimaryKeyName());
    }
    
    public boolean attachApproval(ApplicationApproval approval) throws SQLException
    {
        this.application.setApplicationApproval(approval);
        return db.writeEntity(this.application, this.application.getPrimaryKeyName());
    }
    
    public boolean isRevisionAllowed(RevisionType type)
    {
        return this.application.getAllowedRevisions().contains(type);
    }
    
    public boolean saveApplication() throws SQLException
    {
        return db.writeEntity(this.application, this.application.getPrimaryKeyName());
    }
    
    public boolean editApplication() throws SQLException
    {
        return db.writeEntity(this.application, this.application.getPrimaryKeyName());
    }

    public boolean deleteApplication() throws SQLException
    {
        return db.deleteEntity(this.application, this.application.getPrimaryKeyName());
    }

    public boolean createApplication() throws SQLException
    {
        db.createEntity(this.application);
        return true;
    }
    
    public boolean createApplication(LabelApplication application) throws SQLException
    {
        this.application = application;
        return this.createApplication();
    }  
    
    public boolean submitApplication(Manufacturer submitter) throws SQLException
    {
        this.application.setApplicant(submitter);
        this.application.setStatus(LabelApplication.ApplicationStatus.SUBMITTED_FOR_REVIEW);
        this.application.setApplicationDate(new Date(new java.util.Date().getTime()));
        this.application.setSubmitter(null);
        this.application.setReviewer(null);
        this.application.setApplicationApproval(null);
        this.application.setAllowedRevisions(new HashSet<>());
        return db.writeEntity(this.application, this.application.getPrimaryKeyName());
    }
    
    public boolean approveApplication(UsEmployee submitter, Date experationDate) throws SQLException
    {
        ApplicationApproval approval = new ApplicationApproval(submitter, experationDate, this.application);
        this.application.setStatus(LabelApplication.ApplicationStatus.APPROVED);
        this.application.setApplicationApproval(approval);
        return db.writeEntity(this.application, this.application.getPrimaryKeyName());
    }
    
    public boolean rejectApplication(LabelComment comment) throws SQLException
    {
        this.application.setStatus(LabelApplication.ApplicationStatus.REJECTED);
        return this.attachComment(comment);
    }
    
    public boolean sendForCorrections(LabelComment comment) throws SQLException
    {
        this.application.setStatus(LabelApplication.ApplicationStatus.SENT_FOR_CORRECTIONS);
        return this.attachComment(comment);
    }
    
    public boolean autoSelectReviewer()
    {
        
        
        return false;
    }
}
