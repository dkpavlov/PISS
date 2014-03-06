package fmi.hibernate.model;

import org.xlsx4j.sml.Col;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-7
 * Time: 16:51
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Line extends BaseEntity{

    @Column
    private String workContent;

    @Column
    private String form;

    @Column
    private Date deadline;

    @Column
    private String typeOfReport;

    public void setDeadlineAsString(String deadline){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.deadline = format.parse(deadline);
        } catch (ParseException e) {
        }
    }

    public String getDeadlineAsString(){
        return this.deadline.toString();
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getTypeOfReport() {
        return typeOfReport;
    }

    public void setTypeOfReport(String typeOfReport) {
        this.typeOfReport = typeOfReport;
    }
}
