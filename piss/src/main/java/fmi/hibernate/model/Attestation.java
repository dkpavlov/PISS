package fmi.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: dkpavlov
 * Date: 1/7/14
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Attestation extends BaseEntity {

    @Column
    private String text;

    @ManyToOne
    private User phd;

    @ManyToOne
    private User teacher;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getPhd() {
        return phd;
    }

    public void setPhd(User phd) {
        this.phd = phd;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }
}
