package com.slackers.inc.Controllers;

import com.slackers.inc.database.entities.LabelApplication;
import com.slackers.inc.database.entities.LabelComment;
import com.slackers.inc.database.entities.UsEmployee;

public class USEmployeeController {

    UsEmployee employee;
    LabelApplicationController formController;

    public USEmployeeController(){
        this.employee = null;
        this.formController = null;
    }

    public USEmployeeController(UsEmployee employee, LabelApplicationController formController){
        this.employee = employee;
        this.formController = formController;
    }

    public boolean pullNewApplications(LabelApplication application, LabelComment comment){
        return true;
    }

    public boolean acceptApplication(LabelApplication application, LabelComment comment){
        return true;
    }

    public boolean rejectApplication(LabelApplication application, LabelComment comment){
        return true;
    }

    public boolean sendForRevision(LabelApplication application, LabelComment comment){
        return true;
    }

    public boolean sendSecondOpinion(LabelApplication application, LabelComment comment){
        return true;
    }

}
