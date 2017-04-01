package com.slackers.inc.Controllers;

import com.slackers.inc.database.entities.LabelApplication;
import com.slackers.inc.database.entities.Manufacturer;

import java.sql.SQLException;

public class ManufacturerController {

    Manufacturer manufacturer;
    LabelApplicationController formController;

    public ManufacturerController() throws SQLException {
        this.manufacturer = null;
        this.formController = new LabelApplicationController();
    }

    public ManufacturerController(Manufacturer manufacturer) throws SQLException {
        this.manufacturer = manufacturer;
        this.formController = new LabelApplicationController();
    }

    public ManufacturerController(Manufacturer manufacturer, LabelApplicationController formController){
        this.manufacturer = manufacturer;
        this.formController = formController;
    }

    public boolean createApplication() throws SQLException {
        LabelApplication template = manufacturer.getTemplateApplication();
        LabelApplication app = formController.getLabelApplication();
        app.setApplicant(manufacturer);
        if (template != null) {
            app.setApplicantAddress(template.getApplicantAddress());
            app.setEmailAddress(template.getEmailAddress());
            app.setMailingAddress(template.getMailingAddress());
            app.setLabel(template.getLabel());
            app.setPhoneNumber(template.getPhoneNumber());
            app.setStatus(LabelApplication.ApplicationStatus.SUBMITTED_FOR_REVIEW);

        }
        return formController.createApplication();

    }

    public boolean submitApplication() throws SQLException {

        return formController.submitApplication(manufacturer);
    }

    public boolean editApplication() throws SQLException {

        return formController.editApplication();
    }

    public boolean saveApplication() throws SQLException {

        return formController.saveApplication();
    }

    public boolean deleteApplication() throws SQLException {

        return formController.deleteApplication();
    }

    public LabelApplicationController getFormController(){
        return formController;
    }

}
