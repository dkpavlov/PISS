package fmi.hibernate.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-3
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Thesis extends BaseEntity {

    @OneToOne
    private ThesisProposal thesisProposal;

    @OneToOne
    private User user;

    @Column
    private String text;

    @Column
    private String summaryEnglish;

    @Column
    private String summaryBulgarian;

    @OneToOne(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private File file;

    @OneToOne(cascade = CascadeType.ALL)
    private ThesisReview thesisReview;

    @Column
    private Double score;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public ThesisReview getThesisReview() {
        return thesisReview;
    }

    public void setThesisReview(ThesisReview thesisReview) {
        this.thesisReview = thesisReview;
    }

    public ThesisProposal getThesisProposal() {
        return thesisProposal;
    }

    public void setThesisProposal(ThesisProposal thesisProposal) {
        this.thesisProposal = thesisProposal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSummaryEnglish() {
        return summaryEnglish;
    }

    public void setSummaryEnglish(String summaryEnglish) {
        this.summaryEnglish = summaryEnglish;
    }

    public String getSummaryBulgarian() {
        return summaryBulgarian;
    }

    public void setSummaryBulgarian(String summaryBulgarian) {
        this.summaryBulgarian = summaryBulgarian;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
