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
    public UsEmployeeController(UsEmployee employee, LabelApplicationController formController){
        this.employee = employee;
        this.formController=formController;
    }

    //methods:

    boolean pullNewApplications(LabelApplication application, Label label, LabelComment comment){
        this.employee.getApplications().remove(application);
        return true;
    }
    boolean acceptApplicaton(LabelApplication application,Label label, LabelComment comment, Date experationDate){

        try {
            formController.approveApplication(this.employee, experationDate);
            formController.attachComment(comment);
        } catch (SQLException e) {
            return false;
        }
        return true;

        //application.setApplicationApproval(app);
    }

    boolean rejectApplication(LabelApplication application,Label label, LabelComment comment){

        try {
            formController.rejectApplication(comment);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    boolean sendForRevision(LabelApplication application, UsEmployee employee, LabelComment comment){
        try {
            formController.sendForCorrections(comment);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    boolean sendForSecondOpinion(LabelApplication application,UsEmployee employee, LabelComment comment){
        try {
            formController.setNewReviewer(employee, comment);
        } catch (SQLException e) {
            return false;
        }
        return true;

    }

}
