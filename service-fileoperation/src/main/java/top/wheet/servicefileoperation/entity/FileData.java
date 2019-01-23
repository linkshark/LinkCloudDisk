package top.wheet.servicefileoperation.entity;



import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class FileData {
    @Id
    @GenericGenerator(name="uuid", strategy="uuid")
    @GeneratedValue(generator="uuid")
    private String id;
    private String fileName;
    @CreatedBy
    private String userName;
    @CreatedDate
    private Date CreatedDate;
    @LastModifiedDate
    private Date LastModifiedDate;
    @LastModifiedBy
    private String lastmodifiedBy;
    private String path;

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getUserName() {
        return userName;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public Date getLastModifiedDate() {
        return LastModifiedDate;
    }

    public String getLastmodifiedBy() {
        return lastmodifiedBy;
    }

    public String getPath() {
        return path;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        LastModifiedDate = lastModifiedDate;
    }

    public void setLastmodifiedBy(String lastmodifiedBy) {
        this.lastmodifiedBy = lastmodifiedBy;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileData quickSetId(String id) {
        this.id = id;
        return this;
    }

    public FileData quickSetFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public FileData quickSetUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public FileData quickSetCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
        return this;
    }

    public FileData quickSetLastModifiedDate(Date lastModifiedDate) {
        LastModifiedDate = lastModifiedDate;
        return this;
    }

    public FileData quickSetLastmodifiedBy(String lastmodifiedBy) {
        this.lastmodifiedBy = lastmodifiedBy;
        return this;
    }

    public FileData quickSetPath(String path) {
        this.path = path;
        return this;
    }
}
