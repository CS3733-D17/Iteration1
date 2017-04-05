package com.slackers.inc.Controllers;

import com.slackers.inc.Boundary.BoundaryControllers.FormController;
import com.slackers.inc.Controllers.LabelApplicationController;
import com.slackers.inc.database.entities.*;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by paluro on 3/31/17.
 */
public class UsEmployeeController {
    private UsEmployee employee;
    private LabelApplicationController formController;


    //constructor
    // controller for anything USEmployee related. allows a cleaner flow of code to be used and take advantage of other controllers
    public UsEmployeeController(UsEmployee employee, LabelApplicationController formController){
        this.employee = employee;
        this.formController=formController;
    }

    public UsEmployeeController(UsEmployee employee){
        this.employee = employee;
    }

    //methods:

   //allows a USEmployee to pull an application from the database for viewing
    public boolean pullNewApplications(LabelApplication application, Label label, LabelComment comment){
        this.employee.getApplications().remove(application);
        return true;
    }

    //allows a USEmployee to accept an application
    public boolean acceptApplicaton(LabelApplication application,Label label, LabelComment comment, Date experationDate){

        try {
            formController.approveApplication(this.employee, experationDate);
            formController.attachComment(comment);
        } catch (SQLException e) {
            return false;
        }
        return true;

        //application.setApplicationApproval(app);
    }
//allows a USEmployee to reject an application back to the manufacturer
    public boolean rejectApplication(LabelApplication application,Label label, LabelComment comment){

        try {
            formController.rejectApplication(comment);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    //allows a USEmployee to send a application back to the manufacturer for revisions
    public boolean sendForRevision(LabelApplication application, UsEmployee employee, LabelComment comment){
        try {
            formController.sendForCorrections(comment);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    //allows a USEmployee to send the application to another USEmployee for secondary review
    public boolean sendForSecondOpinion(LabelApplication application,UsEmployee employee, LabelComment comment){
        try {
            formController.setNewReviewer(employee, comment);
        } catch (SQLException e) {
            return false;
        }
        return true;

    }

    public UsEmployee getEmployee() {
        return employee;
    }

    public LabelApplicationController getFormController() {
        return formController;
    }

    
}
