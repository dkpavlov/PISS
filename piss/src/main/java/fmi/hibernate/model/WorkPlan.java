package fmi.hibernate.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-7
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class WorkPlan extends BaseEntity {

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "part_one", joinColumns = {
            @JoinColumn(name = "PLAN_ID") },
            inverseJoinColumns = { @JoinColumn(name = "LINE_ID")})
    private List<Line> partOne;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "part_two", joinColumns = {
            @JoinColumn(name = "PLAN_ID") },
            inverseJoinColumns = { @JoinColumn(name = "LINE_ID")})
    private List<Line> partTwo;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "part_three", joinColumns = {
            @JoinColumn(name = "PLAN_ID") },
            inverseJoinColumns = { @JoinColumn(name = "LINE_ID")})
    private List<Line> partThree;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "part_four", joinColumns = {
            @JoinColumn(name = "PLAN_ID") },
            inverseJoinColumns = { @JoinColumn(name = "LINE_ID")})
    private List<Line> partFour;

    @ManyToOne
    private User phd;

    @ManyToOne
    private User head;

    public List<Line> getPartFour() {
        return partFour;
    }

    public void setPartFour(List<Line> partFour) {
        this.partFour = partFour;
    }

    public List<Line> getPartOne() {
        return partOne;
    }

    public void setPartOne(List<Line> partOne) {
        this.partOne = partOne;
    }

    public List<Line> getPartTwo() {
        return partTwo;
    }

    public void setPartTwo(List<Line> partTwo) {
        this.partTwo = partTwo;
    }

    public List<Line> getPartThree() {
        return partThree;
    }

    public void setPartThree(List<Line> partThree) {
        this.partThree = partThree;
    }

    public User getPhd() {
        return phd;
    }

    public void setPhd(User phd) {
        this.phd = phd;
    }

    public User getHead() {
        return head;
    }

    public void setHead(User head) {
        this.head = head;
    }
}
