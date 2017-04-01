package com.slackers.inc.Controllers;

import com.slackers.inc.database.entities.Manufacturer;

public class ManufacturerController {

    Manufacturer manufacturer;
    LabelApplicationController formController;

    public ManufacturerController(){
        this.manufacturer = null;
        this.formController = null;
    }

    public ManufacturerController(Manufacturer manufacturer, LabelApplicationController formController){
        this.manufacturer = manufacturer;
        this.formController = formController;
    }

    public boolean createApplication(){
        return true;
    }

    public boolean submitApplication(){
        return true;
    }

    public boolean editApplication(){
        return true;
    }

    public boolean saveApplication(){
        return true;
    }

    public boolean deleteApplication(){
        return true;
    }

    public LabelApplicationController getFormController(){
        return formController;
    }

}
