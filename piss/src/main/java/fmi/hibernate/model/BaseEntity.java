package fmi.hibernate.model;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity implements Serializable{

    @Id
    @GeneratedValue
    @DocumentId
    private Long id;

    @Column
    private Date dateCreated;

    @Column
    private Date dateLastModified;

    @Column
    private String userCreated;

    @Column
    private String userLastModified;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserLastModified() {
        return userLastModified;
    }

    public void setUserLastModified(String userLastModified) {
        this.userLastModified = userLastModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = Long.valueOf(id);
    }

    public void setIdAsString(String id) {
        this.id = Long.valueOf(id);
    }


    //@Transient
    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof BaseEntity))
        {
            return false;
        }

        BaseEntity that = (BaseEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }

    @PreUpdate
    public void preUpdate(){
        this.dateLastModified = new Date();
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.getPrincipal() != null){
            this.userLastModified = currentUser.getPrincipal().toString();
        }

    }
    @PrePersist
    public void perCreate(){
        this.dateCreated = new Date();
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.getPrincipal() != null){
            this.userCreated = currentUser.getPrincipal().toString();
        }

    }

}
