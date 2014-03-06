package fmi.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created with IntelliJ IDEA.
 * User: dkpavlov
 * Date: 12/29/13
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class IntershipEstimation extends BaseEntity {

    @Column
    private String manager;

    @Column
    private String organizationName;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column
    private String dataForIntern;

    @Column
    private String internshipTheame;

    @Column
    private String opinionOnFulfillment;

    @Column
    private String resultsAchieved;

    @Column
    private String gainedExperience;

    @Column
    private String projectDuration;

    @Column
    private String viewOfIntern;

    @Column
    private Double score;

    @Column
    private String recommendationsForIntern;

    @Column
    private Boolean validated = false;

    @OneToOne
    private User user;

    public String getResultsAchieved() {
        return resultsAchieved;
    }

    public void setResultsAchieved(String resultsAchieved) {
        this.resultsAchieved = resultsAchieved;
    }

    public String getOpinionOnFulfillment() {
        return opinionOnFulfillment;
    }

    public void setOpinionOnFulfillment(String opinionOnFulfillment) {
        this.opinionOnFulfillment = opinionOnFulfillment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataForIntern() {
        return dataForIntern;
    }

    public void setDataForIntern(String dataForIntern) {
        this.dataForIntern = dataForIntern;
    }

    public String getInternshipTheame() {
        return internshipTheame;
    }

    public void setInternshipTheame(String internshipTheame) {
        this.internshipTheame = internshipTheame;
    }

    public String getGainedExperience() {
        return gainedExperience;
    }

    public void setGainedExperience(String gainedExperience) {
        this.gainedExperience = gainedExperience;
    }

    public String getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(String projectDuration) {
        this.projectDuration = projectDuration;
    }

    public String getViewOfIntern() {
        return viewOfIntern;
    }

    public void setViewOfIntern(String viewOfIntern) {
        this.viewOfIntern = viewOfIntern;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getRecommendationsForIntern() {
        return recommendationsForIntern;
    }

    public void setRecommendationsForIntern(String recommendationsForIntern) {
        this.recommendationsForIntern = recommendationsForIntern;
    }
}
