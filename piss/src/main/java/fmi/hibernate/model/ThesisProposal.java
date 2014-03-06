package fmi.hibernate.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-2
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class ThesisProposal extends BaseEntity {

    @OneToOne
    private User user;

    @ManyToOne
    private User tutor;

    @ManyToOne
    private User consultant;

    @Column
    private String consultantChair;

    @Column
    private String thesis;

    @Column
    private String annotation;

    @Column
    private String thesisGoal;

    @Column
    private String tasks;

    @Column
    private String constraints;

    @Column
    private String deadline;

    @Column
    @Enumerated(EnumType.STRING)
    private ThesisProposalStatus status = ThesisProposalStatus.PENDING;

    @Column
    private String note;



    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ThesisProposalStatus getStatus() {
        return status;
    }

    public void setStatus(ThesisProposalStatus status) {
        this.status = status;
    }

    public User getTutor() {
        return tutor;
    }

    public void setTutor(User tutor) {
        this.tutor = tutor;
    }

    public User getConsultant() {
        return consultant;
    }

    public void setConsultant(User consultant) {
        this.consultant = consultant;
    }

    public String getConsultantChair() {
        return consultantChair;
    }

    public void setConsultantChair(String consultantChair) {
        this.consultantChair = consultantChair;
    }

    public String getThesis() {
        return thesis;
    }

    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getThesisGoal() {
        return thesisGoal;
    }

    public void setThesisGoal(String thesisGoal) {
        this.thesisGoal = thesisGoal;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
