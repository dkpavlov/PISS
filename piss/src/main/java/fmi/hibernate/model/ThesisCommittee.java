package fmi.hibernate.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-3
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class ThesisCommittee extends BaseEntity {

    @Column
    private Date date;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> committee;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<User> getCommittee() {
        return committee;
    }

    public void setCommittee(List<User> committee) {
        this.committee = committee;
    }
}
