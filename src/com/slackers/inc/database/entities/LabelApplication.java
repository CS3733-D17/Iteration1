/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.database.entities;

import com.slackers.inc.database.IEntity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author John Stegeman <j.stegeman@labyrinth-tech.com>
 */
public class LabelApplication implements IEntity{

    public static enum BeverageSource
    {
        DOMESTIC,
        IMPORTED;
    }    
    public static enum BeverageType
    {
        WINE,
        BEER,
        DISTILLED;
    }
    public static enum ApplicationStatus
    {
        SUBMITTED_FOR_REVIEW,
        REJECTED,
        APPROVED,
        SENT_FOR_CORRECTIONS;
    }
    public static enum RevisionType
    {
        CHANGE_NAME;
    }
    
    private long applicationId;
    private String representativeIdNumber;
    private String plantNumber;
    private BeverageSource productSource;
    private BeverageType productType;
    private String brandName;
    private Address applicantAddress;
    private Address mailingAddress;
    private String phoneNumber;
    private String emailAddress;
    private Date applicationDate;
    private ApplicationStatus status;
    private Manufacturer applicant;
    private UsEmployee reviewer;
    private UsEmployee submitter;
    private Label label;
    private List<LabelComment> comments;
    private ApplicationApproval applicationApproval;
    private Set<RevisionType> allowedRevisions;
    
    @Override
    public String getTableName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, Object> getEntityValues() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, Object> getUpdatableEntityValues() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityValues(Map<String, Object> values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, Class> getEntityNameTypePairs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> tableColumnCreationSettings() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getPrimaryKeyName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Serializable getPrimaryKeyValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPrimaryKeyValue(Serializable value) {}

    public void setPrimaryKeyValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public String getRepresentativeIdNumber() {
        return representativeIdNumber;
    }

    public void setRepresentativeIdNumber(String representativeIdNumber) {
        this.representativeIdNumber = representativeIdNumber;
    }

    public String getPlantNumber() {
        return plantNumber;
    }

    public void setPlantNumber(String plantNumber) {
        this.plantNumber = plantNumber;
    }

    public BeverageSource getProductSource() {
        return productSource;
    }

    public void setProductSource(BeverageSource productSource) {
        this.productSource = productSource;
    }

    public BeverageType getProductType() {
        return productType;
    }

    public void setProductType(BeverageType productType) {
        this.productType = productType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Address getApplicantAddress() {
        return applicantAddress;
    }

    public void setApplicantAddress(Address applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Manufacturer getApplicant() {
        return applicant;
    }

    public void setApplicant(Manufacturer applicant) {
        this.applicant = applicant;
    }

    public UsEmployee getReviewer() {
        return reviewer;
    }

    public void setReviewer(UsEmployee reviewer) {
        this.reviewer = reviewer;
    }

    public UsEmployee getSubmitter() {
        return submitter;
    }

    public void setSubmitter(UsEmployee submitter) {
        this.submitter = submitter;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public List<LabelComment> getComments() {
        return comments;
    }

    public void setComments(List<LabelComment> comments) {
        this.comments = comments;
    }

    public ApplicationApproval getApplicationApproval() {
        return applicationApproval;
    }

    public void setApplicationApproval(ApplicationApproval applicationApproval) {
        this.applicationApproval = applicationApproval;
    }

    public Set<RevisionType> getAllowedRevisions() {
        return allowedRevisions;
    }

    public void setAllowedRevisions(Set<RevisionType> allowedRevisions) {
        this.allowedRevisions = allowedRevisions;
    }
    
    
}
