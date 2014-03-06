package fmi.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.sql.Blob;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-3
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class File extends BaseEntity {



    @Column
    @Lob
    private Blob content;

    @Column
    private String originalName;

    @Column
    private String contentType;

    @Column
    private Long size;

    public Blob getContent() {
        return content;
    }

    public void setContent(Blob content) {
        this.content = content;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
