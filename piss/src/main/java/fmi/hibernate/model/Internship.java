package fmi.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created with IntelliJ IDEA.
 * User: dkpavlov
 * Date: 12/29/13
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Internship extends BaseEntity {

    @Column
    private String organizationName;

    @Column
    private String personForContact;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column
    private String dataForIntern;

    @Column
    private String internshipTheame;

    @Column
    private String internshipGoal;

    @Column
    private String annotation;

    @Column
    private String problemsFromGoal;

    @Column
    private String constraints;

    @Column
    private String durationInHours;

    @Column
    private Boolean providedworkplase;

    @Column
    private Boolean validated = false;

    @OneToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPersonForContact() {
        return personForContact;
    }

    public void setPersonForContact(String personForContact) {
        this.personForContact = personForContact;
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

    public String getInternshipGoal() {
        return internshipGoal;
    }

    public void setInternshipGoal(String internshipGoal) {
        this.internshipGoal = internshipGoal;
    }

    public String getProblemsFromGoal() {
        return problemsFromGoal;
    }

    public void setProblemsFromGoal(String problemsFromGoal) {
        this.problemsFromGoal = problemsFromGoal;
    }

    public String getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(String durationInHours) {
        this.durationInHours = durationInHours;
    }

    public Boolean getProvidedworkplase() {
        return providedworkplase;
    }

    public void setProvidedworkplase(Boolean providedworkplase) {
        this.providedworkplase = providedworkplase;
    }
}
