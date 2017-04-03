package com.slackers.inc.Controllers;

import com.slackers.inc.database.entities.LabelApplication;
import com.slackers.inc.database.entities.Manufacturer;

import java.sql.SQLException;

public class ManufacturerController {

    Manufacturer manufacturer;
    LabelApplicationController labelAppController;

    public ManufacturerController() throws SQLException {
        this.manufacturer = null;
        this.labelAppController = new LabelApplicationController();
    }

    public ManufacturerController(Manufacturer manufacturer) throws SQLException {
        this.manufacturer = manufacturer;
        this.labelAppController = new LabelApplicationController();
    }

    public ManufacturerController(Manufacturer manufacturer, LabelApplicationController labelAppController){
        this.manufacturer = manufacturer;
        this.labelAppController = labelAppController;
    }

    public boolean createApplication() throws SQLException {
        LabelApplication template = manufacturer.getTemplateApplication();
        LabelApplication app = labelAppController.getLabelApplication();
        app.setApplicant(manufacturer);
        if (template != null) {
            app.setApplicantAddress(template.getApplicantAddress());
            app.setEmailAddress(template.getEmailAddress());
            app.setMailingAddress(template.getMailingAddress());
            app.setLabel(template.getLabel());
            app.setPhoneNumber(template.getPhoneNumber());
            app.setStatus(LabelApplication.ApplicationStatus.SUBMITTED_FOR_REVIEW);

        }
        return labelAppController.createApplication();

    }

    public boolean submitApplication() throws SQLException {

        return labelAppController.submitApplication(manufacturer);
    }

    public boolean editApplication() throws SQLException {

        return labelAppController.editApplication();
    }

    public boolean saveApplication() throws SQLException {

        return labelAppController.saveApplication();
    }

    public boolean deleteApplication() throws SQLException {

        return labelAppController.deleteApplication();
    }

    public LabelApplicationController getLabelAppController(){
        return labelAppController;
    }

}
