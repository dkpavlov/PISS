package fmi.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-7
 * Time: 14:22
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class IndividualWorkPlan extends BaseEntity {

    @ManyToOne
    private User phd;

    @Column
    private String thesis;

    @Column
    private Date dateThesis;

    @Column
    private String protocolNumber;

    @Column
    private String deanReportNumber;

    @Column
    private Date deanReportDate;

    @ManyToOne
    private User head;

    public void setDeanReportDateAsString(String str){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.deanReportDate = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDeanReportDateAsString(){
        if(this.deanReportDate != null){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.format(this.deanReportDate);
        }
        return "";

    }

    public void setDateThesisAsString(String str){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.dateThesis = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDateThesisAsString(){
        if(this.dateThesis != null){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.format(this.dateThesis);
        }
        return "";

    }

    public User getPhd() {
        return phd;
    }

    public void setPhd(User phd) {
        this.phd = phd;
    }

    public String getThesis() {
        return thesis;
    }

    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    public Date getDateThesis() {
        return dateThesis;
    }

    public void setDateThesis(Date dateThesis) {
        this.dateThesis = dateThesis;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getDeanReportNumber() {
        return deanReportNumber;
    }

    public void setDeanReportNumber(String deanReportNumber) {
        this.deanReportNumber = deanReportNumber;
    }

    public Date getDeanReportDate() {
        return deanReportDate;
    }

    public void setDeanReportDate(Date deanReportDate) {
        this.deanReportDate = deanReportDate;
    }

    public User getHead() {
        return head;
    }

    public void setHead(User head) {
        this.head = head;
    }
}
