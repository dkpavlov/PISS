package fmi.hibernate.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-6
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class StudentList extends BaseEntity {

    @ManyToOne
    private ThesisCommittee committee;

    @ManyToOne
    private User user;

    public ThesisCommittee getCommittee() {
        return committee;
    }

    public void setCommittee(ThesisCommittee committee) {
        this.committee = committee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
