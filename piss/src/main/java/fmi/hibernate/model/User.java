package fmi.hibernate.model;

import fmi.util.MD5Util;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class User extends BaseEntity{



	@Column
    @Size(min = 3, max = 45)
	private String username;
	
	@Column
    @Size(min = 3, max = 45)
	private String password;
	
	@Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column
    @Email
    private String email;

    @Column
    private String fullName;

    @Column
    private String phoneNumber;

    @Column
    private Boolean archived = false;

    @Column
    private Boolean forValidation = true;

    //STUDENT

    @Column
    private String fn;

    @Column
    private String speciality;

    @Column
    private String classOf;

    @Column
    private Boolean graduated = false;

    @Column
    private Date graduatedOn;

    //TEACHER

    @Column
    private String chair;

    //PHD
    //TODO POLE ZA SPECIALNOST
    @Column
    private Date startOfPHD;

    @Column
    private String orderNumber;

    @Column
    private Date orderFrom;

    @Column
    private Date deadLine;


    public void setStartOfPHDAsString(String str){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.startOfPHD = format.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startOfPHD);
            calendar.add(Calendar.YEAR, 3);
            this.deadLine = calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String getStartOfPHDAsString(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if(this.startOfPHD != null){
            return format.format(this.startOfPHD);
        } else {
            return "";
        }

    }

    public String getOrderFromAsString(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if(this.orderFrom != null){
            return format.format(this.orderFrom);
        } else {
            return "";
        }

    }

    public void setOrderFromAsString(String str){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try{
            this.orderFrom = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Date getGraduatedOn() {
        return graduatedOn;
    }


    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Date getStartOfPHD() {
        return startOfPHD;
    }

    public void setStartOfPHD(Date startOfPHD) {
        this.startOfPHD = startOfPHD;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Date orderFrom) {
        this.orderFrom = orderFrom;
    }

    public void setGraduatedOn(Date graduatedOn) {
        this.graduatedOn = graduatedOn;
    }

    public Boolean getGraduated() {
        return graduated;
    }

    public void setGraduated(Boolean graduated) {
        this.graduated = graduated;
    }

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean getForValidation() {
        return forValidation;
    }

    public void setForValidation(Boolean forValidation) {
        this.forValidation = forValidation;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getClassOf() {
        return classOf;
    }

    public void setClassOf(String classOf) {
        this.classOf = classOf;
    }

    public Boolean isForValidation() {
        return forValidation;
    }

    public void setForValidation(boolean forValidation) {
        this.forValidation = forValidation;
    }

    public Boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
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

    public String getFirstName() {
        return fullName;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

    public void setPassword(String password){
        this.password = password;
    }

	public void passwordMD5() {
		password = new MD5Util().generateMD5(this.password);
	}

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
